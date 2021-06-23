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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author Pedro Ferreira
 */
public class MinhaContaController implements Initializable {
    
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    private String password;
    
    Cliente c = (Cliente) FXRouter.getData(); 
    
    @FXML
    private TextField clienteNome;
    
    @FXML
    private TextField clienteUsername;
    
    @FXML
    private TextField clienteMorada;
    
    @FXML
    private TextField clienteEmail;
    
    @FXML
    private TextField clienteNcc;
    
    @FXML
    private TextField clienteTelemovel;
    
    @FXML
    private TextField clientePassword;
    
    @FXML
    private DatePicker clienteData;
    
     @FXML
    private ToggleGroup group;
    
    @FXML
    private TextField clienteSexo;
    
    @FXML
    private TextField clientePlano;
    
    @FXML
    private Text criarVazio;
    
    @FXML
    private Text userNome;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        userNome.setText(c.getNome());
        
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Cliente.findByIdcliente");
        q.setParameter("idcliente", c.getIdcliente());
      //  for (Object d : q.getResultList()) {
      
            Cliente cli = new Cliente();
            cli = (Cliente) q.getSingleResult();
           
            
            BigDecimal id = ((Cliente) cli).getIdcliente();
            String nome = ((Cliente) cli).getNome();
            BigInteger ncc = ((Cliente) cli).getNcc();
            String data = ((Cliente) cli).getDataNascimento();
            BigInteger telemovel = ((Cliente) cli).getTelemovel();
            String rua = ((Cliente) cli).getRua();
            String email = ((Cliente) cli).getEmail();  
            password = ((Cliente) cli).getPassword();
            String sexo = ((Cliente) cli).getSexo();
            String username = ((Cliente) cli).getUsername();
            String plano = ((Cliente) cli).getIdplano().getNome();
       // }
        System.out.println("data:" +data);
       clienteNome.setText(nome);
       clienteUsername.setText(username);
       clienteMorada.setText(rua);
       clienteEmail.setText(email);
       clienteNcc.setText(ncc.toString());
       clienteTelemovel .setText(telemovel.toString());
       clienteSexo.setText(sexo);
       clientePlano.setText(plano);
       clienteData.setValue(LocalDate.parse(data));
       //clientePassword.setText(password);

    }   
    
    
    public void editarCliente(ActionEvent event) throws IOException {
        
      //  clienteData.setText("");
    
        if (clienteNome.getText().isEmpty() || clienteUsername.getText().isEmpty() || clienteMorada.getText().isEmpty() || clienteEmail.getText().isEmpty() || clienteNcc.getText().isEmpty() || clienteTelemovel.getText().isEmpty() ) {
                criarVazio.setText("Preencha todos os campos");   
         }else{
            String nomeInput = clienteNome.getText();
            String usernameInput = clienteUsername.getText();
            String moradaInput = clienteMorada.getText();
            String emailInput = clienteEmail.getText();
            BigInteger nccInput = new BigInteger(clienteNcc.getText());
            BigInteger telemovelInput = new BigInteger(clienteTelemovel.getText());
            if(!clientePassword.getText().isEmpty()){
                password = BCrypt.hashpw(clientePassword.getText(), BCrypt.gensalt());                
            }

            
            factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
            EntityManager em = factory.createEntityManager();
            
           // Cliente c = new Cliente();
            c.setNome(nomeInput);
            c.setUsername(usernameInput);
            c.setRua(moradaInput);
            c.setEmail(emailInput);
            c.setNcc(nccInput);
            c.setTelemovel(telemovelInput);
            c.setDataNascimento(clienteData.getValue().toString());
            c.setPassword(password);

            em.getTransaction().begin();
            em.merge(c);
            em.getTransaction().commit();
            
            FXRouter.when("MenuCliente", "MenuCliente.fxml");     
            FXRouter.goTo("MenuCliente", c);
        }
    }
    
    public void voltarMenu(ActionEvent event) throws IOException {    
        FXRouter.when("MenuCliente", "MenuCliente.fxml");     
        FXRouter.goTo("MenuCliente" , c);
    }
    
    
    public void close(ActionEvent event) throws IOException {
        /*Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();*/
        FXRouter.when("EscolherLogin", "EscolherLogin.fxml");     
        FXRouter.goTo("EscolherLogin");
    }
}
