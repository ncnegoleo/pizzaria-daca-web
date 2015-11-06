package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.entities.Lanche;
import br.edu.ifpb.daca.entities.Pizza;
import br.edu.ifpb.daca.service.LancheService;
import br.edu.ifpb.daca.validation.DacaServiceException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class LancheBean extends AbstractBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Lanche> lanches;

    @Inject
    private LancheService lancheService;

    @PostConstruct
    public void init() {
        setPizzaList();
    }

    public void printList() {
        System.out.println("pizzas");
        lanches.stream().forEach((pizza) -> {
            System.out.println(pizza);
            if (pizza instanceof Pizza) {System.out.println("É poxa");}
        });

    }

    public List<Lanche> getLanches() {
        return lanches;
    }

    private void setPizzaList() {
        try {
            lanches = lancheService.getAl();
        } catch (DacaServiceException ex) {
            errorMessageReport(ex.getMessage());
        }
    }
    
    public boolean isPizza(Lanche lanche) {
        return lanche instanceof Pizza;
    }
}
