/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pedro Ferreira
 */
@Entity
@Table(name = "DIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dia.findAll", query = "SELECT d FROM Dia d")
    , @NamedQuery(name = "Dia.findByIddia", query = "SELECT d FROM Dia d WHERE d.iddia = :iddia")
    , @NamedQuery(name = "Dia.findByNome", query = "SELECT d FROM Dia d WHERE d.nome = :nome")})
public class Dia implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDDIA")
    private BigDecimal iddia;
    @Column(name = "NOME")
    private String nome;
    @OneToMany(mappedBy = "iddia")
    private List<Horario> horarioList;

    public Dia() {
    }

    public Dia(BigDecimal iddia) {
        this.iddia = iddia;
    }

    public BigDecimal getIddia() {
        return iddia;
    }

    public void setIddia(BigDecimal iddia) {
        this.iddia = iddia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public List<Horario> getHorarioList() {
        return horarioList;
    }

    public void setHorarioList(List<Horario> horarioList) {
        this.horarioList = horarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddia != null ? iddia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dia)) {
            return false;
        }
        Dia other = (Dia) object;
        if ((this.iddia == null && other.iddia != null) || (this.iddia != null && !this.iddia.equals(other.iddia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAL.Dia[ iddia=" + iddia + " ]";
    }
    
}
