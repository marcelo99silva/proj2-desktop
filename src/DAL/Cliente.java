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
@Table(name = "CLIENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
    , @NamedQuery(name = "Cliente.findByIdcliente", query = "SELECT c FROM Cliente c WHERE c.idcliente = :idcliente")
    , @NamedQuery(name = "Cliente.findByNome", query = "SELECT c FROM Cliente c WHERE c.nome = :nome")
    , @NamedQuery(name = "Cliente.findByNomeLike", query = "SELECT c FROM Cliente c WHERE c.nome LIKE CONCAT(:nome,'%')")
    , @NamedQuery(name = "Cliente.findByNcc", query = "SELECT c FROM Cliente c WHERE c.ncc = :ncc")
    , @NamedQuery(name = "Cliente.findByDataNascimento", query = "SELECT c FROM Cliente c WHERE c.dataNascimento = :dataNascimento")
    , @NamedQuery(name = "Cliente.findByTelemovel", query = "SELECT c FROM Cliente c WHERE c.telemovel = :telemovel")
    , @NamedQuery(name = "Cliente.findByRua", query = "SELECT c FROM Cliente c WHERE c.rua = :rua")
    , @NamedQuery(name = "Cliente.findByEmail", query = "SELECT c FROM Cliente c WHERE c.email = :email")
    , @NamedQuery(name = "Cliente.findByPassword", query = "SELECT c FROM Cliente c WHERE c.password = :password")
    , @NamedQuery(name = "Cliente.findBySexo", query = "SELECT c FROM Cliente c WHERE c.sexo = :sexo")
    , @NamedQuery(name = "Cliente.findByUsername", query = "SELECT c FROM Cliente c WHERE c.username = :username")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDCLIENTE")
    private BigDecimal idcliente;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "NCC")
    private BigInteger ncc;
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
    @Column(name = "SEXO")
    private String sexo;
    @Column(name = "USERNAME")
    private String username;
    @OneToMany(mappedBy = "idcliente")
    private List<ClienteAula> clienteAulaList;
    @JoinColumn(name = "IDPLANO", referencedColumnName = "IDPLANO")
    @ManyToOne
    private Plano idplano;
    @OneToMany(mappedBy = "idcliente")
    private List<Pagamento> pagamentoList;
    @OneToMany(mappedBy = "idcliente")
    private List<Marcacao> marcacaoList;

    public Cliente() {
    }

    public Cliente(BigDecimal idcliente) {
        this.idcliente = idcliente;
    }
    
    public Cliente(BigDecimal idcliente, String nome, String username, BigInteger telemovel, BigInteger ncc, String rua, String email, String dataNascimento, String sexo, Plano idplano) {
        this.idcliente = idcliente;
        this.nome = nome;
        this.username = username;
        this.telemovel = telemovel;
        this.ncc = ncc;
        this.rua = rua;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.idplano = idplano;
    }

    public BigDecimal getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(BigDecimal idcliente) {
        this.idcliente = idcliente;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @XmlTransient
    public List<ClienteAula> getClienteAulaList() {
        return clienteAulaList;
    }

    public void setClienteAulaList(List<ClienteAula> clienteAulaList) {
        this.clienteAulaList = clienteAulaList;
    }

    public Plano getIdplano() {
        return idplano;
    }

    public void setIdplano(Plano idplano) {
        this.idplano = idplano;
    }

    @XmlTransient
    public List<Pagamento> getPagamentoList() {
        return pagamentoList;
    }

    public void setPagamentoList(List<Pagamento> pagamentoList) {
        this.pagamentoList = pagamentoList;
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
        hash += (idcliente != null ? idcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idcliente == null && other.idcliente != null) || (this.idcliente != null && !this.idcliente.equals(other.idcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAL.Cliente[ idcliente=" + idcliente + " ]";
    }
    
}
