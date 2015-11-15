
package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.entities.PedidoDelivery;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@ConversationScoped
public class PedidoDeliveryDetail extends AbstractBean implements Serializable {
    private static final long serialVersionUID = 1L;

    PedidoDelivery pedidoDelivery;

    @Inject
    private Conversation conversation;

    @PostConstruct
    public void init() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    public String cancel() {
        conversation.end();
        return "pedidos_delivery.xhtml?faces-redirect=true";
    }

    public PedidoDelivery getPedidoDelivery() {
        return pedidoDelivery;
    }

    public void setPedidoDelivery(PedidoDelivery pedidoDelivery) {
        this.pedidoDelivery = pedidoDelivery;
    }
}
