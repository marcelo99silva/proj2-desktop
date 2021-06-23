/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import BCrypt.BCrypt;
import DAL.Funcionario;
import DAL.Plano;
import DAL.Tipofuncionario;
import com.github.fxrouter.FXRouter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
public class CriarFuncionarioController implements Initializable {
    
    
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    Funcionario f = (Funcionario) FXRouter.getData();
    
    @FXML
    private TextField funcionarioNome;
    
    @FXML
    private TextField funcionarioPassword;
    
    @FXML
    private TextField funcionarioMorada;
    
    @FXML
    private TextField funcionarioEmail;
    
    @FXML
    private TextField funcionarioNcc;
    
    @FXML
    private TextField funcionarioNif;
    
    @FXML
    private DatePicker funcionarioData;
    
    @FXML
    private TextField funcionarioTelemovel;
    
    @FXML
    private Text criarVazio;
    @FXML
    private Text nomeUtilizador;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nomeUtilizador.setText(f.getNome());
    }    
    
    
    public void criarFuncionario(ActionEvent event) throws IOException {
        
      //  funcionarioData.setText("");
    
        if (funcionarioNome.getText().isEmpty() || funcionarioPassword.getText().isEmpty() || funcionarioMorada.getText().isEmpty() || funcionarioEmail.getText().isEmpty() || funcionarioNcc.getText().isEmpty() || funcionarioNif.getText().isEmpty() || funcionarioData.getValue()==null || funcionarioTelemovel.getText().isEmpty() ) {
                criarVazio.setText("Preencha os campos");   
                
         }else{
            String nomeInput = funcionarioNome.getText();
            String passwordInput = BCrypt.hashpw(funcionarioPassword.getText(), BCrypt.gensalt());
            String moradaInput = funcionarioMorada.getText();
            String emailInput = funcionarioEmail.getText();
            BigInteger nccInput = new BigInteger(funcionarioNcc.getText());
            BigInteger nifInput = new BigInteger(funcionarioNif.getText());
            BigInteger telemovelInput = new BigInteger(funcionarioTelemovel.getText());
            
            Tipofuncionario tp;
            //System.out.println(plano);
            tp =new Tipofuncionario (new BigDecimal("2"));
       // (new BigInteger("1"));
            

            factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
            EntityManager em = factory.createEntityManager();
            
            Funcionario f = new Funcionario();
            f.setNome(nomeInput);
            f.setPassword(passwordInput);
            f.setRua(moradaInput);
            f.setEmail(emailInput);
            f.setNcc(nccInput);
            f.setNif(nifInput);
            f.setDataNascimento(funcionarioData.getValue().toString());
            f.setTelemovel(telemovelInput);
            f.setTipofuncionario(tp);
            
          
           
           
            em.getTransaction().begin();
            em.persist(f);
            em.getTransaction().commit();
            
            FXRouter.when("ConsultarFuncionarios", "ConsultarFuncionarios.fxml");     
            FXRouter.goTo("ConsultarFuncionarios", this.f);
        }
    }
    
    
    public void voltarMenu(ActionEvent event) throws IOException {
        FXRouter.when("ConsultarFuncionarios", "ConsultarFuncionarios.fxml");     
        FXRouter.goTo("ConsultarFuncionarios", f);
    }
    public void close(ActionEvent event) throws IOException {
        /*Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();*/
        FXRouter.when("EscolherLogin", "EscolherLogin.fxml");     
        FXRouter.goTo("EscolherLogin");
    }
}
