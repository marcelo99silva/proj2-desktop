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
@Table(name = "TIPOFUNCIONARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipofuncionario.findAll", query = "SELECT t FROM Tipofuncionario t")
    , @NamedQuery(name = "Tipofuncionario.findByIdTipofuncionario", query = "SELECT t FROM Tipofuncionario t WHERE t.idTipofuncionario = :idTipofuncionario")
    , @NamedQuery(name = "Tipofuncionario.findByNome", query = "SELECT t FROM Tipofuncionario t WHERE t.nome = :nome")})
public class Tipofuncionario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_TIPOFUNCIONARIO")
    private BigDecimal idTipofuncionario;
    @Column(name = "NOME")
    private String nome;
    @OneToMany(mappedBy = "tipofuncionario")
    private List<Funcionario> funcionarioList;

    public Tipofuncionario() {
    }

    public Tipofuncionario(BigDecimal idTipofuncionario) {
        this.idTipofuncionario = idTipofuncionario;
    }

    public BigDecimal getIdTipofuncionario() {
        return idTipofuncionario;
    }

    public void setIdTipofuncionario(BigDecimal idTipofuncionario) {
        this.idTipofuncionario = idTipofuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public List<Funcionario> getFuncionarioList() {
        return funcionarioList;
    }

    public void setFuncionarioList(List<Funcionario> funcionarioList) {
        this.funcionarioList = funcionarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipofuncionario != null ? idTipofuncionario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipofuncionario)) {
            return false;
        }
        Tipofuncionario other = (Tipofuncionario) object;
        if ((this.idTipofuncionario == null && other.idTipofuncionario != null) || (this.idTipofuncionario != null && !this.idTipofuncionario.equals(other.idTipofuncionario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAL.Tipofuncionario[ idTipofuncionario=" + idTipofuncionario + " ]";
    }
    
}
