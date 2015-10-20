package br.edu.ifpb.daca.validation;

import br.edu.ifpb.daca.validation.exception.DifferentArgumentException;
import br.edu.ifpb.daca.entities.Lanche;
import br.edu.ifpb.daca.entities.Pizza;

public class LancheValidation {

    public static void valideId(Lanche lanche, String expectedMessageException) {
        if (lanche.getId() == null || lanche.getId() < 1) {
            throw new DifferentArgumentException(expectedMessageException);
        }
    }

    public static void valideDescricao(Lanche lanche, String expectedMessageException) {
        if (lanche.getDescricao() == null || lanche.getDescricao().equals("")) {
            throw new DifferentArgumentException(expectedMessageException);
        }
    }

    public static void valideValorUnitario(Lanche lanche, String expectedMessageException) {
        if (lanche.getValorUnitario() <= 0.0) {
            throw new DifferentArgumentException(expectedMessageException);
        }
    }

    public static void valideSabores(Pizza pizza, String expectedMessageException) {
        if (pizza.getSabores() == null || pizza.getSabores().size() <= 0
                || pizza.getSabores().size() > 2) {
            throw new DifferentArgumentException(expectedMessageException);
        }
    }

    public static void valideTamanho(Pizza pizza, String expectedMessageException) {
        if (pizza.getTamanho() == null) {
            throw new DifferentArgumentException(expectedMessageException);
        }
    }
}
