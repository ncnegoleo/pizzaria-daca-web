package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.entities.PedidoLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ConversationScoped
public class PedidoLocalDetail extends AbstractBean implements Serializable{
    
    private static final long serialVersionUID = 1L;

    PedidoLocal pedidoLocal;

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
        return "pedidos_locais.xhtml?faces-redirect=true";
    }

    public PedidoLocal getPedidoLocal() {
        return pedidoLocal;
    }

    public void setPedidoLocal(PedidoLocal pedidoLocal) {
        this.pedidoLocal = pedidoLocal;
    }
}
