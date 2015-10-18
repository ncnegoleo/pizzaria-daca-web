package br.edu.ifpb.daca.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Endereco_Entity")
@Table(name = "ENDERECO")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String rua;

    private String bairro;

    private String complemento;

    private int numero;

    private String cep;

    private String observacao;

    public Endereco() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.rua);
        hash = 23 * hash + Objects.hashCode(this.bairro);
        hash = 23 * hash + Objects.hashCode(this.complemento);
        hash = 23 * hash + this.numero;
        hash = 23 * hash + Objects.hashCode(this.cep);
        hash = 23 * hash + Objects.hashCode(this.observacao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Endereco other = (Endereco) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.rua, other.rua)) {
            return false;
        }
        if (!Objects.equals(this.bairro, other.bairro)) {
            return false;
        }
        if (!Objects.equals(this.complemento, other.complemento)) {
            return false;
        }
        if (this.numero != other.numero) {
            return false;
        }
        if (!Objects.equals(this.cep, other.cep)) {
            return false;
        }
        if (!Objects.equals(this.observacao, other.observacao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Endereco [id=" + id + ", rua=" + rua + ", bairro=" + bairro
                + ", complemento=" + complemento + ", numero=" + numero
                + ", cep=" + cep + ", observacao=" + observacao + "]";
    }
}
