package br.edu.ifpb.daca.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Tamanho_Entity")
@Table(name = "TAMANHO")
public class Tamanho implements Serializable {

    private static final long serialVersionUID = 1L;
    public static boolean DISPONIVEL = true;
    public static boolean INDISPOINVEL = false;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String tamanho;

    @Column(nullable = false)
    private boolean disponivel;

    public Tamanho() {
    }

    public Tamanho(String tamanho, boolean disponivel) {
        this.tamanho = tamanho;
        this.disponivel = disponivel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.tamanho != null ? this.tamanho.hashCode() : 0);
        hash = 31 * hash + (this.disponivel ? 1 : 0);
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
        final Tamanho other = (Tamanho) obj;
        if ((this.tamanho == null) ? (other.tamanho != null)
                : !this.tamanho.equals(other.tamanho)) {
            return false;
        }
        if (this.disponivel != other.disponivel) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tamanho{" + "id=" + id + ", tamanho="
                + tamanho + ", disponivel=" + disponivel + '}';
    }
}
