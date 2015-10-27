package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.entities.Cliente;
import br.edu.ifpb.daca.service.ClienteService;
import br.edu.ifpb.daca.validation.DacaServiceException;
import java.io.Serializable;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ClienteDelete extends AbstractBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    Cliente cliente;

    @Inject
    private ClienteService clienteService;

    public String deleteCliente() {
        try {
            clienteService.delete(cliente);
            successMessageReport("Cliente removido com sucesso!");
        } catch (DacaServiceException ex) {
            errorMessageReport(ex.getMessage());
        }

        return "clientes.xhtml?faces-redirect=true";
    }

    public String cancel() {
        return "clientes.xhtml?faces-redirect=true";
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
