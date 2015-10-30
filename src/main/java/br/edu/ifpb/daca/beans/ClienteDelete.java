package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.entities.Cliente;
import br.edu.ifpb.daca.service.ClienteService;
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
public class ClienteDelete extends AbstractBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    Cliente cliente;

    @Inject @RequestScoped
    private ClienteService clienteService;

    @Inject
    private Conversation conversation;
    
    @PostConstruct
    public void init() {
        if(conversation.isTransient()) {
            conversation.begin();
        }
    }
    
    public String deleteCliente() {
        try {
            conversation.end();
            clienteService.delete(cliente);
            successMessageReport("Cliente removido com sucesso!");
        } catch (DacaServiceException ex) {
            errorMessageReport(ex.getMessage());
        }

        return "clientes.xhtml?faces-redirect=true";
    }

    public String cancel() {
        conversation.end();
        return "clientes.xhtml?faces-redirect=true";
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
