/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import DAL.Aula;
import DAL.Cliente;
import DAL.ClienteAula;
import DAL.Funcionario;
import com.github.fxrouter.FXRouter;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
public class ConsultarMinhasAulasFuncionarioController implements Initializable {

    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    
    Funcionario f = (Funcionario) FXRouter.getData(); 
    
    @FXML
    private TableView<Aula> tableAulas;
    
    @FXML
    private TableColumn<Aula, String> col_id;
    
    @FXML
    private TableColumn<Aula, String> col_nome;
    
    @FXML
    private TableColumn<Aula, String> col_data;
    
    @FXML
    private TableColumn<Aula, String> col_horaInicio;
    
    @FXML
    private TableColumn<Aula, String> col_horaFim;
    
    @FXML
    private Text nomeUtilizador;
    
   // @FXML
  //  private TableColumn<Aula, String> col_funcionario;
        
  //  @FXML
 //   private Text criarVazio;
    
    ObservableList<Aula> aulaList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        nomeUtilizador.setText(f.getNome());
        
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Aula.findByIdFuncionario");
       // System.out.println(c.getIdcliente());
        q.setParameter("idfuncionario", f);
        for (Object d : q.getResultList()) {
           
            
            BigDecimal id = ((Aula) d).getIdaula();
            String data = ((Aula) d).getDiasemana();
            String horainicial = ((Aula) d).getHorarioinicial();
            String horafinal = ((Aula) d).getHorariofinal();
            String nome = ((Aula) d).getNome();
            Funcionario func = ((Aula) d).getIdfuncionario();
            
            
          //  Aula aula = ((Aula) d).getIdaula();
        //    Cliente cliente = ((Aula) d).getIdcliente();


            
            aulaList.add(new Aula(id, nome, data, horainicial, horafinal , func)  );
            //teste.setCellValueFactory(param -> new ReadOnlyStringWrapper(nome));
            
            tableAulas.setItems(aulaList);

                }

        
        
        
        col_id.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getIdaula().toString() ));
        
        col_nome.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getNome() ));
        
        col_data.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getDiasemana()));
        
        col_horaInicio.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getHorarioinicial() ));
        
        col_horaFim.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getHorariofinal()));
        
       // col_funcionario.setCellValueFactory(cellData -> 
       //     new SimpleStringProperty(cellData.getValue().getIdaula().getIdfuncionario().getNome()));
    }    
    
    public void voltarMenu(ActionEvent event) throws IOException {    
        FXRouter.when("MenuFuncionario", "MenuFuncionario.fxml");     
        FXRouter.goTo("MenuFuncionario", f);
    }
    public void close(ActionEvent event) throws IOException {
        /*Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();*/
        FXRouter.when("EscolherLogin", "EscolherLogin.fxml");     
        FXRouter.goTo("EscolherLogin");
    }
}
