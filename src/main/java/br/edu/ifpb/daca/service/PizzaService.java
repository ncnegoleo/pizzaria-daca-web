package br.edu.ifpb.daca.service;

import br.edu.ifpb.daca.entities.Pizza;
import br.edu.ifpb.daca.validation.LancheValidation;

public class PizzaService extends LancheService {

    public static final String SABOR_MSG_EXCEPT
            = "Os sabores da pizza não pode vazios ou nulo";
    public static final String TAMANHO_MSG_EXCEP
            = "O tamanho da pizza não pode ser nulo";

    public PizzaService() {
    }

    public void save(Pizza pizza) {

        LancheValidation.valideSabores(pizza, SABOR_MSG_EXCEPT);

        LancheValidation.valideTamanho(pizza, TAMANHO_MSG_EXCEP);

        super.save(pizza);
    }

    public Pizza update(Pizza pizza) {

        LancheValidation.valideSabores(pizza, SABOR_MSG_EXCEPT);

        LancheValidation.valideTamanho(pizza, TAMANHO_MSG_EXCEP);

        return (Pizza) super.update(pizza);
    }
}
