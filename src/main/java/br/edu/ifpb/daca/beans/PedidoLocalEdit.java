package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.entities.ItensPedido;
import br.edu.ifpb.daca.entities.Lanche;
import br.edu.ifpb.daca.entities.Mesa;
import br.edu.ifpb.daca.entities.PedidoLocal;
import br.edu.ifpb.daca.service.LancheService;
import br.edu.ifpb.daca.service.MesaService;
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
public class PedidoLocalEdit extends AbstractBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private PedidoLocal pedidoLocal;
    private ItensPedido ItensPedido;
    private List<Lanche> lanches;
    private List<Mesa> mesas;
    private Lanche selectedLanche;
    private Integer quantidade;

    @Inject
    private PedidoService pedidoService;

    @Inject
    private LancheService lancheService;

    @Inject
    private MesaService mesaService;

    @Inject
    private Conversation conversation;

    @PostConstruct
    public void init() {
        lanches = new ArrayList<>();
        mesas = new ArrayList<>();
        selectedLanche = new Lanche();
        quantidade = 1;
        setLancheList();
        setMesaList();
    }

    public void preRenderView() {
        if (pedidoLocal == null) {
            pedidoLocal = new PedidoLocal();
            pedidoLocal.setItensPedidos(new ArrayList<>());
            pedidoLocal.setDataHora(new Date());
        }
        if (!FacesContext.getCurrentInstance().isPostback()
                && conversation.isTransient()) {
            conversation.begin();
        }
    }

    public String savePedido() {
        if (pedidoLocal.getItensPedidos().isEmpty()) {
            warningMessageReport("Adicione pelo menos um item ao pedido!");
        } else {
            try {
                if (pedidoLocal.getId() != null) {
                    pedidoService.update(pedidoLocal);
                    successMessageReport("Pedido atualizado com sucesso!");
                } else {
                    pedidoService.save(pedidoLocal);
                    successMessageReport("Pedido salvo com sucesso!");
                }
            } catch (DacaServiceException ex) {
                errorMessageReport(ex.getMessage());
                return null;
            }
            conversation.end();
            return "pedidos_locais.xhtml?faces-redirect=true";
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

            Double ValorTotal = pedidoLocal.getValorTotal();
            Double somaValor = 0.0;
            somaValor += (quantidade * selectedLanche.getValorUnitario());

            System.out.println("Valor total" + somaValor + ValorTotal);

            pedidoLocal.setValorTotal(somaValor + ValorTotal);
            pedidoLocal.getItensPedidos().add(ItensPedido);
        }
    }

    public void removeLanche(ItensPedido itensPedido) {
        pedidoLocal.getItensPedidos().remove(itensPedido);
        pedidoLocal.setValorTotal(pedidoLocal.getValorTotal()
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

    private void setMesaList() {
        try {
            mesas = mesaService.getAll();
        } catch (DacaServiceException ex) {
            errorMessageReport(ex.getMessage());
        }
    }

    public boolean isEdicaoPedido() {
        return pedidoLocal.getId() != null;
    }

    // ------//
    public PedidoLocal getPedidoLocal() {
        return pedidoLocal;
    }

    public void setPedidoLocal(PedidoLocal pedidoLocal) {
        this.pedidoLocal = pedidoLocal;
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

    public List<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(List<Mesa> mesas) {
        this.mesas = mesas;
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
