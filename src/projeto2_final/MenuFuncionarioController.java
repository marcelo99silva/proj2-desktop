/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import DAL.Cliente;
import DAL.Funcionario;
import com.github.fxrouter.FXRouter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pedro Ferreira
 */
public class MenuFuncionarioController implements Initializable {
    
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
    
    public void consultMeusDados(ActionEvent event) throws IOException {
       
        FXRouter.when("MinhaContaFuncionario", "MinhaContaFuncionario.fxml");     
        FXRouter.goTo("MinhaContaFuncionario" , f);
    }
    
    public void meuHorario(ActionEvent event) throws IOException {
       
        FXRouter.when("MeuHorario", "MeuHorario.fxml");     
        FXRouter.goTo("MeuHorario" , f);
    }
    
    public void minhasAulasFuncionario(ActionEvent event) throws IOException {
       
        FXRouter.when("ConsultarMinhasAulasFuncionario", "ConsultarMinhasAulasFuncionario.fxml");     
        FXRouter.goTo("ConsultarMinhasAulasFuncionario" , f);
    }
    
    public void consultarRegistosMarcacoes(ActionEvent event) throws IOException {
       
        FXRouter.when("ConsultarRegistosMarcacoes", "ConsultarRegistosMarcacoes.fxml");     
        FXRouter.goTo("ConsultarRegistosMarcacoes" , f);
    }
    
    public void consultarConsultasEfetuadas(ActionEvent event) throws IOException {
       
        FXRouter.when("ConsultarConsultasEfetuadas", "ConsultarConsultasEfetuadas.fxml");     
        FXRouter.goTo("ConsultarConsultasEfetuadas" , f);
    }
    
    public void efetuarPagamentoCliente(ActionEvent event) throws IOException {
       
        FXRouter.when("EfetuarPagamentosFuncionario", "EfetuarPagamentosFuncionario.fxml");     
        FXRouter.goTo("EfetuarPagamentosFuncionario" , f);
    }
    
    
    public void close(ActionEvent event) throws IOException {
        /*Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();*/
        FXRouter.when("EscolherLogin", "EscolherLogin.fxml");     
        FXRouter.goTo("EscolherLogin");
    }
}
