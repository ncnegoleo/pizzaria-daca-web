package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.entities.Sabor;
import br.edu.ifpb.daca.service.SaborService;
import br.edu.ifpb.daca.validation.DacaServiceException;
import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ConversationScoped
public class SaborEdit extends AbstractBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Sabor sabor;
    
    @Inject
    private SaborService saborService;
    
    @Inject
    private Conversation conversation;
    
    public void preRenderView() {
        if(sabor == null) {
            sabor = new  Sabor();
        }
        if(!FacesContext.getCurrentInstance().isPostback() && conversation.isTransient()) {
            conversation.begin();
        }
    }
    
    
    public String saveSabor() {
        try {
            if(sabor.getId() != null) {
                saborService.update(sabor);
                successMessageReport("Sabor atualizado com sucesso!");
            } else {
                saborService.save(sabor);
                successMessageReport("Sabor salvo com sucesso!");
            }
        } catch (DacaServiceException ex) {
            errorMessageReport(ex.getMessage());
            return null;
        }
        conversation.end();
        return "sabores.xhtml?faces-redirect=true";
    }
    
    public boolean isEdicaoSabor() {
        return sabor.getId() != null;
    }

    public Sabor getSabor() {
        return sabor;
    }

    public void setSabor(Sabor sabor) {
        this.sabor = sabor;
    }
}
