/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import DAL.Aula;
import DAL.Cliente;
import DAL.Funcionario;
import com.github.fxrouter.FXRouter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class ConsultarAulasController implements Initializable {
    Funcionario f = (Funcionario) FXRouter.getData();
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    
    
    @FXML
    private TableView<Aula> tableAulas;
    
    @FXML
    private TableColumn<Aula, Integer> col_id;
    
    @FXML
    private TableColumn<Aula, String> col_nome;
    
    @FXML
    private TableColumn<Aula, String> col_data;
    
    @FXML
    private TableColumn<Aula, Integer> col_horaInicio;
    
    @FXML
    private TableColumn<Aula, Integer> col_horaFim;
    
    @FXML
    private TableColumn<Aula, String> col_funcionario;
    
    @FXML
    private Text editarVazio;
    @FXML
    private Text nomeUtilizador;
    
    ObservableList<Aula> aulaList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomeUtilizador.setText(f.getNome());
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Aula.findAll");
        for (Object d : q.getResultList()) {
           
            
            BigDecimal id = ((Aula) d).getIdaula();
            String nome = ((Aula) d).getNome();
            String data = ((Aula) d).getDiasemana();
            String horainicio = ((Aula) d).getHorarioinicial();
            String horafim = ((Aula) d).getHorariofinal();
            
            String nomeF =  ((Aula) d).getIdfuncionario().getNome().toString();

            
            Funcionario funcionario = ((Aula) d).getIdfuncionario();
            funcionario.setNome(nomeF);

            
            aulaList.add(new Aula(id, nome, data, horainicio,horafim, funcionario)  );
            
            tableAulas.setItems(aulaList);

                }
            //}
        Funcionario sss = new Funcionario();
        
        
        
        col_id.setCellValueFactory(new PropertyValueFactory<>("idaula"));
        col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_data.setCellValueFactory(new PropertyValueFactory<>("diasemana"));
        col_horaInicio.setCellValueFactory(new PropertyValueFactory<>("horarioinicial"));  
        col_horaFim.setCellValueFactory(new PropertyValueFactory<>("horariofinal"));
        col_funcionario.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getIdfuncionario().getNome()));
    }   
    
    
    public void paginaCriarAulas(ActionEvent event) throws IOException {
        
        FXRouter.when("CriarAula", "CriarAula.fxml");     
        FXRouter.goTo("CriarAula", f);
    }
    
    public void voltarMenu(ActionEvent event) throws IOException {
        
        FXRouter.when("MenuAdmin", "MenuAdmin.fxml");     
        FXRouter.goTo("MenuAdmin", f);
    }
    
    public void paginaEditarAulas(ActionEvent event) throws IOException {
        
        if (tableAulas.getSelectionModel().getSelectedItem() != null) {
            Aula a = tableAulas.getSelectionModel().getSelectedItem();
            Object arr[] = new Object[]{f, a};
            FXRouter.when("EditarAula", "EditarAula.fxml");     
            FXRouter.goTo("EditarAula", arr);
        }else{
            editarVazio.setText("Selecione uma aula");
        }
    }
    
    
    public void close(ActionEvent event) throws IOException {
        /*Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();*/
        FXRouter.when("EscolherLogin", "EscolherLogin.fxml");     
        FXRouter.goTo("EscolherLogin");
    }
    
}
