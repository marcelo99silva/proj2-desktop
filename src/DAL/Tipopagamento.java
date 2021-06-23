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
@Table(name = "TIPOPAGAMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipopagamento.findAll", query = "SELECT t FROM Tipopagamento t")
    , @NamedQuery(name = "Tipopagamento.findByIdpagamento", query = "SELECT t FROM Tipopagamento t WHERE t.idpagamento = :idpagamento")
    , @NamedQuery(name = "Tipopagamento.findByNome", query = "SELECT t FROM Tipopagamento t WHERE t.nome = :nome")})
public class Tipopagamento implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDPAGAMENTO")
    private BigDecimal idpagamento;
    @Column(name = "NOME")
    private String nome;
    @OneToMany(mappedBy = "tipopagamento")
    private List<Pagamento> pagamentoList;

    public Tipopagamento() {
    }

    public Tipopagamento(BigDecimal idpagamento) {
        this.idpagamento = idpagamento;
    }

    public BigDecimal getIdpagamento() {
        return idpagamento;
    }

    public void setIdpagamento(BigDecimal idpagamento) {
        this.idpagamento = idpagamento;
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
        hash += (idpagamento != null ? idpagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipopagamento)) {
            return false;
        }
        Tipopagamento other = (Tipopagamento) object;
        if ((this.idpagamento == null && other.idpagamento != null) || (this.idpagamento != null && !this.idpagamento.equals(other.idpagamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAL.Tipopagamento[ idpagamento=" + idpagamento + " ]";
    }
    
}
