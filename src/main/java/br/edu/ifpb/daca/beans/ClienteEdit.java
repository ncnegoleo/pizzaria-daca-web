package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.dao.ClienteDao;
import br.edu.ifpb.daca.entities.Cliente;
import br.edu.ifpb.daca.entities.Endereco;
import br.edu.ifpb.daca.util.Messages;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean
public class ClienteEdit {

    private Cliente cliente;
    // Ver como implantar atravez do service de maneira na qual as validações se encaixem
    private final ClienteDao clienteDao = new ClienteDao();

    public void preRenderView() {
        if (cliente == null) {
            cliente = new Cliente();
            cliente.setTelefone("");
        }
    }

    public String saveCliente() {
        cliente.setEndereco(new Endereco()); // Provisório até arrumar a parte do endereço
        if (cliente.getId() != null) {
            clienteDao.update(cliente);
        } else {
            clienteDao.save(cliente);
        }

        Messages.addFlashMessage("Cliente '" + cliente.getNome() + "' saved");

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
