/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import BCrypt.BCrypt;
import DAL.Funcionario;
import DAL.Tipofuncionario;
import com.github.fxrouter.FXRouter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author Pedro Ferreira
 */
public class LoginFuncionarioController implements Initializable {
    
     private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    
    @FXML
    private TextField LoginEmailF;
    
    @FXML
    private PasswordField LoginPasswordF;
    
    @FXML
    private Text emailVazioF;
    
    @FXML
    private Text passwordVazioF;
    
    @FXML
    private Text loginErradoF;

    /**
     * Initializes the controller class.
     */
    
    
    
    
    @FXML
    private void LoginBotao(ActionEvent event) throws IOException {
        emailVazioF.setText("");
        passwordVazioF.setText("");
        loginErradoF.setText("");
        
        try{
            if (LoginEmailF.getText().isEmpty() && LoginPasswordF.getText().isEmpty()) {
                    emailVazioF.setText("Preencha o email");
                    passwordVazioF.setText("Preencha a password");
                }
            else if (LoginEmailF.getText().isEmpty()) {
                    emailVazioF.setText("Preencha o email");
                }
            else if(LoginPasswordF.getText().isEmpty()){
                   passwordVazioF.setText("Preencha a password");
               }

            else{

                factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
                EntityManager em = factory.createEntityManager();
                //Query q = em.createNamedQuery("Cliente.findAll");
                Query q = em.createNamedQuery("Funcionario.findByEmail");
                q.setParameter("email", LoginEmailF.getText());

                Funcionario f = new Funcionario();
                f = (Funcionario) q.getSingleResult();



                if(BCrypt.checkpw(LoginPasswordF.getText(), f.getPassword())){

                    int tipoUser = 0;
                    tipoUser = (((Funcionario) f).getTipofuncionario().getIdTipofuncionario()).intValue();
                     System.out.println( tipoUser);

                        if(tipoUser == 1){
                            //System.out.println("admin");
                            FXRouter.when("MenuAdmin", "MenuAdmin.fxml");     
                            FXRouter.goTo("MenuAdmin" , f);
                        }
                        else if(tipoUser == 2){
                            FXRouter.when("MenuFuncionario", "MenuFuncionario.fxml");     
                            FXRouter.goTo("MenuFuncionario" , f);
                        }
                }

                else{
                    System.out.println("errado");
                    loginErradoF.setText("Credenciais erradas");
                }
            }
        }catch(NoResultException e){
            System.out.println("nao existe");
            loginErradoF.setText("Credenciais erradas");
        }       
    }   
    
    public void voltarMenu(ActionEvent event) throws IOException {
        FXRouter.when("EscolherLogin", "EscolherLogin.fxml");     
        FXRouter.goTo("EscolherLogin");
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
