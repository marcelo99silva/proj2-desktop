/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import DAL.Aula;
import DAL.Cliente;
import DAL.Funcionario;
import com.github.fxrouter.FXRouter;
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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
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
public class EditarAulaController implements Initializable {
    Object arr[] = (Object[]) FXRouter.getData();
    Funcionario f = (Funcionario) arr[0];
    Aula a = (Aula) arr[1];
    
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    
    @FXML
    private TextField aulaNome;
    
    @FXML
    private ChoiceBox aulaFuncionario;
    
    @FXML
    private DatePicker aulaData;
    
    @FXML
    private JFXTimePicker aulaInicio;
    
    @FXML
    private JFXTimePicker aulaFim;
    
    @FXML
    private Text criarVazio;
    @FXML
    private Text nomeUtilizador;
    
    ObservableList<String> funcionarioList = FXCollections.observableArrayList();
    
    private String nomeCombobox;
    private String nomeRecebido;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomeUtilizador.setText(f.getNome());        
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em1 = factory.createEntityManager();
        Query q1 = em1.createNamedQuery("Funcionario.findAll");
        for (Object d : q1.getResultList()) {
                    
            if(((Funcionario) d).getTipofuncionario().getIdTipofuncionario().intValue() == 2){                
                
                String nomef = ((Funcionario) d).getNome();
                funcionarioList.addAll(nomef);
                aulaFuncionario.getItems().setAll(funcionarioList);
               
            }   
        }
             
        
             
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Aula.findByIdaula");
        q.setParameter("idaula", a.getIdaula() );
      
            Aula aula = new Aula();
            aula = (Aula) q.getSingleResult();
        
            BigDecimal id = ((Aula) aula).getIdaula();
            String nome = ((Aula) aula).getNome();
            String nomeFuncionario = ((Aula) aula).getIdfuncionario().getNome();
            
            String data = ((Aula) aula).getDiasemana();
            String horarioInicila = ((Aula) aula).getHorarioinicial();
            String horariofinal = ((Aula) aula).getHorariofinal();

            nomeCombobox = nomeFuncionario;
       // }
        //System.out.println("data:" +data);
       aulaNome.setText(nome);
       aulaData.setValue(LocalDate.parse(data));
       aulaInicio.setValue(LocalTime.parse(horarioInicila) );
       aulaFim.setValue(LocalTime.parse(horariofinal) );
       aulaFuncionario.setValue(nomeCombobox);
       
    }    
    
    public void voltarMenu(ActionEvent event) throws IOException {
        FXRouter.when("ConsultarAulas", "ConsultarAulas.fxml");     
        FXRouter.goTo("ConsultarAulas", f);
    }
    
    
    
    public void editarAula(ActionEvent event) throws IOException {

        if (aulaNome.getText().isEmpty() ) {
                criarVazio.setText("Preencha os campos");   
                
         }else{
            String nomeInput = aulaNome.getText();
            String funcionarioInput = aulaFuncionario.getValue().toString();
            String dataInput = aulaData.getValue().toString();
            String horaInicioInput = aulaInicio.getValue().toString();
            String horaFimInput = aulaFim.getValue().toString();
            
            Funcionario fid = descobrirId(funcionarioInput);
           
            factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
            EntityManager em = factory.createEntityManager();
            
            Aula aula = new Aula();
            aula.setIdaula(a.getIdaula());
            aula.setNome(nomeInput);
            aula.setIdfuncionario(fid);
            aula.setDiasemana(dataInput);
            aula.setHorarioinicial(horaInicioInput);
            aula.setHorariofinal(horaFimInput);          
           
            em.getTransaction().begin();
            em.merge(aula);
            em.getTransaction().commit();
            
            FXRouter.when("ConsultarAulas", "ConsultarAulas.fxml");     
            FXRouter.goTo("ConsultarAulas", f);
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
    public void close(ActionEvent event) throws IOException {
        /*Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();*/
        FXRouter.when("EscolherLogin", "EscolherLogin.fxml");     
        FXRouter.goTo("EscolherLogin");
    }
    
}
