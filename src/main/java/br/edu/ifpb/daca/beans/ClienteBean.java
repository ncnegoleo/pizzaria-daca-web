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
    
    List<Cliente> clientes;
    
    @Inject
    private ClienteService clienteService;
    
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
        try {
            clientes = clienteService.findByNome(nomeFiltro);
        } catch (DacaServiceException ex) {
            errorMessageReport(ex.getLocalizedMessage());
        }
    }

    public void limparFiltro() {
        nomeFiltro = null;
    }

}
