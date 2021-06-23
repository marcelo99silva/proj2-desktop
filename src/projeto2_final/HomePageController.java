/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;


import DAL.Tipofuncionario;
import com.github.fxrouter.FXRouter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Pedro Ferreira
 */
public class HomePageController implements Initializable {
    
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory; 
   
   
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
      
        Stage stage2 = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("EscolherLogin.fxml")));
        stage2.setScene(scene);
        stage2.show();
        FXRouter.bind(this, stage2, 1100,800);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
