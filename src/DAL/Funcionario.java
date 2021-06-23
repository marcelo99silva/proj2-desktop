/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "FUNCIONARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f")
    , @NamedQuery(name = "Funcionario.findByIdfuncionario", query = "SELECT f FROM Funcionario f WHERE f.idfuncionario = :idfuncionario")
    , @NamedQuery(name = "Funcionario.findByNome", query = "SELECT f FROM Funcionario f WHERE f.nome = :nome")
    , @NamedQuery(name = "Funcionario.findByNcc", query = "SELECT f FROM Funcionario f WHERE f.ncc = :ncc")
    , @NamedQuery(name = "Funcionario.findByNif", query = "SELECT f FROM Funcionario f WHERE f.nif = :nif")
    , @NamedQuery(name = "Funcionario.findByDataNascimento", query = "SELECT f FROM Funcionario f WHERE f.dataNascimento = :dataNascimento")
    , @NamedQuery(name = "Funcionario.findByTelemovel", query = "SELECT f FROM Funcionario f WHERE f.telemovel = :telemovel")
    , @NamedQuery(name = "Funcionario.findByRua", query = "SELECT f FROM Funcionario f WHERE f.rua = :rua")
    , @NamedQuery(name = "Funcionario.findByEmail", query = "SELECT f FROM Funcionario f WHERE f.email = :email")
    , @NamedQuery(name = "Funcionario.findByPassword", query = "SELECT f FROM Funcionario f WHERE f.password = :password")})
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDFUNCIONARIO")
    private BigDecimal idfuncionario;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "NCC")
    private BigInteger ncc;
    @Column(name = "NIF")
    private BigInteger nif;
    @Column(name = "DATA_NASCIMENTO")
    private String dataNascimento;
    @Column(name = "TELEMOVEL")
    private BigInteger telemovel;
    @Column(name = "RUA")
    private String rua;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;
    @JoinColumn(name = "TIPOFUNCIONARIO", referencedColumnName = "ID_TIPOFUNCIONARIO")
    @ManyToOne
    private Tipofuncionario tipofuncionario;
    @OneToMany(mappedBy = "idfuncionario")
    private List<Horario> horarioList;
    @OneToMany(mappedBy = "idfuncionario")
    private List<Aula> aulaList;
    @OneToMany(mappedBy = "idfuncionario")
    private List<Marcacao> marcacaoList;

    public Funcionario() {
    }

    public Funcionario(BigDecimal idfuncionario) {
        this.idfuncionario = idfuncionario;
    }
    
    public Funcionario(BigDecimal idfuncionario, String nome, BigInteger telemovel, BigInteger nif, BigInteger ncc, String rua, String email, String dataNascimento) {
        this.idfuncionario = idfuncionario;
        this.nome = nome;
        this.telemovel = telemovel;
        this.nif = nif;
        this.ncc = ncc;
        this.rua = rua;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public BigDecimal getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(BigDecimal idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigInteger getNcc() {
        return ncc;
    }

    public void setNcc(BigInteger ncc) {
        this.ncc = ncc;
    }

    public BigInteger getNif() {
        return nif;
    }

    public void setNif(BigInteger nif) {
        this.nif = nif;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public BigInteger getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(BigInteger telemovel) {
        this.telemovel = telemovel;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Tipofuncionario getTipofuncionario() {
        return tipofuncionario;
    }

    public void setTipofuncionario(Tipofuncionario tipofuncionario) {
        this.tipofuncionario = tipofuncionario;
    }

    @XmlTransient
    public List<Horario> getHorarioList() {
        return horarioList;
    }

    public void setHorarioList(List<Horario> horarioList) {
        this.horarioList = horarioList;
    }

    @XmlTransient
    public List<Aula> getAulaList() {
        return aulaList;
    }

    public void setAulaList(List<Aula> aulaList) {
        this.aulaList = aulaList;
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
        hash += (idfuncionario != null ? idfuncionario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionario)) {
            return false;
        }
        Funcionario other = (Funcionario) object;
        if ((this.idfuncionario == null && other.idfuncionario != null) || (this.idfuncionario != null && !this.idfuncionario.equals(other.idfuncionario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAL.Funcionario[ idfuncionario=" + idfuncionario + " ]";
    }
    
}
