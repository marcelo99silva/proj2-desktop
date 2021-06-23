/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import DAL.Funcionario;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pedro Ferreira
 */
public class MenuAdminController implements Initializable {
    
    Funcionario f = (Funcionario) FXRouter.getData(); 
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
    
  
    public void consultClientes(ActionEvent event) throws IOException {
       
        FXRouter.when("ConsultarClientes", "ConsultarClientes.fxml");     
        FXRouter.goTo("ConsultarClientes" , f);
    }
    
    public void consultFuncionarios(ActionEvent event) throws IOException {
        
        FXRouter.when("ConsultarFuncionarios", "ConsultarFuncionarios.fxml");     
        FXRouter.goTo("ConsultarFuncionarios", f);
    }
    
    public void consultAdmins(ActionEvent event) throws IOException {

        FXRouter.when("ConsultarAdmins", "ConsultarAdmins.fxml");     
        FXRouter.goTo("ConsultarAdmins", f);
    }
    
    public void consultAulas(ActionEvent event) throws IOException {
            
        FXRouter.when("ConsultarAulas", "ConsultarAulas.fxml");     
        FXRouter.goTo("ConsultarAulas", f);
    }
    
    public void consultHorarioFuncionario(ActionEvent event) throws IOException {
            
        FXRouter.when("ConsultarHorarioFuncionario", "ConsultarHorarioFuncionario.fxml");     
        FXRouter.goTo("ConsultarHorarioFuncionario", f);
    }
    
    public void efetuarPagamentoCliente(ActionEvent event) throws IOException {
            
        FXRouter.when("EfetuarPagamentosAdmin", "EfetuarPagamentosAdmin.fxml");     
        FXRouter.goTo("EfetuarPagamentosAdmin", f);
    }
    
    public void close(ActionEvent event) throws IOException {
        /*Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();*/
        FXRouter.when("EscolherLogin", "EscolherLogin.fxml");     
        FXRouter.goTo("EscolherLogin", f);
    }
    
}
