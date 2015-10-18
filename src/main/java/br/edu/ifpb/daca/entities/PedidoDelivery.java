package br.edu.ifpb.daca.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "PedidoDelivery_Entity")
@Table(name = "PEDIDO_DELIVERY")
public class PedidoDelivery extends Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    private double taxaEntrega;

    @OneToOne(fetch = FetchType.EAGER)
    private Cliente cliente;

    public PedidoDelivery() {

    }

    public PedidoDelivery(Date dataHora, Cliente cliente) {
        super(dataHora);
        this.cliente = cliente;
    }

    public PedidoDelivery(Date dataHora, List<ItensPedido> itensPedidos,
            Cliente cliente) {
        super(dataHora, itensPedidos);
        this.cliente = cliente;
    }

    public double getTaxaEntrega() {
        return taxaEntrega;
    }

    public void setTaxaEntrega(double taxaEntrega) {
        this.taxaEntrega = taxaEntrega;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
        long temp;
        temp = Double.doubleToLongBits(taxaEntrega);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PedidoDelivery other = (PedidoDelivery) obj;
        if (cliente == null) {
            if (other.cliente != null) {
                return false;
            }
        } else if (!cliente.equals(other.cliente)) {
            return false;
        }
        if (Double.doubleToLongBits(taxaEntrega) != Double
                .doubleToLongBits(other.taxaEntrega)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PedidoDelivery [taxaEntrega=" + taxaEntrega + ", cliente="
                + cliente + ", id=" + id + ", valorTotal=" + valorTotal
                + ", dataHora=" + dataHora + ", itensPedidos=" + itensPedidos
                + "]";
    }
}
