package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.entities.Cliente;
import br.edu.ifpb.daca.entities.Endereco;
import br.edu.ifpb.daca.service.ClienteService;
import br.edu.ifpb.daca.validation.DacaServiceException;
import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ConversationScoped
public class ClienteEdit extends AbstractBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Cliente cliente;

    @Inject
    private ClienteService clienteService;
    
    @Inject
    private Conversation conversation;

    public void preRenderView() {
        if (cliente == null) {
            cliente = new Cliente();
            cliente.setTelefone("");
        }
        if(!FacesContext.getCurrentInstance().isPostback() && conversation.isTransient()) {
            conversation.begin();
        }
    }

    public String saveCliente() {
        cliente.setEndereco(new Endereco()); // Provisório até arrumar a parte do endereço
        conversation.end();
        try {
            if (cliente.getId() != null) {
                clienteService.update(cliente);
                successMessageReport("Cliente atualizado com sucesso!");
            } else {
                clienteService.save(cliente);
                successMessageReport("Cliente salvo com sucesso!");
            }
        } catch (DacaServiceException ex) {
            errorMessageReport(ex.getMessage());
            return null;
        }

        return "clientes.xhtml?faces-redirect=true";
    }

    public boolean isEdicaoCliente() {
        return cliente.getId() != null;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
