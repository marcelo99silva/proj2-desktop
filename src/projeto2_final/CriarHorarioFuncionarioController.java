/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import DAL.Aula;
import DAL.Cliente;
import DAL.Dia;
import DAL.Funcionario;
import DAL.Horario;
import DAL.Marcacao;
import DAL.Plano;
import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTimePicker;
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
import javafx.scene.control.ChoiceBox;
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
public class CriarHorarioFuncionarioController implements Initializable {
    Funcionario f = (Funcionario) FXRouter.getData();
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    
    @FXML
    private JFXComboBox choiseFuncionario;
        
    @FXML
    private TextField horarioEmail;
    
   // @FXML
   // private TextField criarVazio;
    
    @FXML
    private JFXTimePicker horarioSegundaInicio;
        
    @FXML
    private JFXTimePicker horarioSegundaFim;
    
    @FXML
    private JFXTimePicker horarioTercaInicio;
            
    @FXML
    private JFXTimePicker horarioTercaFim;
                
    @FXML
    private JFXTimePicker horarioQuartaInicio;
                    
    @FXML
    private JFXTimePicker horarioQuartaFim;
                        
    @FXML
    private JFXTimePicker horarioQuintaInicio;
                            
    @FXML
    private JFXTimePicker horarioQuintaFim;
                                
    @FXML
    private JFXTimePicker horarioSextaInicio;
                                                                            
    @FXML
    private JFXTimePicker horarioSextaFim;
                                            
    @FXML
    private JFXTimePicker horarioSabadoInicio;
                                                
    @FXML
    private JFXTimePicker horarioSabadoFim;
    @FXML
    private Text nomeUtilizador;
                                                    
    
    ObservableList<String> funcionarioList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomeUtilizador.setText(f.getNome());
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Funcionario.findAll");
        for (Object d : q.getResultList()) {
            if(((Funcionario) d).getTipofuncionario().getIdTipofuncionario().intValue() == 2){     
                String nome = ((Funcionario) d).getNome();


                funcionarioList.addAll(nome);
                choiseFuncionario.getItems().setAll(funcionarioList);
            }
        } 
    }  
    
    
    public void tabelaHorarios (ActionEvent event) throws IOException {

        if(choiseFuncionario.getValue() != null ){
 
            String nomeFuncionario = choiseFuncionario.getValue().toString();
            System.out.println("deu");

        
            factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
            EntityManager em = factory.createEntityManager();
            Query q = em.createNamedQuery("Funcionario.findByNome");
            q.setParameter("nome", nomeFuncionario);

            
            Funcionario func = new Funcionario();
            func = (Funcionario) q.getSingleResult();
           
            
            BigDecimal id = ((Funcionario) func).getIdfuncionario();
            String email = ((Funcionario) func).getEmail();
            horarioEmail.setText(email);

        }else{
            System.out.println("iii");
        }
    }  
    
    
    public void criarHorario(ActionEvent event) throws IOException {
        
      //  clienteData.setText("");
    
        if (choiseFuncionario.getValue() == null ) {
            //    criarVazio.setText("Preencha os campos");   
                
         }else{
            String funcionarioInput = choiseFuncionario.getValue().toString();
            
            //SEGUNDA
            Dia segunda = new Dia(new BigDecimal(1) );
            
            //TERÇA
            Dia terca = new Dia(new BigDecimal(2) );
            
            //QUARTA
            Dia quarta = new Dia(new BigDecimal(3) );
            
            //Quinta
            Dia quinta = new Dia(new BigDecimal(4) );
            
            //SEXTA
            Dia sexta = new Dia(new BigDecimal(5) );
            
            //SABADO
            Dia sabado = new Dia(new BigDecimal(6) );
            
            
            Funcionario fid = descobrirId(choiseFuncionario.getValue().toString());
           
            factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
            EntityManager em = factory.createEntityManager();
               
                            
            em.getTransaction().begin();
                            
            if(horarioSegundaInicio.getValue() != null && horarioSegundaFim.getValue() != null){
                Horario h1 = new Horario();
            
                h1.setHoraentrda(horarioSegundaInicio.getValue().toString());
                h1.setHorasaida(horarioSegundaFim.getValue().toString());
                h1.setIddia(segunda);
                h1.setIdfuncionario(fid);
                
                em.persist(h1);
            }
            
            if(horarioTercaInicio.getValue() != null && horarioTercaFim.getValue() != null){
                Horario h2 = new Horario();
            
                h2.setHoraentrda(horarioTercaInicio.getValue().toString());
                h2.setHorasaida(horarioTercaFim.getValue().toString());
                h2.setIddia(terca);
                h2.setIdfuncionario(fid);
                
                em.persist(h2);
            }
            
            if(horarioQuartaInicio.getValue() != null && horarioQuartaFim.getValue() != null){
                Horario h3 = new Horario();
            
                h3.setHoraentrda(horarioQuartaInicio.getValue().toString());
                h3.setHorasaida(horarioQuartaFim.getValue().toString());
                h3.setIddia(quarta);
                h3.setIdfuncionario(fid);
                
                em.persist(h3);
            }
            
            if(horarioQuintaInicio.getValue() != null && horarioQuintaFim.getValue() != null){
                Horario h4 = new Horario();
            
                h4.setHoraentrda(horarioQuintaInicio.getValue().toString());
                h4.setHorasaida(horarioQuintaFim.getValue().toString());
                h4.setIddia(quinta);
                h4.setIdfuncionario(fid);
                
                em.persist(h4);
            }
            
            if(horarioSextaInicio.getValue() != null && horarioSextaInicio.getValue() != null){      
                Horario h5 = new Horario();
            
                h5.setHoraentrda(horarioSextaInicio.getValue().toString());
                h5.setHorasaida(horarioSextaInicio.getValue().toString());
                h5.setIddia(sexta);
                h5.setIdfuncionario(fid);
                
                em.persist(h5);
            }
            
            if(horarioSabadoInicio.getValue() != null && horarioSabadoFim.getValue() != null){          
                Horario h6 = new Horario();
            
                h6.setHoraentrda(horarioSabadoInicio.getValue().toString());
                h6.setHorasaida(horarioSabadoFim.getValue().toString());
                h6.setIddia(sabado);
                h6.setIdfuncionario(fid);
            
                em.persist(h6);        
            } 

            
            em.getTransaction().commit();
                
            FXRouter.when("ConsultarHorarioFuncionario", "ConsultarHorarioFuncionario.fxml");     
            FXRouter.goTo("ConsultarHorarioFuncionario", f);
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
    
    public void voltarMenu(ActionEvent event) throws IOException {
        FXRouter.when("ConsultarHorarioFuncionario", "ConsultarHorarioFuncionario.fxml");     
        FXRouter.goTo("ConsultarHorarioFuncionario", f);
    }
    public void close(ActionEvent event) throws IOException {
        /*Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();*/
        FXRouter.when("EscolherLogin", "EscolherLogin.fxml");     
        FXRouter.goTo("EscolherLogin");
    }
    
}
