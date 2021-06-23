/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import BCrypt.BCrypt;
import DAL.Cliente;
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
public class MinhaContaFuncionarioController implements Initializable {
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    private String password;
    Funcionario f = (Funcionario) FXRouter.getData(); 
    
    @FXML
    private TextField funcionarioNome;
    
    @FXML
    private TextField funcionarioEmail;
    
    @FXML
    private TextField funcionarioMorada;
    
    @FXML
    private TextField funcionarioTelemovel;
    
    @FXML
    private TextField funcionarioNcc;
        
    @FXML
    private TextField funcionarioNif;   
        
    @FXML
    private DatePicker funcionarioData;

    @FXML
    private TextField funcionarioPassword;
    
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
        q.setParameter("idfuncionario", f.getIdfuncionario());
      //  for (Object d : q.getResultList()) {
      
            Funcionario fun = new Funcionario();
            fun = (Funcionario) q.getSingleResult();
           
            
            BigDecimal id = ((Funcionario) fun).getIdfuncionario();
            String nome = ((Funcionario) fun).getNome();
            String email = ((Funcionario) fun).getEmail();
            String morada = ((Funcionario) fun).getRua();
            BigInteger telemovel = ((Funcionario) fun).getTelemovel();
            BigInteger ncc = ((Funcionario) fun).getNcc();
            BigInteger nif = ((Funcionario) fun).getNif();
            String data = ((Funcionario) fun).getDataNascimento();
            password = ((Funcionario) fun).getPassword();

        funcionarioNome.setText(nome);
        funcionarioEmail.setText(email);
        funcionarioMorada.setText(morada);
        funcionarioTelemovel .setText(telemovel.toString());
        funcionarioNcc.setText(ncc.toString());
        funcionarioNif.setText(nif.toString());
        funcionarioData.setValue(LocalDate.parse(data));

    }    
    
    
        
    public void editarFuncionario(ActionEvent event) throws IOException {
        
      //  clienteData.setText("");
    
        if (funcionarioNome.getText().isEmpty() || funcionarioEmail.getText().isEmpty() || funcionarioMorada.getText().isEmpty() || funcionarioTelemovel.getText().isEmpty() || funcionarioNcc.getText().isEmpty() || funcionarioNif.getText().isEmpty() ) {
                criarVazio.setText("Preencha todos os campos");   
         }else{
            String nomeInput = funcionarioNome.getText();
            String emailInput = funcionarioEmail.getText();
            String moradaInput = funcionarioMorada.getText();
            BigInteger telemovelInput = new BigInteger(funcionarioTelemovel.getText());
            BigInteger nccInput = new BigInteger(funcionarioNcc.getText());
            BigInteger nifInput = new BigInteger(funcionarioNif.getText());
            if(!funcionarioPassword.getText().isEmpty()){
                password = BCrypt.hashpw(funcionarioPassword.getText(), BCrypt.gensalt());                
            }

            
            factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
            EntityManager em = factory.createEntityManager();
            
            Funcionario func = new Funcionario();
            func.setIdfuncionario(f.getIdfuncionario());
            func.setNome(nomeInput);
            func.setEmail(emailInput);
            func.setRua(moradaInput);
            func.setTelemovel(telemovelInput);
            func.setNcc(nccInput);
            func.setNif(nifInput);
            func.setPassword(password);
            func.setDataNascimento(funcionarioData.getValue().toString());
            func.setTipofuncionario(f.getTipofuncionario());

            em.getTransaction().begin();
            em.merge(func);
            em.getTransaction().commit();
            
            FXRouter.when("MenuFuncionario", "MenuFuncionario.fxml");     
            FXRouter.goTo("MenuFuncionario", f);
        }
    }
    
    
    public void voltarMenu(ActionEvent event) throws IOException {    
        FXRouter.when("MenuFuncionario", "MenuFuncionario.fxml");     
        FXRouter.goTo("MenuFuncionario" , f);
    }
    
    public void close(ActionEvent event) throws IOException {
        /*Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();*/
        FXRouter.when("EscolherLogin", "EscolherLogin.fxml");     
        FXRouter.goTo("EscolherLogin");
    }
}
