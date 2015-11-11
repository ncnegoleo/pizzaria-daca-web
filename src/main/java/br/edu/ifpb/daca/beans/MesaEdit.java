package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.entities.Mesa;
import br.edu.ifpb.daca.service.MesaService;
import br.edu.ifpb.daca.validation.DacaServiceException;
import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ConversationScoped
public class MesaEdit extends AbstractBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Mesa mesa;
    
    @Inject
    private MesaService mesaService;
    
    @Inject
    private Conversation conversation;
    
    public void preRenderView() {
        if(mesa == null) {
            mesa = new Mesa();
        }
        if(!FacesContext.getCurrentInstance().isPostback() && conversation.isTransient()) {
            conversation.begin();
        }
    }
    
    public String saveMesa() {
        try {
            if(mesa.getId() != null) {
                mesaService.update(mesa);
                successMessageReport("Tamanho atualizado com sucesso!");
            } else {
                mesaService.save(mesa);
                successMessageReport("Tamanho salvo com sucesso!");
            }
        } catch (DacaServiceException ex) {
            errorMessageReport(ex.getMessage());
            return null;
        }
        conversation.end();
        return "mesas.xhtml?faces-redirect=true";
    }
    
    public boolean isEdicaoTamanho() {
        return mesa.getId() != null;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
}
