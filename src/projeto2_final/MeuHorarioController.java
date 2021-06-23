/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import DAL.Dia;
import DAL.Funcionario;
import DAL.Horario;
import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
public class MeuHorarioController implements Initializable {
    
    
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    
    Funcionario f = (Funcionario) FXRouter.getData(); 
    
    @FXML
    private Text nomeUtilizador;

    
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
    
    private BigDecimal dia1;
    private BigDecimal dia2;
    private BigDecimal dia3;
    private BigDecimal dia4;
    private BigDecimal dia5;
    private BigDecimal dia6;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        nomeUtilizador.setText(f.getNome());
                        
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em1 = factory.createEntityManager();
        Query q = em1.createNamedQuery("Horario.findByIdfuncionario");
        q.setParameter("idfuncionario", f);
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
            
       //horarioNome.setText(idFunc.getNome());
     //  horarioEmail.setText(idFunc.getEmail()); 
       
       
        }
    }    
    
    public void voltarMenu(ActionEvent event) throws IOException {
        FXRouter.when("MenuFuncionario", "MenuFuncionario.fxml");     
        FXRouter.goTo("MenuFuncionario" , f);
    }
    
    public void close(ActionEvent event) throws IOException {
        /*Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();*/
        FXRouter.when("EscolherLogin", "EscolherLogin.fxml");     
        FXRouter.goTo("EscolherLogin");
    }
    
}
