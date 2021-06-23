/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import DAL.Aula;
import DAL.Avaliacao;
import DAL.Cliente;
import DAL.Funcionario;
import DAL.Marcacao;
import DAL.Tipomarcacao;
import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author Pedro Ferreira
 */
public class CriarAvaliacaoController implements Initializable {
    
      
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    
    Marcacao marca = (Marcacao) FXRouter.getData(); 
    
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
    private Text criarVazio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        avaliacaoNome.setText(marca.getIdcliente().getNome());
        avaliacaoEmail.setText(marca.getIdcliente().getEmail());
        avaliacaoSexo.setText(marca.getIdcliente().getSexo());
    }  
    
    
    public void criarAvaliacao(ActionEvent event) throws IOException {
        
      //  clienteData.setText("");
    
        if (avaliacaoAltura.getText().isEmpty() || avaliacaoPeso.getText().isEmpty() || avaliacaoBiceps.getText().isEmpty() || avaliacaoTriceps.getText().isEmpty() || avaliacaoAbdomen.getText().isEmpty() || avaliacaoCoxa.getText().isEmpty() || avaliacaoCintura.getText().isEmpty() || avaliacaoBraco.getText().isEmpty() || avaliacaoPerna.getText().isEmpty() ) {
                criarVazio.setText("Preencha os campos");   
                
         }else{
          //  String altura = avaliacaoSexo.getText();
            String altura = avaliacaoAltura.getText();
            String peso = avaliacaoPeso.getText();
            String biceps = avaliacaoBiceps.getText();
            String triceps = avaliacaoTriceps.getText();
            String abdomen = avaliacaoAbdomen.getText();
            String coxa = avaliacaoCoxa.getText();
            String cintura = avaliacaoCintura.getText();
            String sexo = avaliacaoSexo.getText();
            
            String braco = avaliacaoBraco.getText();
            String perna = avaliacaoPerna.getText();
            
           // Funcionario fid = descobrirId(funcionarioInput);
           
            factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
            EntityManager em = factory.createEntityManager();
            
            Avaliacao avaliacao = new Avaliacao();
            
            avaliacao.setIdmarcacao(marca);
            avaliacao.setSexo(sexo);
            avaliacao.setPeso( Double.parseDouble(peso) );
            avaliacao.setAltura( Double.parseDouble(altura) );
            avaliacao.setDobraCutaneaTriceps( Double.parseDouble(triceps) );
            avaliacao.setDobraCutaneaBiceps( Double.parseDouble(biceps) );
            avaliacao.setDobraCutaneaCoxa(Double.parseDouble(coxa) );
            avaliacao.setDobraCutaneaAbdomen(Double.parseDouble(abdomen) );
            avaliacao.setPerimetroBraco(Double.parseDouble(braco) );
            avaliacao.setPerimetroPerna(Double.parseDouble(perna) );
            avaliacao.setPerimetroCintura(Double.parseDouble(cintura) );      
            
            Marcacao m = new Marcacao();
            m.setIdmarcacao(marca.getIdmarcacao());
            m.setIdcliente(marca.getIdcliente());
            m.setIdfuncionario(marca.getIdfuncionario());
            m.setData(marca.getData());
            m.setHorario(marca.getHorario());
            m.setRealizado(new Tipomarcacao (new BigDecimal("1")) );
            
            em.getTransaction().begin();
            em.persist(avaliacao);
            em.merge(m);
            em.getTransaction().commit();
            
            Funcionario f = new Funcionario();
            f.setIdfuncionario(marca.getIdfuncionario().getIdfuncionario());
            f.setNome(marca.getIdfuncionario().getNome());
            f.setNcc(marca.getIdfuncionario().getNcc());
            f.setNif(marca.getIdfuncionario().getNif());
            f.setDataNascimento(marca.getIdfuncionario().getDataNascimento());
            f.setTelemovel(marca.getIdfuncionario().getTelemovel());
            f.setRua(marca.getIdfuncionario().getRua());
            f.setEmail(marca.getIdfuncionario().getEmail());
            f.setPassword(marca.getIdfuncionario().getPassword());
            f.setTipofuncionario(marca.getIdfuncionario().getTipofuncionario());
            
            FXRouter.when("ConsultarRegistosMarcacoes", "ConsultarRegistosMarcacoes.fxml");     
            FXRouter.goTo("ConsultarRegistosMarcacoes", f);
        }
    }
    
    public void voltarMenu(ActionEvent event) throws IOException {
        
        Funcionario func = new Funcionario();
        func.setIdfuncionario(marca.getIdfuncionario().getIdfuncionario());
        func.setNome(marca.getIdfuncionario().getNome());
        func.setNcc(marca.getIdfuncionario().getNcc());
        func.setNif(marca.getIdfuncionario().getNif());
        func.setDataNascimento(marca.getIdfuncionario().getDataNascimento());
        func.setTelemovel(marca.getIdfuncionario().getTelemovel());
        func.setRua(marca.getIdfuncionario().getRua());
        func.setEmail(marca.getIdfuncionario().getEmail());
        func.setPassword(marca.getIdfuncionario().getPassword());
        func.setTipofuncionario(marca.getIdfuncionario().getTipofuncionario());
        
        FXRouter.when("ConsultarRegistosMarcacoes", "ConsultarRegistosMarcacoes.fxml");     
        FXRouter.goTo("ConsultarRegistosMarcacoes" , func);
    }
    public void close(ActionEvent event) throws IOException {
        /*Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();*/
        FXRouter.when("EscolherLogin", "EscolherLogin.fxml");     
        FXRouter.goTo("EscolherLogin");
    }
    
}
