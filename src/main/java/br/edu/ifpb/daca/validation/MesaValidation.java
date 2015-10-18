package br.edu.ifpb.daca.validation;

import br.edu.ifpb.daca.entities.Mesa;

public class MesaValidation {

    public static void valideId(Mesa mesa, String expectedMessageException) {
        if (mesa.getId() == null || mesa.getId() < 1) {
            throw new DifferentArgumentException(expectedMessageException);
        }
    }

    public static void valideDescricao(Mesa mesa, String expectedMessageException) {
        if (mesa.getDescricao() == null || mesa.getDescricao().equals("")) {
            throw new DifferentArgumentException(expectedMessageException);
        }
    }
}
