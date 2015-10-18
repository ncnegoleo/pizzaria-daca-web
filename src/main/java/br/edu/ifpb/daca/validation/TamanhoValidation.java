package br.edu.ifpb.daca.validation;

import br.edu.ifpb.daca.entities.Tamanho;

public class TamanhoValidation {

    public static void validId(Tamanho tamanho, String expectedMessageException) {
        if (tamanho.getId() == null || tamanho.getId() < 1) {
            throw new DifferentArgumentException(expectedMessageException);
        }
    }

    public static void valideNomeTamanho(Tamanho tamanho, String expectedMessageException) {
        if (tamanho.getTamanho() == null || tamanho.getTamanho().equals("")) {
            throw new DifferentArgumentException(expectedMessageException);
        }
    }
}
