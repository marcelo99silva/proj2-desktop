/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import DAL.Aula;
import DAL.Cliente;
import DAL.ClienteAula;
import DAL.Mes;
import DAL.Pagamento;
import DAL.Tipopagamento;
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
public class ConsultarPagamentosClienteController implements Initializable {
    
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
        
    Cliente c = (Cliente) FXRouter.getData();
    
    @FXML
    private TableView<Pagamento> tablePagamentos;
    
    @FXML
    private TableColumn<Pagamento, String> col_mes;
    
    @FXML
    private TableColumn<Pagamento, String> col_preco;
    
    @FXML
    private TableColumn<Pagamento, String> col_metodo;
    
    ObservableList<Pagamento> pagamentosList = FXCollections.observableArrayList();
    
  
    @FXML
    private Text userNome;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userNome.setText(c.getNome());
        
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Pagamento.findByCliente");
        q.setParameter("idcliente", c);
      
        for (Object d : q.getResultList()) {
           
            
            BigDecimal id = ((Pagamento) d).getIdpagamento();
            Mes mes = ((Pagamento) d).getMes();
            Double preco = ((Pagamento) d).getPreco();
            Tipopagamento tipo = ((Pagamento) d).getTipopagamento();
            Cliente cliente = ((Pagamento) d).getIdcliente();
         
            pagamentosList.add(new Pagamento(id, preco, mes, cliente , tipo)  );
            //teste.setCellValueFactory(param -> new ReadOnlyStringWrapper(nome));
            
            tablePagamentos.setItems(pagamentosList);

                }

        
        col_mes.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getMes().getNome() ));
        
        col_preco.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getPreco().toString() ));
        
        col_metodo.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getTipopagamento().getNome()));

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
