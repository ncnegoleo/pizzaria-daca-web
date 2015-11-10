package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.entities.Lanche;
import br.edu.ifpb.daca.service.LancheService;
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
public class LancheDelete extends AbstractBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Lanche lanche;

    @Inject @RequestScoped
    private LancheService lancheService;

    @Inject
    private Conversation conversation;
    
    @PostConstruct
    public void init() {
        if(conversation.isTransient()) {
            conversation.begin();
        }
    }
    
    public String deleteLanche() {
        try {
            conversation.end();
            lancheService.delete(lanche);
            successMessageReport("Cliente removido com sucesso!");
        } catch (DacaServiceException ex) {
            errorMessageReport(ex.getMessage());
        }

        return "lanches.xhtml?faces-redirect=true";
    }

    public String cancel() {
        conversation.end();
        return "lanches.xhtml?faces-redirect=true";
    }

    public Lanche getLanche() {
        return lanche;
    }

    public void setLanche(Lanche lanche) {
        this.lanche = lanche;
    }
}
