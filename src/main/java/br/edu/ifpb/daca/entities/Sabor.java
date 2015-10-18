package br.edu.ifpb.daca.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Sabor_Entity")
@Table(name = "SABOR")
public class Sabor implements Serializable {

    private static final long serialVersionUID = 1L;
    public static boolean DISPONIVEL = true;
    public static boolean INDISPOINVEL = false;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(nullable = false)
    private String sabor;

    @Column(nullable = false)
    private boolean disponivel;

    public Sabor() {

    }

    public Sabor(String sabor, boolean disponivel) {
        this.sabor = sabor;
        this.disponivel = disponivel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
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
        hash = 19 * hash + (this.sabor != null ? this.sabor.hashCode() : 0);
        hash = 19 * hash + (this.disponivel ? 1 : 0);
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
        final Sabor other = (Sabor) obj;
        if ((this.sabor == null) ? (other.sabor != null)
                : !this.sabor.equals(other.sabor)) {
            return false;
        }
        if (this.disponivel != other.disponivel) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sabor{" + "id=" + id + ", sabor="
                + sabor + ", disponivel=" + disponivel + '}';
    }

}
