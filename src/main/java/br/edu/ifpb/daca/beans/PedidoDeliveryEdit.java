package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.entities.Cliente;
import br.edu.ifpb.daca.entities.ItensPedido;
import br.edu.ifpb.daca.entities.Lanche;
import br.edu.ifpb.daca.entities.PedidoDelivery;
import br.edu.ifpb.daca.service.ClienteService;
import br.edu.ifpb.daca.service.LancheService;
import br.edu.ifpb.daca.service.PedidoService;
import br.edu.ifpb.daca.validation.DacaServiceException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ConversationScoped
public class PedidoDeliveryEdit extends AbstractBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private PedidoDelivery pedidoDelivery;
    private ItensPedido ItensPedido;
    private List<Lanche> lanches;
    private List<Cliente> clientes;
    private Lanche selectedLanche;
    private Integer quantidade;

    @Inject
    private PedidoService pedidoService;

    @Inject
    private LancheService lancheService;

    @Inject
    private ClienteService clienteService;

    @Inject
    private Conversation conversation;

    @PostConstruct
    public void init() {
        lanches = new ArrayList<>();
        clientes = new ArrayList<>();
        selectedLanche = new Lanche();
        quantidade = 1;
        setLancheList();
        setClienteList();
    }

    public void preRenderView() {
        if (pedidoDelivery == null) {
            pedidoDelivery = new PedidoDelivery();
            pedidoDelivery.setItensPedidos(new ArrayList<>());
            pedidoDelivery.setDataHora(new Date());
        }
        if (!FacesContext.getCurrentInstance().isPostback()
                && conversation.isTransient()) {
            conversation.begin();
        }
    }

    public String savePedido() {
        if (pedidoDelivery.getItensPedidos().isEmpty()) {
            warningMessageReport("Adicione pelo menos um item ao pedido!");
        } else {
            try {
                if (pedidoDelivery.getId() != null) {
                    pedidoService.update(pedidoDelivery);
                    successMessageReport("Pedido atualizado com sucesso!");
                } else {
                    pedidoService.save(pedidoDelivery);
                    successMessageReport("Pedido salvo com sucesso!");
                }
            } catch (DacaServiceException ex) {
                errorMessageReport(ex.getMessage());
                return null;
            }
            conversation.end();
            return "pedidos_delivery.xhtml?faces-redirect=true";
        }
        return "";
    }

    public void addLanche() {

        if (quantidade == null || quantidade <= 0) {
            errorMessageReport("Não é possivel adicionar lanches com a quantidade de 0 ou menos!");

        } else if (quantidade > 100) {
            warningMessageReport("É possivel que a quantidade de lanches esteja errada para um único item!");
        } else {
            ItensPedido = new ItensPedido(selectedLanche, quantidade);

            Double ValorTotal = pedidoDelivery.getValorTotal();
            Double somaValor = 0.0;
            somaValor += (quantidade * selectedLanche.getValorUnitario());

            System.out.println("Valor total" + somaValor + ValorTotal);

            pedidoDelivery.setValorTotal(somaValor + ValorTotal);
            pedidoDelivery.getItensPedidos().add(ItensPedido);
        }
    }

    public void removeLanche(ItensPedido itensPedido) {
        pedidoDelivery.getItensPedidos().remove(itensPedido);
        pedidoDelivery.setValorTotal(pedidoDelivery.getValorTotal()
                - (itensPedido.getLanche().getValorUnitario() * itensPedido.getQuantidade()));
    }

    // ------//
    private void setLancheList() {
        try {
            lanches = lancheService.getAll();
        } catch (DacaServiceException ex) {
            errorMessageReport(ex.getMessage());
        }
    }

    private void setClienteList() {
        try {
            clientes = clienteService.getAll();
        } catch (DacaServiceException ex) {
            errorMessageReport(ex.getMessage());
        }
    }

    public boolean isEdicaoPedido() {
        return pedidoDelivery.getId() != null;
    }

    // ------//
    public PedidoDelivery getPedidoDelivery() {
        return pedidoDelivery;
    }

    public void setPedidoDelivery(PedidoDelivery pedidoDelivery) {
        this.pedidoDelivery = pedidoDelivery;
    }

    public ItensPedido getItensPedido() {
        return ItensPedido;
    }

    public void setItensPedido(ItensPedido ItensPedido) {
        this.ItensPedido = ItensPedido;
    }

    public List<Lanche> getLanches() {
        return lanches;
    }

    public void setLanches(List<Lanche> lanches) {
        this.lanches = lanches;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setMesas(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Lanche getSelectedLanche() {
        return selectedLanche;
    }

    public void setSelectedLanche(Lanche selectedLanche) {
        this.selectedLanche = selectedLanche;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
