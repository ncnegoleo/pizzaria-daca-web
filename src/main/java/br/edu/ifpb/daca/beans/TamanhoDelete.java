package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.entities.Tamanho;
import br.edu.ifpb.daca.service.TamanhoService;
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
public class TamanhoDelete extends AbstractBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Tamanho tamanho;

    @Inject
    @RequestScoped
    private TamanhoService tamanhoService;

    @Inject
    private Conversation conversation;

    @PostConstruct
    public void init() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    public String deleteTamanho() {
        if (!isInAssociation()) {
            try {
                conversation.end();
                tamanhoService.delete(tamanho);
                successMessageReport("Tamnaho deletado com sucesso!");
            } catch (DacaServiceException ex) {
                errorMessageReport(ex.getMessage());
            }
        } else {
            errorMessageReport("Não é possível deletar um "
                    + "tamanho que está associado a uma Pizza!");
        }
        return "tamanhos.xhtml?faces-redirect=true";
    }

    private boolean isInAssociation() {
        try {
            return tamanhoService.isInPizzaAssociation(tamanho);
        } catch (DacaServiceException ex) {
            errorMessageReport(ex.getMessage());
        }
        return false;
    }

    public String cancel() {
        conversation.end();
        return "tamanhos.xhtml?faces-redirect=true";
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }
}
