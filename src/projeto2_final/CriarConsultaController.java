/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import DAL.Aula;
import DAL.Cliente;
import DAL.Funcionario;
import DAL.Marcacao;
import DAL.Tipomarcacao;
import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author Pedro Ferreira
 */
public class CriarConsultaController implements Initializable {
    
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    private Funcionario ff ;
    
    boolean existe = false;
    
    String[] horarios= {"02:00", "02:30", "03:00", "03:30", "04:00" };
    //ArrayList<String> horarios = new ArrayList<String>(Arrays.asList( "02:00", "02:30", "03:00", "03:30", "04:00") );
    
    Cliente c = (Cliente) FXRouter.getData(); 
    
    @FXML
    private JFXComboBox consultaFuncionario;
        
    @FXML
    private Text dataVazia;
    
    @FXML
    private Text horarioCheio;
    
    @FXML
    private JFXComboBox consultaHorario;
    
    @FXML
    private TableView consultaTabela;
    
    @FXML
    private DatePicker consultaData;
    
    @FXML
    private TableColumn<Marcacao, String> col_data;
    
    @FXML
    private TableColumn<Marcacao, String> col_funcionario;
        
    @FXML
    private TableColumn<Marcacao, String> col_hora;
    
    @FXML
    private Text userNome;
    
    ObservableList<String> funcionarioList = FXCollections.observableArrayList();
    
    ObservableList<String> horariosList = FXCollections.observableArrayList();
    
     ObservableList<Marcacao> marcacaoList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        userNome.setText(c.getNome());
        
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Funcionario.findAll");
        for (Object d : q.getResultList()) {
                    
            if(((Funcionario) d).getTipofuncionario().getIdTipofuncionario().intValue() == 2){
                
                String nome = ((Funcionario) d).getNome();
                String email = ((Funcionario) d).getEmail();
                

                funcionarioList.addAll(nome);
                consultaFuncionario.getItems().setAll(funcionarioList);

            }   
        }
    }    
    
    
    public void tabelaHorarios (ActionEvent event) throws IOException {

        horariosList.clear();
        consultaHorario.getItems().clear();
        consultaTabela.getItems().clear();
        
        
      if(consultaData.getValue() == null){
          dataVazia.setText("Preencha a data");
      }else{
          if(consultaFuncionario.getValue() != null ){
          
          dataVazia.setText("");
          String nomeFuncionario = consultaFuncionario.getValue().toString();
          
            for(String hora : horarios){

                horariosList.addAll(hora);
                consultaHorario.getItems().setAll(horariosList);
            }
          
      
        
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Marcacao.findByFuncionarioHora");
        q.setParameter("idfuncionario", nomeFuncionario);
        for (Object d : q.getResultList()) {
                    
            if(((Marcacao) d).getData().equals(consultaData.getValue().toString())){
                BigDecimal id = ((Marcacao) d).getIdmarcacao();
                String data = ((Marcacao) d).getData();
                String horario = ((Marcacao) d).getHorario();
                Cliente idCliente = ((Marcacao) d).getIdcliente();
                Funcionario idFuncionario = ((Marcacao) d).getIdfuncionario();
                Tipomarcacao realizado = ((Marcacao) d).getRealizado();
         
         marcacaoList.add(new Marcacao(id,data, horario,idCliente, idFuncionario)  );
            
        consultaTabela.setItems(marcacaoList);
                
            }      
       }
      }
    }
        
    
        col_funcionario.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getIdfuncionario().getNome()));
        col_data.setCellValueFactory(new PropertyValueFactory<>("data"));
        col_hora.setCellValueFactory(new PropertyValueFactory<>("horario"));
    }  
    
    public void marcarConsulta (ActionEvent event) throws IOException {
        
        if(!existe){
        
        String nomeFuncionario = consultaFuncionario.getValue().toString();
        String data = consultaData.getValue().toString();
        String horario = consultaHorario.getValue().toString();
        Funcionario fid = descobrirId(nomeFuncionario);
        
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        
        Tipomarcacao tipo = new Tipomarcacao( new BigDecimal("2") );
            
        Marcacao marca = new Marcacao();
            
            marca.setIdfuncionario(fid);
            marca.setIdcliente(c);
            marca.setData(data);
            marca.setHorario(horario);
            marca.setRealizado(tipo);
            
        
        em.getTransaction().begin();
        em.persist(marca);
        em.getTransaction().commit();

        FXRouter.when("MenuCliente", "MenuCliente.fxml");     
        FXRouter.goTo("MenuCliente", c);
        
         }
       
    }
    
    
    private Funcionario descobrirId (String nomeF) {

        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Funcionario.findAll");   
        Funcionario f = null;

        for (Object d : q.getResultList()) {
            
            String nome = ((Funcionario) d).getNome(); 
            
            if (nome.equals(nomeF)) {
               System.out.println("valor id:" +((Funcionario) d).getIdfuncionario()); 
               f = (Funcionario) d;
            }
        }
        
        return f;
    }
    
    
    
    public void escolherHorario (ActionEvent event) throws IOException {
          horarioCheio.setText("");
          existe = false;

        if(consultaHorario.getValue() != null ){
          String nomeFuncionario = consultaFuncionario.getValue().toString();

        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Marcacao.findByFuncionarioHora");
        q.setParameter("idfuncionario", nomeFuncionario);
        for (Object d : q.getResultList()) {
                    
            if(((Marcacao) d).getData().equals(consultaData.getValue().toString())){
                BigDecimal id = ((Marcacao) d).getIdmarcacao();
                String data = ((Marcacao) d).getData();
                String horario = ((Marcacao) d).getHorario();
                Cliente idCliente = ((Marcacao) d).getIdcliente();
                Funcionario idFuncionario = ((Marcacao) d).getIdfuncionario();
                

                if(((Marcacao) d).getHorario().equals(consultaHorario.getValue().toString())){
                    horarioCheio.setText("ja existe consulta nesse dia");
                    
                    existe = true;
                }
                               
                
            }
            
       }
       
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
