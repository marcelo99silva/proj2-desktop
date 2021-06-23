/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import BCrypt.BCrypt;
import DAL.Aula;
import DAL.Cliente;
import DAL.Funcionario;
import DAL.Plano;
import com.github.fxrouter.FXRouter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author Pedro Ferreira
 */
public class EditarClienteController implements Initializable {
    
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    private String password;
    
    Object arr[] = (Object[]) FXRouter.getData();
    Funcionario f = (Funcionario) arr[0];
    Cliente c = (Cliente) arr[1];
    
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
    private RadioButton clienteSexo2;
    
    @FXML
    private ChoiceBox clientePlano;
    
    @FXML
    private Text criarVazio;
    @FXML
    private Text nomeUtilizador;
    
    ObservableList<String> planoList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomeUtilizador.setText(f.getNome());
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em1 = factory.createEntityManager();
        Query q1 = em1.createNamedQuery("Plano.findAll");
        for (Object d : q1.getResultList()) {    
                
            String nomef = ((Plano) d).getNome();
            planoList.addAll(nomef);
            clientePlano.getItems().setAll(planoList);   
        }
             
        
             
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Cliente.findByIdcliente");
        q.setParameter("idcliente", c.getIdcliente() );
      
            Cliente cli = new Cliente();
            cli = (Cliente) q.getSingleResult();
        
            BigDecimal id = ((Cliente) cli).getIdcliente();
            String nome = ((Cliente) cli).getNome();
            String username = ((Cliente) cli).getUsername();
            String morada = ((Cliente) cli).getRua();
            String email = ((Cliente) cli).getEmail();
            BigInteger ncc = ((Cliente) cli).getNcc();
            BigInteger telemovel = ((Cliente) cli).getTelemovel();
            String sexo = ((Cliente) cli).getSexo();
            password = ((Cliente) cli).getPassword();
            String data = ((Cliente) cli).getDataNascimento();
            String plano = ((Cliente) cli).getIdplano().getNome();
            
            
       clienteNome.setText(nome);
       clienteUsername.setText(username);
       clienteMorada.setText(morada);
       clienteEmail.setText(email);
       clienteNcc.setText(ncc.toString() );
       clienteTelemovel.setText(telemovel.toString());
       //clientePassword.setText(password);
       clienteData.setValue(LocalDate.parse(data));
       clientePlano.setValue(plano);
       
       if(sexo.equals("Masculino") ){
           clienteSexo.setSelected(true);
       }else{
           clienteSexo2.setSelected(true);
       }
    }    
    
    
    public void editarCliente(ActionEvent event) throws IOException {

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
            
            Cliente cli = new Cliente();
            cli.setIdcliente(c.getIdcliente());
            cli.setNome(nomeInput);
            cli.setUsername(usernameInput);
            cli.setRua(moradaInput);
            cli.setEmail(emailInput);
            cli.setNcc(nccInput);
            cli.setTelemovel(telemovelInput);
            cli.setSexo(toogleGroupValue);
            cli.setPassword(password);
            cli.setDataNascimento(clienteData.getValue().toString());
            cli.setIdplano(p);
                            //);
           // c.set(telemovelInput);
           
           
            em.getTransaction().begin();
            em.merge(cli);
            em.getTransaction().commit();
            
            FXRouter.when("ConsultarClientes", "ConsultarClientes.fxml");     
            FXRouter.goTo("ConsultarClientes", f);
        }
    }
    
    
    
    
    
    public void voltarMenu(ActionEvent event) throws IOException {
        FXRouter.when("ConsultarClientes", "ConsultarClientes.fxml");     
        FXRouter.goTo("ConsultarClientes", f);
    }
    public void close(ActionEvent event) throws IOException {
        /*Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();*/
        FXRouter.when("EscolherLogin", "EscolherLogin.fxml");     
        FXRouter.goTo("EscolherLogin");
    }
    
}
