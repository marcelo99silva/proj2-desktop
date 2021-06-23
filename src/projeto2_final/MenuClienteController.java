/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import DAL.Cliente;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.github.fxrouter.FXRouter;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Pedro Ferreira
 */
public class MenuClienteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Text userNome;
    
    Cliente c = (Cliente) FXRouter.getData(); 
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        userNome.setText(c.getNome());  
    }   
    
    public void minhaConta(ActionEvent event) throws IOException {
            
        FXRouter.when("MinhaConta", "MinhaConta.fxml");     
        FXRouter.goTo("MinhaConta", c);
    }
    
    public void consulta(ActionEvent event) throws IOException {
            
        FXRouter.when("CriarConsulta", "CriarConsulta.fxml");     
        FXRouter.goTo("CriarConsulta", c);
    }
        
    public void consultAulasCliente(ActionEvent event) throws IOException {
            
        FXRouter.when("ConsultarAulasCliente", "ConsultarAulasCliente.fxml");     
        FXRouter.goTo("ConsultarAulasCliente", c);
    }
    
    public void consultMinhasAulas(ActionEvent event) throws IOException {
            
        FXRouter.when("ConsultarMinhasAulas", "ConsultarMinhasAulas.fxml");     
        FXRouter.goTo("ConsultarMinhasAulas", c);
    }
    
    public void consultMinhasConsultas(ActionEvent event) throws IOException {
            
        FXRouter.when("ConsultarMinhasConsultasCliente", "ConsultarMinhasConsultasCliente.fxml");     
        FXRouter.goTo("ConsultarMinhasConsultasCliente", c);
    }
    
    public void consultMeusPagamentos(ActionEvent event) throws IOException {
            
        FXRouter.when("ConsultarPagamentosCliente", "ConsultarPagamentosCliente.fxml");     
        FXRouter.goTo("ConsultarPagamentosCliente", c);
    }
    
    public void close(ActionEvent event) throws IOException {
        /*Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();*/
        FXRouter.when("EscolherLogin", "EscolherLogin.fxml");     
        FXRouter.goTo("EscolherLogin");
    }
     
}
