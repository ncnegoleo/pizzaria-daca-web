package br.edu.ifpb.daca.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity(name = "Lanche_Entity")
@Table(name = "LANCHE")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DISC", discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue("L")
public class Lanche implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    @Column(nullable = false)
    protected String descricao;

    @Column(nullable = false)
    protected double valorUnitario;

    public Lanche() {

    }

    public Lanche(String descricao, double valorUnitario) {
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((descricao == null) ? 0 : descricao.hashCode());
        long temp;
        temp = Double.doubleToLongBits(valorUnitario);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Lanche other = (Lanche) obj;
        if (descricao == null) {
            if (other.descricao != null) {
                return false;
            }
        } else if (!descricao.equals(other.descricao)) {
            return false;
        }
        if (Double.doubleToLongBits(valorUnitario) != Double
                .doubleToLongBits(other.valorUnitario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lanche{" + "id=" + id + ", descricao=" + descricao
                + ", valorUnitario=" + valorUnitario + '}';
    }

}
