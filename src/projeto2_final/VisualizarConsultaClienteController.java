/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import DAL.Avaliacao;
import DAL.Cliente;
import DAL.Marcacao;
import com.github.fxrouter.FXRouter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
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
public class VisualizarConsultaClienteController implements Initializable {
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    
    Marcacao m = (Marcacao) FXRouter.getData(); 
    
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
    private Text userNome;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        userNome.setText(m.getIdcliente().getNome());
     //           avaliacaoNome.setText(avaliacao.getIdmarcacao().getIdcliente().getNome());
       // avaliacaoEmail.setText(avaliacao.getIdmarcacao().getIdcliente().getEmail());
       // avaliacaoSexo.setText(avaliacao.getIdmarcacao().getIdcliente().getSexo());

        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Avaliacao.findByIdmarcacao");
        q.setParameter("idmarcacao", m);
      
            Avaliacao avalia = new Avaliacao();
            avalia = (Avaliacao) q.getSingleResult();
        
            String nomeCliente = ((Avaliacao) avalia).getIdmarcacao().getIdcliente().getNome();
            String sexoCliente = ((Avaliacao) avalia).getIdmarcacao().getIdcliente().getSexo();
            String emailCliente = ((Avaliacao) avalia).getIdmarcacao().getIdcliente().getEmail();
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


        avaliacaoNome.setText(nomeCliente);
        avaliacaoEmail.setText(emailCliente);
        avaliacaoSexo.setText(sexoCliente);
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
    
    public void voltar(ActionEvent event) throws IOException {
        
        Cliente cli = new Cliente();
        cli.setIdcliente(m.getIdcliente().getIdcliente());
        cli.setNome(m.getIdcliente().getNome());
        cli.setNcc(m.getIdcliente().getNcc());
        cli.setDataNascimento(m.getIdcliente().getDataNascimento());
        cli.setTelemovel(m.getIdcliente().getTelemovel());
        cli.setRua(m.getIdcliente().getRua());
        cli.setEmail(m.getIdcliente().getEmail());
        cli.setPassword(m.getIdcliente().getPassword());
        cli.setIdplano(m.getIdcliente().getIdplano());
        cli.setSexo(m.getIdcliente().getSexo());
        cli.setUsername(m.getIdcliente().getUsername());
        
        
        FXRouter.when("ConsultarMinhasConsultasCliente", "ConsultarMinhasConsultasCliente.fxml");     
        FXRouter.goTo("ConsultarMinhasConsultasCliente" , cli);
    }
    
    public void voltarMenu(ActionEvent event) throws IOException {
        
        Cliente cli = new Cliente();
        cli.setIdcliente(m.getIdcliente().getIdcliente());
        cli.setNome(m.getIdcliente().getNome());
        cli.setNcc(m.getIdcliente().getNcc());
        cli.setDataNascimento(m.getIdcliente().getDataNascimento());
        cli.setTelemovel(m.getIdcliente().getTelemovel());
        cli.setRua(m.getIdcliente().getRua());
        cli.setEmail(m.getIdcliente().getEmail());
        cli.setPassword(m.getIdcliente().getPassword());
        cli.setIdplano(m.getIdcliente().getIdplano());
        cli.setSexo(m.getIdcliente().getSexo());
        cli.setUsername(m.getIdcliente().getUsername());
        
        
        FXRouter.when("ConsultarMinhasConsultasCliente", "ConsultarMinhasConsultasCliente.fxml");     
        FXRouter.goTo("ConsultarMinhasConsultasCliente", cli);
    }
    
    
    public void close(ActionEvent event) throws IOException {
        /*Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();*/
        FXRouter.when("EscolherLogin", "EscolherLogin.fxml");     
        FXRouter.goTo("EscolherLogin");
    }
}
