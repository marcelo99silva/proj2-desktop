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
import DAL.Horario;
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
public class ConsultarMinhasAulasController implements Initializable {
    
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    
    Cliente c = (Cliente) FXRouter.getData(); 
    
    @FXML
    private TableView<ClienteAula> tableAulas;
    
    @FXML
    private TableColumn<ClienteAula, String> col_id;
    
    @FXML
    private TableColumn<ClienteAula, String> col_nome;
    
    @FXML
    private TableColumn<ClienteAula, String> col_data;
    
    @FXML
    private TableColumn<ClienteAula, String> col_horaInicio;
    
    @FXML
    private TableColumn<ClienteAula, String> col_horaFim;
    
    @FXML
    private TableColumn<ClienteAula, String> col_funcionario;
        
    @FXML
    private Text criarVazio;
    
    @FXML
    private Text userNome;
    
    ObservableList<ClienteAula> aulaList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        userNome.setText(c.getNome());
        
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("ClienteAula.findByIdCliente");
        System.out.println(c.getIdcliente());
        q.setParameter("idcliente", c);
        for (Object d : q.getResultList()) {
           
            
            BigDecimal id = ((ClienteAula) d).getIdClienteaula();
            Aula aula = ((ClienteAula) d).getIdaula();
            Cliente cliente = ((ClienteAula) d).getIdcliente();
          //  String horainicio = ((ClienteAula) d).getHorarioinicial();
          //  String horafim = ((Aula) d).getHorariofinal();
            
       //     String nomeF =  ((Aula) d).getIdfuncionario().getNome().toString();
            
           // System.out.println("nome:" +nomeF);            
         //   Funcionario funcionario = ((Aula) d).getIdfuncionario();
       //     funcionario.setNome(nomeF);

            
            aulaList.add(new ClienteAula(id, aula, cliente)  );
            //teste.setCellValueFactory(param -> new ReadOnlyStringWrapper(nome));
            
            tableAulas.setItems(aulaList);

                }
            //}
     //   Funcionario sss = new Funcionario();
        
        
        
        col_id.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getIdaula().getIdaula().toString() ));
        
        col_nome.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getIdaula().getNome() ));
        
        col_data.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getIdaula().getDiasemana()));
        
        col_horaInicio.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getIdaula().getHorarioinicial() ));
        
        col_horaFim.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getIdaula().getHorariofinal() ));
        
        col_funcionario.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getIdaula().getIdfuncionario().getNome()));
        // TODO
    }    
    
        
    public void desinscreverAula(ActionEvent event) throws IOException {
        
        if (tableAulas.getSelectionModel().getSelectedItem() != null) {
        ClienteAula cli = tableAulas.getSelectionModel().getSelectedItem();       

        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        ClienteAula cliaula = new ClienteAula();

        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        Query q = em.createNamedQuery("ClienteAula.findAll");
        for (Object d : q.getResultList()) {
            if (cli.getIdClienteaula() == ((ClienteAula) d).getIdClienteaula() ) {
                cliaula = (ClienteAula) d;
            }
        }

        try {
            em.remove(cliaula);
            em.flush();

        } catch (Exception e) {

            em.getTransaction().rollback();
        } finally {
            em.getTransaction().commit();
        }
        
        FXRouter.when("ConsultarMinhasAulas", "ConsultarMinhasAulas.fxml");     
        FXRouter.goTo("ConsultarMinhasAulas", c);
    }else{
            criarVazio.setText("Selecione uma aula"); 
        }
    }
    
    
    public void voltarMenu(ActionEvent event) throws IOException {
        FXRouter.when("MenuCliente", "MenuCliente.fxml");     
        FXRouter.goTo("MenuCliente", c);
    }
    public void close(ActionEvent event) throws IOException {
        /*Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();*/
        FXRouter.when("EscolherLogin", "EscolherLogin.fxml");     
        FXRouter.goTo("EscolherLogin");
    }
}
