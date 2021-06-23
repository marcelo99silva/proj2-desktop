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
@Table(name = "PLANO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plano.findAll", query = "SELECT p FROM Plano p")
    , @NamedQuery(name = "Plano.findByIdplano", query = "SELECT p FROM Plano p WHERE p.idplano = :idplano")
    , @NamedQuery(name = "Plano.findByNome", query = "SELECT p FROM Plano p WHERE p.nome = :nome")
    , @NamedQuery(name = "Plano.findByPreco", query = "SELECT p FROM Plano p WHERE p.preco = :preco")})
public class Plano implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDPLANO")
    private BigDecimal idplano;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "PRECO")
    private Double preco;
    @OneToMany(mappedBy = "idplano")
    private List<Cliente> clienteList;

    public Plano() {
    }

    public Plano(BigDecimal idplano) {
        this.idplano = idplano;
    }
    
    public Plano(String nome,BigDecimal idplano ) {
        this.nome = nome;
        this.idplano = idplano;
    }

    public BigDecimal getIdplano() {
        return idplano;
    }

    public void setIdplano(BigDecimal idplano) {
        this.idplano = idplano;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @XmlTransient
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idplano != null ? idplano.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plano)) {
            return false;
        }
        Plano other = (Plano) object;
        if ((this.idplano == null && other.idplano != null) || (this.idplano != null && !this.idplano.equals(other.idplano))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAL.Plano[ idplano=" + idplano + " ]";
    }
    
}
