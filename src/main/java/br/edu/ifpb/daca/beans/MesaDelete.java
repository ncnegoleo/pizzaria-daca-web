package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.entities.Mesa;
import br.edu.ifpb.daca.service.MesaService;
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
public class MesaDelete extends AbstractBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Mesa mesa;

    @Inject
    @RequestScoped
    private MesaService mesaService;

    @Inject
    private Conversation conversation;

    @PostConstruct
    public void init() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    public String deleteMesa() {
        
        try {
            conversation.end();
            mesaService.delete(mesa);
            successMessageReport("Mesa deletada com sucesso!");
        } catch (DacaServiceException ex) {
            errorMessageReport(ex.getMessage());
        }
        return "mesas.xhtml?faces-redirect=true";
    }


    public String cancel() {
        conversation.end();
        return "mesas.xhtml?faces-redirect=true";
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

}

    
