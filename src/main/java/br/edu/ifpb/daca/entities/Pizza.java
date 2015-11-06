package br.edu.ifpb.daca.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Pizza_Entity")
@Table(name = "PIZZA")
@DiscriminatorValue("P")
public class Pizza extends Lanche implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PIZZA_SABOR",
            joinColumns = @JoinColumn(name = "PIZZA_FK"),
            inverseJoinColumns = @JoinColumn(name = "SABOR_FK"))
    private List<Sabor> sabores;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TAMANHO_FK")
    private Tamanho tamanho;

    public Pizza() {

    }

    public Pizza(String descricao, double valorUnitario, Tamanho tamanho, Sabor... sbrs) {
        super(descricao, valorUnitario);
        this.tamanho = tamanho;
        addSabores(sbrs);
    }

    public List<Sabor> getSabores() {
        return sabores;
    }

    public void setSabores(List<Sabor> sabores) {
        this.sabores = sabores;
    }

    public void addSabores(Sabor... sbrs) {
        if (this.sabores == null) {
            this.sabores = new ArrayList<Sabor>();
        }

        for (Sabor sabor : sbrs) {
            sabores.add(sabor);
        }
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.sabores != null ? this.sabores.hashCode() : 0);
        hash = 97 * hash + (this.tamanho != null ? this.tamanho.hashCode() : 0);
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
        final Pizza other = (Pizza) obj;
        if (this.sabores != other.sabores
                && (this.sabores == null || !this.sabores.equals(other.sabores))) {
            return false;
        }
        if (this.tamanho != other.tamanho
                && (this.tamanho == null || !this.tamanho.equals(other.tamanho))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " Pizza{" + "sabores=" + sabores + ", tamanho=" + tamanho + '}';
    }
    
    
}
