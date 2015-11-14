package br.edu.ifpb.daca.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "PedidoLocal_Entity")
@Table(name = "PEDIDO_LOCAL")
@DiscriminatorColumn(name = "DISC", discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue("PL")
public class PedidoLocal extends Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_fk", nullable = false)// mudar para mesa_fk
    private Mesa mesa;

    public PedidoLocal() {

    }

    public PedidoLocal(Date dataHora, Mesa mesa) {
        super(dataHora);
        this.mesa = mesa;
    }

    public PedidoLocal(Date dataHora, List<ItensPedido> itensPedidos, Mesa mesa) {
        super(dataHora, itensPedidos);
        this.mesa = mesa;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.mesa);
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
        final PedidoLocal other = (PedidoLocal) obj;
        if (!Objects.equals(this.mesa, other.mesa)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "PedidoLocal [mesa=" + mesa + ", id=" + id + ", valorTotal="
                + valorTotal + ", dataHora=" + dataHora + ", itensPedidos="
                + itensPedidos + "]";
    }
}
