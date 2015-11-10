package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.entities.Pizza;
import br.edu.ifpb.daca.entities.Sabor;
import br.edu.ifpb.daca.entities.Tamanho;
import br.edu.ifpb.daca.service.LancheService;
import br.edu.ifpb.daca.service.SaborService;
import br.edu.ifpb.daca.service.TamanhoService;
import br.edu.ifpb.daca.validation.DacaServiceException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ConversationScoped
public class PizzaEdit extends AbstractBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Pizza pizza;
    private List<Sabor> sabores;
    private List<Tamanho> Tamanhos;

    @Inject
    private LancheService lancheService;
    @Inject
    private SaborService SaborService;
    @Inject
    private TamanhoService tamanhoService;
    @Inject
    private Conversation conversation;

    @PostConstruct
    public void init() {
        sabores = new ArrayList<>();
        Tamanhos = new ArrayList<>();
        setSabores();
        setTamanhoList();
    }

    public void preRenderView() {
        if (pizza == null) {
            pizza = new Pizza();
            pizza.setSabores(new ArrayList<>());
            pizza.setTamanho(new Tamanho());
        }
        if (!FacesContext.getCurrentInstance().isPostback() && conversation.isTransient()) {
            conversation.begin();
        }
    }
    
    public String savePizza() {
        try {
            if(pizza.getId() != null) {
                lancheService.update(pizza);
                successMessageReport("Pizza atualizada com sucesso!");
            } else {
                lancheService.save(pizza);
                successMessageReport("Pizza salva com sucesso!");
            }
        } catch (DacaServiceException ex) {
            errorMessageReport(ex.getMessage());
            return null;
        }
        conversation.end();
        return "lanches.xhtml?faces-redirect=true";
    }

    public boolean isEdicaoPizza() {
        return pizza.getId() != null;
    }

    private void setTamanhoList() {
        try {
            Tamanhos = tamanhoService.getAll();
        } catch (DacaServiceException ex) {
            errorMessageReport(ex.getMessage());
        }
    }

    private void setSabores() {
        try {
            sabores = SaborService.getAll();
        } catch (DacaServiceException ex) {
            errorMessageReport(ex.getMessage());
        }
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public List<Tamanho> getTamanhos() {
        return Tamanhos;
    }

    public List<Sabor> getSabores() {
        return sabores;
    }
}
