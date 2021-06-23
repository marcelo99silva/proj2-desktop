/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import DAL.Cliente;
import DAL.Funcionario;
import DAL.Mes;
import DAL.Pagamento;
import DAL.Plano;
import DAL.Tipopagamento;
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
import javafx.scene.control.ComboBox;
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
public class EfetuarPagamentosAdminController implements Initializable {
    Funcionario f = (Funcionario) FXRouter.getData();
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    
    boolean existe = false;
    
    
    @FXML
    private TableView<Cliente> tableClientes;
    
    @FXML
    private TableView<Pagamento> tabelaMensalidades;
    
    @FXML
    private TableColumn<Cliente, String> col_nome;
    
    @FXML
    private TableColumn<Cliente, String> col_email;
    
    @FXML
    private TableColumn<Cliente, String> col_plano;
    
    @FXML
    private TableColumn<Cliente, String> col_preco;
    
    
    @FXML
    private TableColumn<Pagamento, String> col_clienteMensalidade;
    
    @FXML
    private TableColumn<Pagamento, String> col_mesMensalidade;
    
    @FXML
    private TableColumn<Pagamento, String> col_precoMensalidade;
    
    @FXML
    private ComboBox metodoPaga;
    
    @FXML
    private ComboBox mesMensalidade;   

    @FXML
    private Text editarVazio;
    
    @FXML
    private Text textErro;
    
    @FXML
    private TextField precoInput;
    
    @FXML
    private TextField nomeProcurar;
    @FXML
    private Text nomeUtilizador;
    

    ObservableList<Cliente> clienteList = FXCollections.observableArrayList();
    
    ObservableList<String> metodoList = FXCollections.observableArrayList();
    
    ObservableList<Pagamento> pagamentoList = FXCollections.observableArrayList();
    
    ObservableList<String> mesList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomeUtilizador.setText(f.getNome());
        procurarNome();
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Cliente.findAll");
        for (Object d : q.getResultList()) {
                    
           // if(((Funcionario) d).getTipofuncionario().getIdTipofuncionario().intValue() == 2){
                BigDecimal id = ((Cliente) d).getIdcliente();
                String nome = ((Cliente) d).getNome();
                String email = ((Cliente) d).getEmail();
              //  String planoNome = ((Cliente) d).getIdplano().getNome();
             //   Double preco = ((Cliente) d).getIdplano().getPreco();
                
            //BigDecimal id = ((Cliente) d).getIdcliente();
         //   String nome = ((Cliente) d).getNome();
            BigInteger ncc = ((Cliente) d).getNcc();
            String data = ((Cliente) d).getDataNascimento();
            BigInteger telemovel = ((Cliente) d).getTelemovel();
            String rua = ((Cliente) d).getRua();
          //  String email = ((Cliente) d).getEmail();  
            String password = ((Cliente) d).getPassword();
            String sexo = ((Cliente) d).getSexo();
            String username = ((Cliente) d).getUsername();
            
            Plano pp = ((Cliente) d).getIdplano();
                

            clienteList.add(new Cliente(id, nome, username, telemovel, ncc, rua, email, data, sexo, pp));
            
            tableClientes.setItems(clienteList);
            
            
            col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            col_email.setCellValueFactory(new PropertyValueFactory<>("email")); 
            col_plano.setCellValueFactory(cellData -> 
                new SimpleStringProperty(cellData.getValue().getIdplano().getNome()));
            col_preco.setCellValueFactory(cellData -> 
                new SimpleStringProperty(cellData.getValue().getIdplano().getPreco().toString()));
 
            }
        
        Query qq = em.createNamedQuery("Tipopagamento.findAll");
        for (Object dd : qq.getResultList()) {
                      
            String tipo = ((Tipopagamento) dd).getNome();
            
            metodoList.addAll(tipo);
            metodoPaga.getItems().setAll(metodoList);

            } 
    }   
    
    
    public void tabelaClientes (ActionEvent event) throws IOException {


        tabelaMensalidades.getItems().clear();

        if(tableClientes.getSelectionModel().getSelectedItem() != null){
            
            Cliente c = tableClientes.getSelectionModel().getSelectedItem();
          
          
      precoInput.setText(c.getIdplano().getPreco().toString());
        
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Pagamento.findByCliente");
        q.setParameter("idcliente", c);
        for (Object d : q.getResultList()) {
                    
                BigDecimal id = ((Pagamento) d).getIdpagamento();
                Cliente idCliente = ((Pagamento) d).getIdcliente();
                Mes mes = ((Pagamento) d).getMes();
                Double preco = ((Pagamento) d).getPreco();

                Tipopagamento tipoPaga = ((Pagamento) d).getTipopagamento();

         
         pagamentoList.add(new Pagamento(id, preco, mes, idCliente, tipoPaga)  );
            
        tabelaMensalidades.setItems(pagamentoList);
                
         //   }      
       }
        
        
        Query qq = em.createNamedQuery("Mes.findAll");
        for (Object dd : qq.getResultList()) {
                      
            String nomeMes = ((Mes) dd).getNome();
            

            mesList.addAll(nomeMes);
            mesMensalidade.getItems().setAll(mesList);

            } 
      }
        
    
        col_clienteMensalidade.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getIdcliente().getNome()));
        col_mesMensalidade.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getMes().getNome()));
        col_precoMensalidade.setCellValueFactory(new PropertyValueFactory<>("preco"));
    }    
    
    
    public void escolherMes (ActionEvent event) throws IOException {
         existe = false;
         textErro.setText("");

        if(mesMensalidade.getValue() != null ){
          String nomeMes = mesMensalidade.getValue().toString();
          Cliente c = tableClientes.getSelectionModel().getSelectedItem();

        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Pagamento.findByCliente");
        q.setParameter("idcliente", c);
        for (Object d : q.getResultList()) {
                    
                BigDecimal id = ((Pagamento) d).getIdpagamento();
                Cliente data = ((Pagamento) d).getIdcliente();
                Mes horario = ((Pagamento) d).getMes();
                Double idCliente = ((Pagamento) d).getPreco();
                Tipopagamento idFuncionario = ((Pagamento) d).getTipopagamento();
                

                if(((Pagamento) d).getMes().getNome().equals(mesMensalidade.getValue().toString())){
                    
                    textErro.setText("ja esta pago");
                    existe = true;   
            }
            
       }
       
      }
        
    }  
    
    public void efetuarPagamento (ActionEvent event) throws IOException {    
        if(!existe){

        Cliente c = tableClientes.getSelectionModel().getSelectedItem();
        
        String mes = mesMensalidade.getValue().toString();
        String preco = precoInput.getText();
        String metodo = metodoPaga.getValue().toString();
        
        Mes idMes = descobrirId(mes);
        Tipopagamento tipo = null;
        
        
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        
        if(metodo.equals("dinheiro")){
             tipo  = new Tipopagamento( new BigDecimal("1") );
        }else{
             tipo  = new Tipopagamento( new BigDecimal("2") );
        }
            
        Pagamento paga = new Pagamento();
            
            paga.setIdcliente(c);
            paga.setMes(idMes);
            paga.setPreco(Double.parseDouble(preco));
            paga.setTipopagamento(tipo);
            
        
        em.getTransaction().begin();
        em.persist(paga);
        em.getTransaction().commit();

        FXRouter.when("MenuAdmin", "MenuAdmin.fxml");     
        FXRouter.goTo("MenuAdmin", f);
         }
    }
    
    private Mes descobrirId (String nomeMes) {

        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Mes.findAll");   
        Mes m = null;

        for (Object d : q.getResultList()) {
            
            String nome = ((Mes) d).getNome(); 
            
            if (nome.equals(nomeMes)) {
               System.out.println("valor id:" +((Mes) d).getIdmes()); 
               m = (Mes) d;
            }
        }
        
        return m;
    }
    
    
    public void procurarNome(){
    
    nomeProcurar.textProperty().addListener((obs, oldText, newText) -> {
    //System.out.println("Text changed from "+oldText+" to "+newText);
    tableClientes.getItems().clear();
            
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Cliente.findByNomeLike");
        q.setParameter("nome", newText );
        for (Object d : q.getResultList()) {
                    

            BigDecimal id = ((Cliente) d).getIdcliente();
            String nome = ((Cliente) d).getNome();
            String email = ((Cliente) d).getEmail();
            BigInteger ncc = ((Cliente) d).getNcc();
            String data = ((Cliente) d).getDataNascimento();
            BigInteger telemovel = ((Cliente) d).getTelemovel();
            String rua = ((Cliente) d).getRua();
            String password = ((Cliente) d).getPassword();
            String sexo = ((Cliente) d).getSexo();
            String username = ((Cliente) d).getUsername();
            Plano pp = ((Cliente) d).getIdplano();
                

            clienteList.add(new Cliente(id, nome, username, telemovel, ncc, rua, email, data, sexo, pp));
            tableClientes.setItems(clienteList);
            
            
            col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
            col_plano.setCellValueFactory(cellData -> 
                new SimpleStringProperty(cellData.getValue().getIdplano().getNome()));
            col_preco.setCellValueFactory(cellData -> 
                new SimpleStringProperty(cellData.getValue().getIdplano().getPreco().toString()));
 
            }

    });
    
    
    
    }
    
    public void voltarMenu(ActionEvent event) throws IOException {    
        FXRouter.when("MenuAdmin", "MenuAdmin.fxml");     
        FXRouter.goTo("MenuAdmin", f);
    }
    
    public void close(ActionEvent event) throws IOException {
        /*Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();*/
        FXRouter.when("EscolherLogin", "EscolherLogin.fxml");     
        FXRouter.goTo("EscolherLogin");
    }
    
}
