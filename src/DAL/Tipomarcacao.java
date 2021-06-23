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
@Table(name = "TIPOMARCACAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipomarcacao.findAll", query = "SELECT t FROM Tipomarcacao t")
    , @NamedQuery(name = "Tipomarcacao.findByIdtipo", query = "SELECT t FROM Tipomarcacao t WHERE t.idtipo = :idtipo")
    , @NamedQuery(name = "Tipomarcacao.findByNome", query = "SELECT t FROM Tipomarcacao t WHERE t.nome = :nome")})
public class Tipomarcacao implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDTIPO")
    private BigDecimal idtipo;
    @Column(name = "NOME")
    private String nome;
    @OneToMany(mappedBy = "realizado")
    private List<Marcacao> marcacaoList;

    public Tipomarcacao() {
    }

    public Tipomarcacao(BigDecimal idtipo) {
        this.idtipo = idtipo;
    }

    public BigDecimal getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(BigDecimal idtipo) {
        this.idtipo = idtipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public List<Marcacao> getMarcacaoList() {
        return marcacaoList;
    }

    public void setMarcacaoList(List<Marcacao> marcacaoList) {
        this.marcacaoList = marcacaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipo != null ? idtipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipomarcacao)) {
            return false;
        }
        Tipomarcacao other = (Tipomarcacao) object;
        if ((this.idtipo == null && other.idtipo != null) || (this.idtipo != null && !this.idtipo.equals(other.idtipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAL.Tipomarcacao[ idtipo=" + idtipo + " ]";
    }
    
}
