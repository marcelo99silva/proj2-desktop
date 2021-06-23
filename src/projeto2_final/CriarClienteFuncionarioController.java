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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class CriarClienteFuncionarioController implements Initializable {
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    
    Funcionario f = (Funcionario) FXRouter.getData(); 
    
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
    private RadioButton clienteSexo;
    
    @FXML
    private ChoiceBox clientePlano;
    
    @FXML
    private Text criarVazio;
    
    
    ObservableList<String> planoList = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         clienteNome.setText("");
        clienteUsername.setText("");
        clienteMorada.setText("");
        clienteEmail.setText("");
        clienteNcc.setText("");
        clienteTelemovel.setText("");
        clientePassword.setText("");
        
        
        
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Plano.findAll");
        for (Object d : q.getResultList()) {
                      
            String nome = ((Plano) d).getNome();
            
            System.out.println(nome);
            
            //planoList.add(new Plano(nome));
           // planoList.removeAll(planoList);
            planoList.addAll(nome);
            
            //clientePlano.setItems(planoList);
            clientePlano.getItems().setAll(planoList);
        // TODO
        }   
    }    
    
    public void criarCliente(ActionEvent event) throws IOException {

        if (clienteNome.getText().isEmpty() || clienteUsername.getText().isEmpty() || clienteMorada.getText().isEmpty() || clienteEmail.getText().isEmpty() || clienteNcc.getText().isEmpty() || clienteTelemovel.getText().isEmpty()  || clientePassword.getText().isEmpty()) {
                criarVazio.setText("Preencha os campos");   
                Integer plano;  
                

         }else{
            String nomeInput = clienteNome.getText();
            String usernameInput = clienteUsername.getText();
            String moradaInput = clienteMorada.getText();
            String emailInput = clienteEmail.getText();
            BigInteger nccInput = new BigInteger(clienteNcc.getText());
            BigInteger telemovelInput = new BigInteger(clienteTelemovel.getText());
            String passwordInput = BCrypt.hashpw(clientePassword.getText(), BCrypt.gensalt());
            
            RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
            String toogleGroupValue = selectedRadioButton.getText();
            
            BigDecimal plano;
            //System.out.println(toogleGroupValue);
            
            if(clientePlano.getValue().equals("plano completo")){
                plano=new BigDecimal("1");
            }else if(clientePlano.getValue().equals("plano matinal")){
                plano=new BigDecimal("2");
            }else{
                plano=new BigDecimal("3");
            }
            
            Plano p;
            System.out.println(plano);
            p =new Plano (plano);
            
            factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
            EntityManager em = factory.createEntityManager();
            
            Cliente c = new Cliente();
            c.setNome(nomeInput);
            c.setUsername(usernameInput);
            c.setRua(moradaInput);
            c.setEmail(emailInput);
            c.setNcc(nccInput);
            c.setTelemovel(telemovelInput);
            c.setSexo(toogleGroupValue);
            c.setPassword(passwordInput);
            c.setDataNascimento(clienteData.getValue().toString());
            c.setIdplano(p);

           
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            
            FXRouter.when("ConsultarClientesFuncionario", "ConsultarClientesFuncionario.fxml");     
            FXRouter.goTo("ConsultarClientesFuncionario");
        }
    }
    
    public void voltarMenu(ActionEvent event) throws IOException {
      
        FXRouter.when("MenuFuncionario", "MenuFuncionario.fxml");     
        FXRouter.goTo("MenuFuncionario", f);
    }
    public void close(ActionEvent event) throws IOException {
        /*Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();*/
        FXRouter.when("EscolherLogin", "EscolherLogin.fxml");     
        FXRouter.goTo("EscolherLogin");
    }
    
}
