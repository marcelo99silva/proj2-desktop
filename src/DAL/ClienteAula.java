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
@Table(name = "CLIENTE_AULA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClienteAula.findAll", query = "SELECT c FROM ClienteAula c")
    ,@NamedQuery(name = "ClienteAula.finddddddd", query = "SELECT c FROM ClienteAula c WHERE c.idaula = :idaula AND c.idcliente = :idcliente")
    , @NamedQuery(name = "ClienteAula.findByIdCliente", query = "SELECT c FROM ClienteAula c WHERE c.idcliente = :idcliente")
    , @NamedQuery(name = "ClienteAula.findByIdClienteaula", query = "SELECT c FROM ClienteAula c WHERE c.idClienteaula = :idClienteaula")})
public class ClienteAula implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CLIENTEAULA")
    private BigDecimal idClienteaula;
    @JoinColumn(name = "IDAULA", referencedColumnName = "IDAULA")
    @ManyToOne
    private Aula idaula;
    @JoinColumn(name = "IDCLIENTE", referencedColumnName = "IDCLIENTE")
    @ManyToOne
    private Cliente idcliente;

    public ClienteAula() {
    }

    public ClienteAula(BigDecimal idClienteaula) {
        this.idClienteaula = idClienteaula;
    }
    
    public ClienteAula(BigDecimal idClienteaula , Aula idaula , Cliente idcliente) {
        this.idClienteaula = idClienteaula;
        this.idaula = idaula;
        this.idcliente = idcliente;
    }

    public BigDecimal getIdClienteaula() {
        return idClienteaula;
    }

    public void setIdClienteaula(BigDecimal idClienteaula) {
        this.idClienteaula = idClienteaula;
    }

    public Aula getIdaula() {
        return idaula;
    }

    public void setIdaula(Aula idaula) {
        this.idaula = idaula;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClienteaula != null ? idClienteaula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClienteAula)) {
            return false;
        }
        ClienteAula other = (ClienteAula) object;
        if ((this.idClienteaula == null && other.idClienteaula != null) || (this.idClienteaula != null && !this.idClienteaula.equals(other.idClienteaula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAL.ClienteAula[ idClienteaula=" + idClienteaula + " ]";
    }
    
}
