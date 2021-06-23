/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import DAL.Aula;
import DAL.Dia;
import DAL.Funcionario;
import DAL.Horario;
import DAL.Tipofuncionario;
import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
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
public class EditarHorarioFuncionarioController implements Initializable {
    
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    Object arr[] = (Object[]) FXRouter.getData();
    Funcionario f = (Funcionario) arr[0];
    Horario h = (Horario) arr[1];
    
    @FXML
    private TextField horarioNome;
        
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
    
    private BigDecimal dia1;
    private BigDecimal dia2;
    private BigDecimal dia3;
    private BigDecimal dia4;
    private BigDecimal dia5;
    private BigDecimal dia6;
 //   private Integer dia7;
                            
                
                                                    
    
    ObservableList<String> funcionarioList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomeUtilizador.setText(f.getNome());
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em1 = factory.createEntityManager();
        Query q = em1.createNamedQuery("Horario.findByIdfuncionario");
        q.setParameter("idfuncionario", h.getIdfuncionario());
        for (Object d : q.getResultList()) {
           
            Horario horario = new Horario();
           // horario = (Horario) q.getSingleResult();
        
            BigDecimal id = ((Horario) d).getIdhorario();
            Funcionario idFunc = ((Horario) d).getIdfuncionario();
            String horaEntrada = ((Horario) d).getHoraentrda();
            String horaSaida = ((Horario) d).getHorasaida();
            Dia idDia = ((Horario) d).getIddia();
            
            Dia segunda = new Dia( new BigDecimal(1));
            Dia terca = new Dia( new BigDecimal(2));
            Dia quarta = new Dia( new BigDecimal(3));
            Dia quinta = new Dia( new BigDecimal(4));
            Dia sexta = new Dia( new BigDecimal(5));
            Dia sabado = new Dia( new BigDecimal(6));
            
            if(idDia.equals(segunda)){
                horarioSegundaInicio.setValue( LocalTime.parse(horaEntrada) );
                horarioSegundaFim.setValue( LocalTime.parse(horaSaida) );
                dia1=((Horario) d).getIdhorario();
            }else if(idDia.equals(terca)){
                horarioTercaInicio.setValue( LocalTime.parse(horaEntrada) );
                horarioTercaFim.setValue( LocalTime.parse(horaSaida) );
                dia2=((Horario) d).getIdhorario();
            }else if(idDia.equals(quarta)){
                horarioQuartaInicio.setValue( LocalTime.parse(horaEntrada) );
                horarioQuartaFim.setValue( LocalTime.parse(horaSaida) );
                dia3=((Horario) d).getIdhorario();
            }else if(idDia.equals(quinta)){
                horarioQuintaInicio.setValue( LocalTime.parse(horaEntrada) );
                horarioQuintaFim.setValue( LocalTime.parse(horaSaida) );
                dia4=((Horario) d).getIdhorario();
            }else if(idDia.equals(sexta)){
                horarioSextaInicio.setValue( LocalTime.parse(horaEntrada) );
                horarioSextaFim.setValue( LocalTime.parse(horaSaida) );
                dia5=((Horario) d).getIdhorario();
            }else{
                horarioSabadoInicio.setValue( LocalTime.parse(horaEntrada) );
                horarioSabadoFim.setValue( LocalTime.parse(horaSaida) );   
                dia6=((Horario) d).getIdhorario();
            }    
            
       horarioNome.setText(idFunc.getNome());
       horarioEmail.setText(idFunc.getEmail()); 
       
       
        }
             
    }  
    
    
    
    
    
    public void editarHorario(ActionEvent event) throws IOException {
        
            
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

            factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
            EntityManager em = factory.createEntityManager();
            
                            
            em.getTransaction().begin();
            
            if(horarioSegundaInicio.getValue() != null && horarioSegundaFim.getValue() != null){
                Horario h1 = new Horario();

                h1.setIdhorario(dia1);
                h1.setHoraentrda(horarioSegundaInicio.getValue().toString());
                h1.setHorasaida(horarioSegundaFim.getValue().toString());
                h1.setIddia(segunda);
                h1.setIdfuncionario(h.getIdfuncionario());
                
                em.merge(h1); 
            }else{
                apagaHorario(dia1);
            }
            
            if(horarioTercaInicio.getValue() != null && horarioTercaFim.getValue() != null){
                Horario h2 = new Horario();

                h2.setIdhorario(dia2);
                h2.setHoraentrda(horarioTercaInicio.getValue().toString());
                h2.setHorasaida(horarioTercaFim.getValue().toString());
                h2.setIddia(terca);
                h2.setIdfuncionario(h.getIdfuncionario());
                
                em.merge(h2);
            }else{
                apagaHorario(dia2);
            }
            
            if(horarioQuartaInicio.getValue() != null && horarioQuartaFim.getValue() != null){
                Horario h3 = new Horario();

                h3.setIdhorario(dia3);
                h3.setHoraentrda(horarioQuartaInicio.getValue().toString());
                h3.setHorasaida(horarioQuartaFim.getValue().toString());
                h3.setIddia(quarta);
                h3.setIdfuncionario(h.getIdfuncionario());
                
                em.merge(h3);
            }else{
                apagaHorario(dia3);
            }
            
            if(horarioQuintaInicio.getValue() != null && horarioQuintaFim.getValue() != null){
                Horario h4 = new Horario();

                h4.setIdhorario(dia4);
                h4.setHoraentrda(horarioQuintaInicio.getValue().toString());
                h4.setHorasaida(horarioQuintaFim.getValue().toString());
                h4.setIddia(quinta);
                h4.setIdfuncionario(h.getIdfuncionario());
                
                em.merge(h4);
            }else{
                apagaHorario(dia4);
            }
            
            if(horarioSextaInicio.getValue() != null && horarioSextaInicio.getValue() != null){      
                Horario h5 = new Horario();
            
                h5.setIdhorario(dia5);
                h5.setHoraentrda(horarioSextaInicio.getValue().toString());
                h5.setHorasaida(horarioSextaInicio.getValue().toString());
                h5.setIddia(sexta);
                h5.setIdfuncionario(h.getIdfuncionario());
                
                em.merge(h5);
            }else{
                apagaHorario(dia5);
            }
            
            if(horarioSabadoInicio.getValue() != null && horarioSabadoFim.getValue() != null){          
                Horario h6 = new Horario();
            
                h6.setIdhorario(dia6);
                h6.setHoraentrda(horarioSabadoInicio.getValue().toString());
                h6.setHorasaida(horarioSabadoFim.getValue().toString());
                h6.setIddia(sabado);
                h6.setIdfuncionario(h.getIdfuncionario());
            
                em.merge(h6);
                     
            }else{
                apagaHorario(dia6);
            }    

                em.getTransaction().commit();
            
            
            FXRouter.when("ConsultarHorarioFuncionario", "ConsultarHorarioFuncionario.fxml");     
            FXRouter.goTo("ConsultarHorarioFuncionario", f);
     //   }
    }
    
    
    private void apagaHorario(BigDecimal id) {

        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        Horario horario = new Horario();

        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        Query q = em.createNamedQuery("Horario.findAll");
        for (Object d : q.getResultList()) {
            if (id == ((Horario) d).getIdhorario() ) {
                horario = (Horario) d;
            }
        }

        try {
            em.remove(horario);
            em.flush();

        } catch (Exception e) {

            em.getTransaction().rollback();
        } finally {
            em.getTransaction().commit();
        }
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
