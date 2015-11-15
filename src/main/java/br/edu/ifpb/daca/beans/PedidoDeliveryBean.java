package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.entities.PedidoDelivery;
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
public class PedidoDeliveryBean extends AbstractBean implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private List<PedidoDelivery> pedidos;

    @Inject
    PedidoService pedidoService;

    @PostConstruct
    public void init() {
        setPedidoDeliveryList();
    }

    public List<PedidoDelivery> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoDelivery> pedidos) {
        this.pedidos = pedidos;
    }

    private void setPedidoDeliveryList() {
        try {
            pedidos = pedidoService.getAllPedidoDelivery();
        } catch (DacaServiceException ex) {
            errorMessageReport(ex.getMessage());
        }
    }
}
