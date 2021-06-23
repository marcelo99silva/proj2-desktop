/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import DAL.Avaliacao;
import DAL.Funcionario;
import DAL.Marcacao;
import com.github.fxrouter.FXRouter;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author Pedro Ferreira
 */
public class VisualizarConsultaController implements Initializable {
    
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    
    Avaliacao avaliacao = (Avaliacao) FXRouter.getData(); 
    
    @FXML
    private TextField avaliacaoNome;
    
    @FXML
    private TextField avaliacaoEmail;
    
    @FXML
    private TextField avaliacaoSexo;
    
    @FXML
    private TextField avaliacaoAltura;
    
    @FXML
    private TextField avaliacaoPeso;
    
    @FXML
    private TextField avaliacaoBiceps;
    
    @FXML
    private TextField avaliacaoTriceps;
    
    @FXML
    private TextField avaliacaoAbdomen;
    
    @FXML
    private TextField avaliacaoCoxa;
    
    @FXML
    private TextField avaliacaoCintura;
    
    @FXML
    private TextField avaliacaoBraco;
    
    @FXML
    private TextField avaliacaoPerna;
    
    @FXML
    private Text nomeUtilizador;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        nomeUtilizador.setText(avaliacao.getIdmarcacao().getIdfuncionario().getNome());
        
        avaliacaoNome.setText(avaliacao.getIdmarcacao().getIdcliente().getNome());
        avaliacaoEmail.setText(avaliacao.getIdmarcacao().getIdcliente().getEmail());
        avaliacaoSexo.setText(avaliacao.getIdmarcacao().getIdcliente().getSexo());

        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Avaliacao.findByIdavaliacao");
        q.setParameter("idavaliacao", avaliacao.getIdavaliacao());
      
            Avaliacao avalia = new Avaliacao();
            avalia = (Avaliacao) q.getSingleResult();
        
            BigDecimal id = ((Avaliacao) avalia).getIdavaliacao();
            String peso = ((Avaliacao) avalia).getPeso().toString();
            String altura = ((Avaliacao) avalia).getAltura().toString();
            String dobra_triceps = ((Avaliacao) avalia).getDobraCutaneaTriceps().toString();
            String dobra_biceps = ((Avaliacao) avalia).getDobraCutaneaBiceps().toString();
            String dobra_coxa = ((Avaliacao) avalia).getDobraCutaneaCoxa().toString();
            String dobra_abdomen = ((Avaliacao) avalia).getDobraCutaneaAbdomen().toString();
            String perimetro_braco = ((Avaliacao) avalia).getPerimetroBraco().toString();
            String perimetro_cintura = ((Avaliacao) avalia).getPerimetroCintura().toString();
            String perimetro_perna = ((Avaliacao) avalia).getPerimetroPerna().toString();


            
        avaliacaoPeso.setText(peso);  
        avaliacaoAltura.setText(altura);  
        avaliacaoBiceps.setText(dobra_biceps);  
        avaliacaoTriceps.setText(dobra_triceps);  
        avaliacaoAbdomen.setText(dobra_abdomen);  
        avaliacaoCoxa.setText(dobra_coxa);  
        avaliacaoCintura.setText(perimetro_cintura); 
        avaliacaoBraco.setText(perimetro_braco); 
        avaliacaoPerna.setText(perimetro_perna); 
                                          
    }    
    
    public void voltarMenu(ActionEvent event) throws IOException {
        
        Funcionario func = new Funcionario();
        func.setIdfuncionario(avaliacao.getIdmarcacao().getIdfuncionario().getIdfuncionario());
        func.setNome(avaliacao.getIdmarcacao().getIdfuncionario().getNome());
        func.setNcc(avaliacao.getIdmarcacao().getIdfuncionario().getNcc());
        func.setNif(avaliacao.getIdmarcacao().getIdfuncionario().getNif());
        func.setDataNascimento(avaliacao.getIdmarcacao().getIdfuncionario().getDataNascimento());
        func.setTelemovel(avaliacao.getIdmarcacao().getIdfuncionario().getTelemovel());
        func.setRua(avaliacao.getIdmarcacao().getIdfuncionario().getRua());
        func.setEmail(avaliacao.getIdmarcacao().getIdfuncionario().getEmail());
        func.setPassword(avaliacao.getIdmarcacao().getIdfuncionario().getPassword());
        func.setTipofuncionario(avaliacao.getIdmarcacao().getIdfuncionario().getTipofuncionario());
        
        
        FXRouter.when("ConsultarConsultasEfetuadas", "ConsultarConsultasEfetuadas.fxml");     
        FXRouter.goTo("ConsultarConsultasEfetuadas", func);
    }
    
    
    public void close(ActionEvent event) throws IOException {
        /*Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();*/
        FXRouter.when("EscolherLogin", "EscolherLogin.fxml");     
        FXRouter.goTo("EscolherLogin");
    }
}
