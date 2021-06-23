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
@Table(name = "MES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mes.findAll", query = "SELECT m FROM Mes m")
    , @NamedQuery(name = "Mes.findByIdmes", query = "SELECT m FROM Mes m WHERE m.idmes = :idmes")
    , @NamedQuery(name = "Mes.findByNome", query = "SELECT m FROM Mes m WHERE m.nome = :nome")})
public class Mes implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDMES")
    private BigDecimal idmes;
    @Column(name = "NOME")
    private String nome;
    @OneToMany(mappedBy = "mes")
    private List<Pagamento> pagamentoList;

    public Mes() {
    }

    public Mes(BigDecimal idmes) {
        this.idmes = idmes;
    }

    public BigDecimal getIdmes() {
        return idmes;
    }

    public void setIdmes(BigDecimal idmes) {
        this.idmes = idmes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public List<Pagamento> getPagamentoList() {
        return pagamentoList;
    }

    public void setPagamentoList(List<Pagamento> pagamentoList) {
        this.pagamentoList = pagamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmes != null ? idmes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mes)) {
            return false;
        }
        Mes other = (Mes) object;
        if ((this.idmes == null && other.idmes != null) || (this.idmes != null && !this.idmes.equals(other.idmes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAL.Mes[ idmes=" + idmes + " ]";
    }
    
}
