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
@Table(name = "PAGAMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagamento.findAll", query = "SELECT p FROM Pagamento p")
    , @NamedQuery(name = "Pagamento.findByIdpagamento", query = "SELECT p FROM Pagamento p WHERE p.idpagamento = :idpagamento")
    , @NamedQuery(name = "Pagamento.findByPreco", query = "SELECT p FROM Pagamento p WHERE p.preco = :preco")
    , @NamedQuery(name = "Pagamento.findByCliente", query = "SELECT p FROM Pagamento p WHERE p.idcliente = :idcliente order by p.mes")
    , @NamedQuery(name = "Pagamento.findByData", query = "SELECT p FROM Pagamento p WHERE p.mes = :mes")})
public class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDPAGAMENTO")
    private BigDecimal idpagamento;
    @Column(name = "PRECO")
    private Double preco;
    @JoinColumn(name = "IDCLIENTE", referencedColumnName = "IDCLIENTE")
    @ManyToOne
    private Cliente idcliente;
    @JoinColumn(name = "MES", referencedColumnName = "IDMES")
    @ManyToOne
    private Mes mes;
    @JoinColumn(name = "TIPOPAGAMENTO", referencedColumnName = "IDPAGAMENTO")
    @ManyToOne
    private Tipopagamento tipopagamento;

    public Pagamento() {
    }

    public Pagamento(BigDecimal idpagamento) {
        this.idpagamento = idpagamento;
    }
    
    public Pagamento(BigDecimal idpagamento, Double preco, Mes mes, Cliente idcliente, Tipopagamento tipopagamento) {
        this.idpagamento = idpagamento;
        this.preco = preco;
        this.mes = mes;
        this.idcliente = idcliente;
        this.tipopagamento = tipopagamento;
    }

    public BigDecimal getIdpagamento() {
        return idpagamento;
    }

    public void setIdpagamento(BigDecimal idpagamento) {
        this.idpagamento = idpagamento;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    public Mes getMes() {
        return mes;
    }

    public void setMes(Mes mes) {
        this.mes = mes;
    }

    public Tipopagamento getTipopagamento() {
        return tipopagamento;
    }

    public void setTipopagamento(Tipopagamento tipopagamento) {
        this.tipopagamento = tipopagamento;
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
        if (!(object instanceof Pagamento)) {
            return false;
        }
        Pagamento other = (Pagamento) object;
        if ((this.idpagamento == null && other.idpagamento != null) || (this.idpagamento != null && !this.idpagamento.equals(other.idpagamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAL.Pagamento[ idpagamento=" + idpagamento + " ]";
    }
    
}
