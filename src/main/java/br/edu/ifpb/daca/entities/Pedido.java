package br.edu.ifpb.daca.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Pedido_Entity")
@Table(name = "PEDIDO")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    @Column(name = "valor_total", nullable = false)
    protected double valorTotal;

    @Column(name = "data_hora", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dataHora;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    protected List<ItensPedido> itensPedidos;

    public Pedido() {
        itensPedidos = new ArrayList<ItensPedido>();
    }

    public Pedido(Date dataHora) {
        itensPedidos = new ArrayList<ItensPedido>();
        this.dataHora = dataHora;
    }

    public Pedido(Date dataHora, List<ItensPedido> itensPedidos) {
        this.dataHora = dataHora;
        this.itensPedidos = itensPedidos;
        this.valorTotal = requestValortotal();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public List<ItensPedido> getItensPedidos() {
        return itensPedidos;
    }

    public void setItensPedidos(List<ItensPedido> itensPedidos) {
        this.itensPedidos = itensPedidos;
        this.valorTotal = requestValortotal();
    }

    public void addLancheItensPedios(Lanche lanche, int quantidade, String obs) {
        ItensPedido it = new ItensPedido(lanche, quantidade);
        it.setPedido(this);
        it.setSubtotal(it.getLanche().getValorUnitario() * quantidade);
        it.setObservacao(obs);
        this.itensPedidos.add(it);
        this.valorTotal = requestValortotal();
    }

    public void addLancheItensPedios(ItensPedido it) {
        this.itensPedidos.add(it);
    }

    private double requestValortotal() {
        double vlrTotal = 0;
        for (ItensPedido it : itensPedidos) {
            vlrTotal += it.getSubtotal();
        }

        return vlrTotal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.valorTotal) ^ 
                (Double.doubleToLongBits(this.valorTotal) >>> 32));
        hash = 37 * hash + Objects.hashCode(this.dataHora);
        hash = 37 * hash + Objects.hashCode(this.itensPedidos);
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
        final Pedido other = (Pedido) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (Double.doubleToLongBits(this.valorTotal) != 
                Double.doubleToLongBits(other.valorTotal)) {
            return false;
        }
        if (!Objects.equals(this.dataHora, other.dataHora)) {
            return false;
        }
        if (!Objects.equals(this.itensPedidos, other.itensPedidos)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pedido [id=" + id + ", valorTotal=" + valorTotal
                + ", dataHora=" + dataHora + ", itensPedidos=" + itensPedidos
                + "]";
    }
}
