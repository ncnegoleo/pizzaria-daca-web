package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.entities.Pedido;
import br.edu.ifpb.daca.entities.PedidoLocal;
import br.edu.ifpb.daca.service.PedidoService;
import br.edu.ifpb.daca.validation.DacaServiceException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ConversationScoped
public class PedidoDelete extends AbstractBean implements Serializable {

    private Pedido pedido;

    @Inject
    @RequestScoped
    private PedidoService pedidoService;

    @Inject
    private Conversation conversation;

    @PostConstruct
    public void init() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    public String deletePedido() {
        try {
            conversation.end();
            pedidoService.delete(pedido);
            successMessageReport("Pedido removido com sucesso!");
        } catch (DacaServiceException ex) {
            errorMessageReport(ex.getMessage());
        }
        if(pedido instanceof PedidoLocal) {
            return "pedidos_locais.xhtml?faces-redirect=true";
        } else {
            return "pedidos_delivery.xhtml?faces-redirect=true";
        }
    }

    public String cancel() {
        conversation.end();
         if(pedido instanceof PedidoLocal) {
            return "pedidos_locais.xhtml?faces-redirect=true";
        } else {
            return "pedidos_delivery.xhtml?faces-redirect=true";
        }
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}
