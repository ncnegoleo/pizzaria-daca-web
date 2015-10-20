package br.edu.ifpb.daca.validation;

import br.edu.ifpb.daca.validation.exception.DifferentArgumentException;
import br.edu.ifpb.daca.entities.Sabor;

public class SaborValidation {

    public static void validId(Sabor sabor, String expectedMessageException) {
        if (sabor.getId() == null || sabor.getId() < 1) {
            throw new DifferentArgumentException(expectedMessageException);
        }
    }

    public static void valideNomeSabor(Sabor sabor, String expectedMessageException) {
        if (sabor.getSabor() == null || sabor.getSabor().equals("")) {
            throw new DifferentArgumentException(expectedMessageException);
        }
    }

}
