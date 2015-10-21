package br.edu.ifpb.daca.service;

import br.edu.ifpb.daca.entities.Pizza;

public class PizzaService extends LancheService {

    public PizzaService() {
    }

    public void save(Pizza pizza) {
        super.save(pizza);
    }

    public Pizza update(Pizza pizza) {
        return (Pizza) super.update(pizza);
    }
}
