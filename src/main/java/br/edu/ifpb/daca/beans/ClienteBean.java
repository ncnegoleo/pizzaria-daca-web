package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.dao.ClienteDao;
import br.edu.ifpb.daca.entities.Cliente;
import br.edu.ifpb.daca.service.ClienteService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean
public class ClienteBean {

    List<Cliente> clientes;

    ClienteService clienteService = new ClienteService(new ClienteDao());

    String nomeFiltro;

    @PostConstruct
    public void init() {
        filtrar();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public String getNomeFiltro() {
        return nomeFiltro;
    }

    public void setNomeFiltro(String nomeFiltro) {
        this.nomeFiltro = nomeFiltro;
    }

    public void filtrar() {
        clientes = clienteService.getByNome(nomeFiltro);
    }

    public void limparFiltro() {
        nomeFiltro = null;
    }

}
