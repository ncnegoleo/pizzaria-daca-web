package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.dao.ClienteDao;
import br.edu.ifpb.daca.entities.Cliente;
import br.edu.ifpb.daca.service.ClienteService;
import br.edu.ifpb.daca.util.Messages;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean
public class ClienteDelete {
    
    Cliente cliente;
    ClienteService clienteService = new ClienteService(new ClienteDao());
    
    public String deleteCliente() {
        clienteService.delete(cliente);
        Messages.addFlashMessage("Cliente '" + cliente.getNome()+ "' deleted");
        
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
