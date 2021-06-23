/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pedro Ferreira
 */
@Entity
@Table(name = "AVALIACAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Avaliacao.findAll", query = "SELECT a FROM Avaliacao a")
    , @NamedQuery(name = "Avaliacao.findByIdavaliacao", query = "SELECT a FROM Avaliacao a WHERE a.idavaliacao = :idavaliacao")
    , @NamedQuery(name = "Avaliacao.findBySexo", query = "SELECT a FROM Avaliacao a WHERE a.sexo = :sexo")
    , @NamedQuery(name = "Avaliacao.findByIdmarcacao", query = "SELECT a FROM Avaliacao a WHERE a.idmarcacao = :idmarcacao")
    , @NamedQuery(name = "Avaliacao.findByPeso", query = "SELECT a FROM Avaliacao a WHERE a.peso = :peso")
    , @NamedQuery(name = "Avaliacao.findByAltura", query = "SELECT a FROM Avaliacao a WHERE a.altura = :altura")
    , @NamedQuery(name = "Avaliacao.findByDobraCutaneaTriceps", query = "SELECT a FROM Avaliacao a WHERE a.dobraCutaneaTriceps = :dobraCutaneaTriceps")
    , @NamedQuery(name = "Avaliacao.findByDobraCutaneaBiceps", query = "SELECT a FROM Avaliacao a WHERE a.dobraCutaneaBiceps = :dobraCutaneaBiceps")
    , @NamedQuery(name = "Avaliacao.findByDobraCutaneaCoxa", query = "SELECT a FROM Avaliacao a WHERE a.dobraCutaneaCoxa = :dobraCutaneaCoxa")
    , @NamedQuery(name = "Avaliacao.findByDobraCutaneaAbdomen", query = "SELECT a FROM Avaliacao a WHERE a.dobraCutaneaAbdomen = :dobraCutaneaAbdomen")
    , @NamedQuery(name = "Avaliacao.findByPerimetroBraco", query = "SELECT a FROM Avaliacao a WHERE a.perimetroBraco = :perimetroBraco")
    , @NamedQuery(name = "Avaliacao.findByPerimetroCintura", query = "SELECT a FROM Avaliacao a WHERE a.perimetroCintura = :perimetroCintura")
    , @NamedQuery(name = "Avaliacao.findByPerimetroPerna", query = "SELECT a FROM Avaliacao a WHERE a.perimetroPerna = :perimetroPerna")})
public class Avaliacao implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDAVALIACAO")
    private BigDecimal idavaliacao;
    @Column(name = "SEXO")
    private String sexo;
    @Column(name = "PESO")
    private Double peso;
    @Column(name = "ALTURA")
    private Double altura;
    @Column(name = "DOBRA_CUTANEA_TRICEPS")
    private Double dobraCutaneaTriceps;
    @Column(name = "DOBRA_CUTANEA_BICEPS")
    private Double dobraCutaneaBiceps;
    @Column(name = "DOBRA_CUTANEA_COXA")
    private Double dobraCutaneaCoxa;
    @Column(name = "DOBRA_CUTANEA_ABDOMEN")
    private Double dobraCutaneaAbdomen;
    @Column(name = "PERIMETRO_BRACO")
    private Double perimetroBraco;
    @Column(name = "PERIMETRO_CINTURA")
    private Double perimetroCintura;
    @Column(name = "PERIMETRO_PERNA")
    private Double perimetroPerna;
    @JoinColumn(name = "IDMARCACAO", referencedColumnName = "IDMARCACAO")
    @ManyToOne
    private Marcacao idmarcacao;

    public Avaliacao() {
    }

    public Avaliacao(BigDecimal idavaliacao) {
        this.idavaliacao = idavaliacao;
    }
    
    public Avaliacao(BigDecimal idavaliacao, Marcacao idmarcacao) {
        this.idavaliacao = idavaliacao;
        this.idmarcacao = idmarcacao;
    }

    public BigDecimal getIdavaliacao() {
        return idavaliacao;
    }

    public void setIdavaliacao(BigDecimal idavaliacao) {
        this.idavaliacao = idavaliacao;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getDobraCutaneaTriceps() {
        return dobraCutaneaTriceps;
    }

    public void setDobraCutaneaTriceps(Double dobraCutaneaTriceps) {
        this.dobraCutaneaTriceps = dobraCutaneaTriceps;
    }

    public Double getDobraCutaneaBiceps() {
        return dobraCutaneaBiceps;
    }

    public void setDobraCutaneaBiceps(Double dobraCutaneaBiceps) {
        this.dobraCutaneaBiceps = dobraCutaneaBiceps;
    }

    public Double getDobraCutaneaCoxa() {
        return dobraCutaneaCoxa;
    }

    public void setDobraCutaneaCoxa(Double dobraCutaneaCoxa) {
        this.dobraCutaneaCoxa = dobraCutaneaCoxa;
    }

    public Double getDobraCutaneaAbdomen() {
        return dobraCutaneaAbdomen;
    }

    public void setDobraCutaneaAbdomen(Double dobraCutaneaAbdomen) {
        this.dobraCutaneaAbdomen = dobraCutaneaAbdomen;
    }

    public Double getPerimetroBraco() {
        return perimetroBraco;
    }

    public void setPerimetroBraco(Double perimetroBraco) {
        this.perimetroBraco = perimetroBraco;
    }

    public Double getPerimetroCintura() {
        return perimetroCintura;
    }

    public void setPerimetroCintura(Double perimetroCintura) {
        this.perimetroCintura = perimetroCintura;
    }

    public Double getPerimetroPerna() {
        return perimetroPerna;
    }

    public void setPerimetroPerna(Double perimetroPerna) {
        this.perimetroPerna = perimetroPerna;
    }

    public Marcacao getIdmarcacao() {
        return idmarcacao;
    }

    public void setIdmarcacao(Marcacao idmarcacao) {
        this.idmarcacao = idmarcacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idavaliacao != null ? idavaliacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Avaliacao)) {
            return false;
        }
        Avaliacao other = (Avaliacao) object;
        if ((this.idavaliacao == null && other.idavaliacao != null) || (this.idavaliacao != null && !this.idavaliacao.equals(other.idavaliacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAL.Avaliacao[ idavaliacao=" + idavaliacao + " ]";
    }
    
}
