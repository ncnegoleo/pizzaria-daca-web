package br.edu.ifpb.daca.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "IntensPedido_Entity")
@Table(name = "ITENS_PEDIDO")
public class ItensPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private double subtotal;

    private String observacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_fk")
    private Pedido pedido;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lanche_fk", nullable = false)
    private Lanche lanche;

    public ItensPedido() {

    }

    public ItensPedido(Lanche lanche, int quantidade) {
        this.lanche = lanche;
        this.quantidade = quantidade;
        this.subtotal = this.lanche.getValorUnitario() * this.quantidade;
    }

    public ItensPedido(Lanche lanche, int quantidade, String observacao) {
        this.lanche = lanche;
        this.quantidade = quantidade;
        this.observacao = observacao;
        this.subtotal = this.lanche.getValorUnitario() * this.quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Lanche getLanche() {
        return lanche;
    }

    public void setLanche(Lanche lanche) {
        this.lanche = lanche;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + this.quantidade;
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.subtotal) ^ 
                (Double.doubleToLongBits(this.subtotal) >>> 32));
        hash = 43 * hash + Objects.hashCode(this.observacao);
        hash = 43 * hash + Objects.hashCode(this.pedido);
        hash = 43 * hash + Objects.hashCode(this.lanche);
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
        final ItensPedido other = (ItensPedido) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.quantidade != other.quantidade) {
            return false;
        }
        if (Double.doubleToLongBits(this.subtotal) != 
                Double.doubleToLongBits(other.subtotal)) {
            return false;
        }
        if (!Objects.equals(this.observacao, other.observacao)) {
            return false;
        }
        if (!Objects.equals(this.pedido, other.pedido)) {
            return false;
        }
        if (!Objects.equals(this.lanche, other.lanche)) {
            return false;
        }
        return true;
    }

    
    
    
    @Override
    public String toString() {
        return "ItensPedido [id=" + id + ", quantidade=" + quantidade
                + ", subtotal=" + subtotal + ", observacao=" + observacao
                + ", lanche=" + lanche + "]";
    }
}
