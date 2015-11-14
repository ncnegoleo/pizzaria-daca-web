package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.entities.PedidoLocal;
import br.edu.ifpb.daca.service.PedidoService;
import br.edu.ifpb.daca.validation.DacaServiceException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class PedidoLocalBean extends AbstractBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<PedidoLocal> pedidos;

    @Inject
    PedidoService pedidoService;

    @PostConstruct
    public void init() {
        setPedidoLocalList();
    }

    public List<PedidoLocal> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoLocal> pedidos) {
        this.pedidos = pedidos;
    }

    private void setPedidoLocalList() {
        try {
            pedidos = pedidoService.getAllgetAllPedidoLocal();
        } catch (DacaServiceException ex) {
            errorMessageReport(ex.getMessage());
        }
    }
}
