package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.entities.Tamanho;
import br.edu.ifpb.daca.service.TamanhoService;
import br.edu.ifpb.daca.validation.DacaServiceException;
import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ConversationScoped
public class TamanhoEdit extends AbstractBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Tamanho tamanho;
    
    @Inject
    private TamanhoService tamanhoService;
    
    @Inject
    private Conversation conversation;
    
    public void preRenderView() {
        if(tamanho == null) {
            tamanho = new Tamanho();
            tamanho.setDisponivel(true);
        }
        if(!FacesContext.getCurrentInstance().isPostback() && conversation.isTransient()) {
            conversation.begin();
        }
    }
    
    public String saveTamanho() {
        try {
            if(tamanho.getId() != null) {
                tamanhoService.update(tamanho);
                successMessageReport("Tamanho atualizado com sucesso!");
            } else {
                tamanhoService.save(tamanho);
                successMessageReport("Tamanho salvo com sucesso!");
            }
        } catch (DacaServiceException ex) {
            errorMessageReport(ex.getMessage());
            return null;
        }
        conversation.end();
        return "tamanhos.xhtml?faces-redirect=true";
    }
    
    public boolean isEdicaoTamanho() {
        return tamanho.getId() != null;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }
}
