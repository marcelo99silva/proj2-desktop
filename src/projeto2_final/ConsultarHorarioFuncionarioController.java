/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import DAL.Cliente;
import DAL.Funcionario;
import DAL.Horario;
import com.github.fxrouter.FXRouter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
public class ConsultarHorarioFuncionarioController implements Initializable {
    Funcionario f = (Funcionario) FXRouter.getData();
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    
    
    @FXML
    private TableView<Horario> tableHorarios;
    
    @FXML
    private TableColumn<Horario, String> col_id;
    
    @FXML
    private TableColumn<Horario, String> col_nome;
    
    @FXML
    private TableColumn<Horario, String> col_email;
    
    ObservableList<Horario> horarioList = FXCollections.observableArrayList();
    
    @FXML
    private TextField nomeFuncionario;
     
    @FXML
    private Text editarVazio;
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
        Query q = em.createNamedQuery("Horario.findAllDistinct");
        for (Object d : q.getResultList()) {
         //   Integer i = 1;
            
         //   if(((Funcionario) d).getTipofuncionario().getIdTipofuncionario().intValue() == 1){
           
            
            BigDecimal id = ((Horario) d).getIdhorario();
            Funcionario idFunc = ((Horario) d).getIdfuncionario();

            
            horarioList.add(new Horario (id,idFunc) )  ;            
            tableHorarios.setItems(horarioList);
            
            
            
            col_id.setCellValueFactory(cellData -> 
                 new SimpleStringProperty(cellData.getValue().getIdfuncionario().getIdfuncionario().toString()));
            col_nome.setCellValueFactory(cellData -> 
                new SimpleStringProperty(cellData.getValue().getIdfuncionario().getNome()));
            col_email.setCellValueFactory(cellData -> 
                new SimpleStringProperty(cellData.getValue().getIdfuncionario().getEmail()));
        } 
    }    
          
    
    public void CriarHorario(ActionEvent event) throws IOException {
        FXRouter.when("CriarHorarioFuncionario", "CriarHorarioFuncionario.fxml");     
        FXRouter.goTo("CriarHorarioFuncionario", f);
    }
    
    public void voltarMenu(ActionEvent event) throws IOException {
        
        FXRouter.when("MenuAdmin", "MenuAdmin.fxml");     
        FXRouter.goTo("MenuAdmin", f);
    }
    
    public void paginaEditarHorario(ActionEvent event) throws IOException {
        
        if (tableHorarios.getSelectionModel().getSelectedItem() != null) {
            Horario h = tableHorarios.getSelectionModel().getSelectedItem();
            Object arr[] = new Object[]{f, h};
            FXRouter.when("EditarHorarioFuncionario", "EditarHorarioFuncionario.fxml");     
            FXRouter.goTo("EditarHorarioFuncionario", arr);

            System.out.println("cliente:" +h.getIdfuncionario().getIdfuncionario());
        }else{
            editarVazio.setText("Selecione um horario");
        }
    }
    
    public void close(ActionEvent event) throws IOException {
        /*Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();*/
        FXRouter.when("EscolherLogin", "EscolherLogin.fxml");     
        FXRouter.goTo("EscolherLogin");
    }
    
}
