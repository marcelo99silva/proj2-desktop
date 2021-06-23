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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
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
public class EditarAdminController implements Initializable {
    
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    
    Object arr[] = (Object[]) FXRouter.getData();
    Funcionario f = (Funcionario) arr[0];
    Funcionario a = (Funcionario) arr[1];
    private String password;
    
    @FXML
    private TextField adminNome;
    
    @FXML
    private TextField adminPassword;
    
    @FXML
    private TextField adminMorada;
    
    @FXML
    private TextField adminEmail;
    
    @FXML
    private TextField adminNcc;
    
    @FXML
    private TextField adminNif;
    
    @FXML
    private DatePicker adminData;
    
    @FXML
    private TextField adminTelemovel;
    
    @FXML
    private Text criarVazio;
    @FXML
    private Text nomeUtilizador;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomeUtilizador.setText(f.getNome());
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Funcionario.findByIdfuncionario");
        q.setParameter("idfuncionario", a.getIdfuncionario());
      
            Funcionario admin = new Funcionario();
            admin = (Funcionario) q.getSingleResult();
        
            BigDecimal id = ((Funcionario) admin).getIdfuncionario();
            String nome = ((Funcionario) admin).getNome();
            password = ((Funcionario) admin).getPassword();
            String morada = ((Funcionario) admin).getRua();
            String email = ((Funcionario) admin).getEmail();
            BigInteger ncc = ((Funcionario) admin).getNcc();
            BigInteger nif = ((Funcionario) admin).getNif();
            String data = ((Funcionario) admin).getDataNascimento();
            BigInteger telemovel = ((Funcionario) admin).getTelemovel();
            
            
       adminNome.setText(nome);
       //adminPassword.setText(password);
       adminMorada.setText(morada);
       adminEmail.setText(email);
       adminNcc.setText(ncc.toString() );
       adminNif.setText(nif.toString());
       adminData.setValue(LocalDate.parse(data));
       adminTelemovel.setText(telemovel.toString());
    }   
    
    
    
    public void editarAdmin(ActionEvent event) throws IOException {
        
      //  funcionarioData.setText("");
    
        if (adminNome.getText().isEmpty() || adminMorada.getText().isEmpty() || adminEmail.getText().isEmpty() || adminNcc.getText().isEmpty() || adminNif.getText().isEmpty() || adminData.getValue()==null || adminTelemovel.getText().isEmpty() ) {
                criarVazio.setText("Preencha todos os campos");   
                
         }else{
            String nomeInput = adminNome.getText();
            String moradaInput = adminMorada.getText();
            String emailInput = adminEmail.getText();
            BigInteger nccInput = new BigInteger(adminNcc.getText());
            BigInteger nifInput = new BigInteger(adminNif.getText());
            BigInteger telemovelInput = new BigInteger(adminTelemovel.getText());
            if(!adminPassword.getText().isEmpty()){
                password = BCrypt.hashpw(adminPassword.getText(), BCrypt.gensalt());
            }
            
            Tipofuncionario tp;
            //System.out.println(plano);
            tp =new Tipofuncionario (new BigDecimal("1"));

            

            factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
            EntityManager em = factory.createEntityManager();
            
            Funcionario adm = new Funcionario();
            adm.setIdfuncionario(a.getIdfuncionario());
            adm.setNome(nomeInput);
            adm.setPassword(password);
            adm.setRua(moradaInput);
            adm.setEmail(emailInput);
            adm.setNcc(nccInput);
            adm.setNif(nifInput);
            adm.setDataNascimento(adminData.getValue().toString());
            adm.setTelemovel(telemovelInput);
            adm.setTipofuncionario(tp);
            
          
           
            em.getTransaction().begin();
            em.merge(adm);
            em.getTransaction().commit();
            
            
            FXRouter.when("ConsultarAdmins", "ConsultarAdmins.fxml");     
            FXRouter.goTo("ConsultarAdmins", f);
        }
    }
    
    
    
    
    public void voltarMenu(ActionEvent event) throws IOException {
        FXRouter.when("ConsultarAdmins", "ConsultarAdmins.fxml");     
        FXRouter.goTo("ConsultarAdmins", f);
    }
    public void close(ActionEvent event) throws IOException {
        /*Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();*/
        FXRouter.when("EscolherLogin", "EscolherLogin.fxml");     
        FXRouter.goTo("EscolherLogin");
    }
    
}
