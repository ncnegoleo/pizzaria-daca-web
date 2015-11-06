package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.entities.Cliente;
import br.edu.ifpb.daca.service.ClienteService;
import br.edu.ifpb.daca.validation.DacaServiceException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ClienteBean extends AbstractBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private List<Cliente> clientes;
    private Cliente selectedCliente;
    
    @Inject
    private ClienteService clienteService;

    @PostConstruct
    public void init() {
        setClientList();
    }

    public Cliente getSelectedCliente() {
        return selectedCliente;
    }

    public void setSelectedCliente(Cliente selectedCliente) {
        this.selectedCliente = selectedCliente;
    }
    
    public void setClientList() {
        try {
            clientes = clienteService.getAll();
        } catch (DacaServiceException ex) {
            errorMessageReport(ex.getMessage());
        }
    }
}
