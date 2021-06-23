/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import BCrypt.BCrypt;
import DAL.Cliente;
import DAL.Plano;
import com.github.fxrouter.FXRouter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Pedro Ferreira
 */
public class Projeto2_Final extends Application {
     
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("HomePage.fxml")));
        stage.setScene(scene);
        stage.show();
        
        /*
        FXRouter.bind(this, stage, 1100,800);
        FXRouter.when("HomePage", "HomePage.fxml");     
        FXRouter.goTo("HomePage");*/
       
       //System.out.println(BCrypt.hashpw("renatopass", BCrypt.gensalt()));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }   
}