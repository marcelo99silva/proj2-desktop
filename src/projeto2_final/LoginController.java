/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import BCrypt.BCrypt;
import DAL.Cliente;
import DAL.Funcionario;
import DAL.Plano;
import com.github.fxrouter.FXRouter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Pedro Ferreira
 */
public class LoginController implements Initializable {
    
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    
    @FXML
    private TextField LoginEmail;
    
    @FXML
    private PasswordField LoginPassword;
    
    @FXML
    private Text emailVazio;
    
    @FXML
    private Text passwordVazio;
    
    @FXML
    private Text loginErrado;
    
    @FXML
    private void LoginBotao(ActionEvent event) throws IOException {
        emailVazio.setText("");
        passwordVazio.setText("");
        loginErrado.setText("");
        
    
        if (LoginEmail.getText().isEmpty() && LoginPassword.getText().isEmpty()) {
                emailVazio.setText("Preencha o email");
                passwordVazio.setText("Preencha a password");
            }
        else if (LoginEmail.getText().isEmpty()) {
                emailVazio.setText("Preencha o email");
            }
        else if(LoginPassword.getText().isEmpty()){
               passwordVazio.setText("Preencha a password");
           }
            
        else{
                 
            factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
            EntityManager em = factory.createEntityManager();
            Query q = em.createNamedQuery("Cliente.findAll");
            for (Object d : q.getResultList()) {
           
                String email = ((Cliente) d).getEmail();     
                String pass = ((Cliente) d).getPassword();
                   
                if (email.equals(LoginEmail.getText()) && BCrypt.checkpw(LoginPassword.getText(), pass) ) {
                    System.out.println(" \n - " + ((Cliente) d).getNome() );

                    Cliente c = new Cliente();
                    c =  (Cliente) d;
                   
                    FXRouter.when("MenuCliente", "MenuCliente.fxml");     
                    FXRouter.goTo("MenuCliente",c);

                }else{
                    loginErrado.setText("Credenciais erradas");
                }
            }
           
            }
 
    }
    
    public void voltarMenu(ActionEvent event) throws IOException {
        FXRouter.when("EscolherLogin", "EscolherLogin.fxml");     
        FXRouter.goTo("EscolherLogin");
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
