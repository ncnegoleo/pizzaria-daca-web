package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.entities.Sabor;
import br.edu.ifpb.daca.service.SaborService;
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
public class SaborDelete extends AbstractBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Sabor sabor;

    @Inject
    @RequestScoped
    private SaborService saborService;

    @Inject
    private Conversation conversation;

    @PostConstruct
    public void init() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    public SaborDelete() {

    }

    public String deleteSabor() {
        if (!isInAssociation()) {
            try {
                conversation.end();
                saborService.delete(sabor);
                successMessageReport("Sabor deletado com sucesso!");
            } catch (DacaServiceException ex) {
                errorMessageReport(ex.getMessage());
            }
        } else {
            errorMessageReport("Não é possível deletar um "
                    + "sabor que está associado a uma Pizza!");
        }
        return "sabores.xhtml?faces-redirect=true";
    }

    private boolean isInAssociation() {
        try {
            return saborService.isInPizzaAssociation(sabor);
        } catch (DacaServiceException ex) {
            errorMessageReport(ex.getMessage());
        }
        return false;
    }

    public String cancel() {
        conversation.end();
        return "sabores.xhtml?faces-redirect=true";
    }

    public Sabor getSabor() {
        return sabor;
    }

    public void setSabor(Sabor sabor) {
        this.sabor = sabor;
    }
}
