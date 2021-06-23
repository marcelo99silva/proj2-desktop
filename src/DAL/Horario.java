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
@Table(name = "HORARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h")
    , @NamedQuery(name = "Horario.findAllDistinct", query = "SELECT h FROM Horario h WHERE h.idhorario IN (SELECT MIN(h.idhorario) FROM Horario h GROUP BY h.idfuncionario ) ORDER BY h.idfuncionario")
    , @NamedQuery(name = "Horario.findByIdfuncionario", query = "SELECT h FROM Horario h WHERE h.idfuncionario = :idfuncionario")
    , @NamedQuery(name = "Horario.findByIdhorario", query = "SELECT h FROM Horario h WHERE h.idhorario = :idhorario")
    , @NamedQuery(name = "Horario.findByHoraentrda", query = "SELECT h FROM Horario h WHERE h.horaentrda = :horaentrda")
    , @NamedQuery(name = "Horario.findByHorasaida", query = "SELECT h FROM Horario h WHERE h.horasaida = :horasaida")})
public class Horario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDHORARIO")
    private BigDecimal idhorario;
    @Column(name = "HORAENTRDA")
    private String horaentrda;
    @Column(name = "HORASAIDA")
    private String horasaida;
    @JoinColumn(name = "IDDIA", referencedColumnName = "IDDIA")
    @ManyToOne
    private Dia iddia;
    @JoinColumn(name = "IDFUNCIONARIO", referencedColumnName = "IDFUNCIONARIO")
    @ManyToOne
    private Funcionario idfuncionario;

    public Horario() {
    }

    public Horario(BigDecimal idhorario) {
        this.idhorario = idhorario;
    }
    
    public Horario(BigDecimal idhorario,Funcionario idfuncionario) {
        this.idhorario = idhorario;
        this.idfuncionario = idfuncionario;
    }

    public BigDecimal getIdhorario() {
        return idhorario;
    }

    public void setIdhorario(BigDecimal idhorario) {
        this.idhorario = idhorario;
    }

    public String getHoraentrda() {
        return horaentrda;
    }

    public void setHoraentrda(String horaentrda) {
        this.horaentrda = horaentrda;
    }

    public String getHorasaida() {
        return horasaida;
    }

    public void setHorasaida(String horasaida) {
        this.horasaida = horasaida;
    }

    public Dia getIddia() {
        return iddia;
    }

    public void setIddia(Dia iddia) {
        this.iddia = iddia;
    }

    public Funcionario getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(Funcionario idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idhorario != null ? idhorario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horario)) {
            return false;
        }
        Horario other = (Horario) object;
        if ((this.idhorario == null && other.idhorario != null) || (this.idhorario != null && !this.idhorario.equals(other.idhorario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAL.Horario[ idhorario=" + idhorario + " ]";
    }
    
}
