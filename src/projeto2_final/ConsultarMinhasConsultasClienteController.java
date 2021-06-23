/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import DAL.Avaliacao;
import DAL.Cliente;
import DAL.Funcionario;
import DAL.Marcacao;
import DAL.Tipomarcacao;
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
public class ConsultarMinhasConsultasClienteController implements Initializable {
    
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    
    Cliente c = (Cliente) FXRouter.getData(); 
      
    @FXML
    private TableView<Marcacao> tableMarcacoes;
    
    @FXML
    private TableColumn<Marcacao, Integer> col_id;
    
    
    @FXML
    private TableColumn<Marcacao, String> col_data;
    
    @FXML
    private TableColumn<Marcacao, String> col_horario;
    
    @FXML
    private TableColumn<Marcacao, String> col_funcionario;
    
    @FXML
    private TableColumn<Marcacao, String> col_realizado;
    
    @FXML
    private Text editarVazio;
    
    @FXML
    private Text userNome;
    
    ObservableList<Marcacao> aulaList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               
        userNome.setText(c.getNome());
        
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Marcacao.findByCliente");
        q.setParameter("idcliente", c);
        for (Object d : q.getResultList()) {
            
          //  if(((Marcacao) d).getIdfuncionario().getIdfuncionario().intValue() == f.getIdfuncionario().intValue()){
           
            
            BigDecimal id = ((Marcacao) d).getIdmarcacao();
            String data = ((Marcacao) d).getData();
            String horario = ((Marcacao) d).getHorario();
            Cliente cliente = ((Marcacao) d).getIdcliente();
            Funcionario func = ((Marcacao) d).getIdfuncionario();
            Tipomarcacao realizado = ((Marcacao) d).getRealizado();
           // Marcacao marcacao = ((Marcacao) d).getIdmarcacao();

            
            aulaList.add(new Marcacao(id , data, horario, cliente, func, realizado)  );
            
            tableMarcacoes.setItems(aulaList);

                }
            //}
      //  Funcionario sss = new Funcionario();
        
        
        
        col_id.setCellValueFactory(new PropertyValueFactory<>("idmarcacao"));
        col_data.setCellValueFactory(new PropertyValueFactory<>("data"));
        col_horario.setCellValueFactory(new PropertyValueFactory<>("horario"));

        col_funcionario.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getIdfuncionario().getNome()));
        
        col_realizado.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getRealizado().getNome()));

    }   
    
    
        
    public void verConsulta(ActionEvent event) throws IOException {
        
    if (tableMarcacoes.getSelectionModel().getSelectedItem() != null) {
        Marcacao m = tableMarcacoes.getSelectionModel().getSelectedItem();

        if(m.getRealizado().getIdtipo().intValue() == 1){
            FXRouter.when("VisualizarConsultaCliente", "VisualizarConsultaCliente.fxml");     
            FXRouter.goTo("VisualizarConsultaCliente", m);
        }else{
            editarVazio.setText("Esta consulta ainda nao foi realizada");
        }

        
     //   System.out.println("cliente:" +h.getIdfuncionario().getIdfuncionario());
    }else{
       editarVazio.setText("Selecione uma consulta");
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
