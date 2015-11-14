
package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.entities.Lanche;
import br.edu.ifpb.daca.service.LancheService;
import br.edu.ifpb.daca.validation.DacaServiceException;
import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ConversationScoped
public class LancheEdit extends AbstractBean implements Serializable {
    
     private static final long serialVersionUID = 1L;
    
    private Lanche lanche;

    @Inject
    private LancheService lancheService;
    
    @Inject
    private Conversation conversation;

    public void preRenderView() {
        if (lanche == null) {
            lanche = new Lanche();
        }
        if(!FacesContext.getCurrentInstance().isPostback() && conversation.isTransient()) {
            conversation.begin();
        }
    }

    public String saveLanche() { 
        try {
            if (lanche.getId() != null) {
                lancheService.update(lanche);
                successMessageReport("Lanche atualizado com sucesso!");
            } else {
                lancheService.save(lanche);
                successMessageReport("Lanche salvo com sucesso!");
            }
        } catch (DacaServiceException ex) {
            errorMessageReport(ex.getMessage());
            return null;
        }
        
        conversation.end();
        return "lanches.xhtml?faces-redirect=true";
    }

    public boolean isEdicaoLanche() {
        return lanche.getId() != null;
    }

    public Lanche getLanche() {
        return lanche;
    }

    public void setLanche(Lanche lanche) {
        this.lanche = lanche;
    }
}
