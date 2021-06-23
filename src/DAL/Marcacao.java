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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "MARCACAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Marcacao.findAll", query = "SELECT m FROM Marcacao m")
    , @NamedQuery(name = "Marcacao.findByIdmarcacao", query = "SELECT m FROM Marcacao m WHERE m.idmarcacao = :idmarcacao")
    , @NamedQuery(name = "Marcacao.findByData", query = "SELECT m FROM Marcacao m WHERE m.data = :data")
    , @NamedQuery(name = "Marcacao.findByFuncionario", query = "SELECT m FROM Marcacao m WHERE m.idfuncionario.nome = :idfuncionario")
    , @NamedQuery(name = "Marcacao.findByCliente", query = "SELECT m FROM Marcacao m WHERE m.idcliente = :idcliente ORDER BY m.idmarcacao")
    , @NamedQuery(name = "Marcacao.findByFuncionarioHora", query = "SELECT m FROM Marcacao m WHERE m.idfuncionario.nome = :idfuncionario order by m.horario")
    , @NamedQuery(name = "Marcacao.findByHorario", query = "SELECT m FROM Marcacao m WHERE m.horario = :horario")})
public class Marcacao implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDMARCACAO")
    private BigDecimal idmarcacao;
    @Column(name = "DATA")
    private String data;
    @Column(name = "HORARIO")
    private String horario;
    @OneToMany(mappedBy = "idmarcacao")
    private List<Avaliacao> avaliacaoList;
    @JoinColumn(name = "IDCLIENTE", referencedColumnName = "IDCLIENTE")
    @ManyToOne
    private Cliente idcliente;
    @JoinColumn(name = "IDFUNCIONARIO", referencedColumnName = "IDFUNCIONARIO")
    @ManyToOne
    private Funcionario idfuncionario;
    @JoinColumn(name = "REALIZADO", referencedColumnName = "IDTIPO")
    @ManyToOne
    private Tipomarcacao realizado;

    public Marcacao() {
    }

    public Marcacao(BigDecimal idmarcacao) {
        this.idmarcacao = idmarcacao;
    }
    
    public Marcacao(BigDecimal idmarcacao, String data, String horario, Cliente idcliente, Funcionario idfuncionario) {
        this.idmarcacao = idmarcacao;
        this.data = data;
        this.horario = horario;
        this.idcliente = idcliente;
        this.idfuncionario = idfuncionario;
    }
    
    public Marcacao(BigDecimal idmarcacao, String data, String horario, Cliente idcliente, Funcionario idfuncionario , Tipomarcacao realizado) {
        this.idmarcacao = idmarcacao;
        this.data = data;
        this.horario = horario;
        this.idcliente = idcliente;
        this.idfuncionario = idfuncionario;
        this.realizado = realizado;
    }
        
    public BigDecimal getIdmarcacao() {
        return idmarcacao;
    }

    public void setIdmarcacao(BigDecimal idmarcacao) {
        this.idmarcacao = idmarcacao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @XmlTransient
    public List<Avaliacao> getAvaliacaoList() {
        return avaliacaoList;
    }

    public void setAvaliacaoList(List<Avaliacao> avaliacaoList) {
        this.avaliacaoList = avaliacaoList;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    public Funcionario getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(Funcionario idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public Tipomarcacao getRealizado() {
        return realizado;
    }

    public void setRealizado(Tipomarcacao realizado) {
        this.realizado = realizado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmarcacao != null ? idmarcacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marcacao)) {
            return false;
        }
        Marcacao other = (Marcacao) object;
        if ((this.idmarcacao == null && other.idmarcacao != null) || (this.idmarcacao != null && !this.idmarcacao.equals(other.idmarcacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAL.Marcacao[ idmarcacao=" + idmarcacao + " ]";
    }
    
}
