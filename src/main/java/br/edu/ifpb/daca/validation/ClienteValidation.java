package br.edu.ifpb.daca.validation;

import br.edu.ifpb.daca.entities.Cliente;

public class ClienteValidation {

    public static void valideId(Cliente cliente, String expectedMessageException) {
        if (cliente.getId() == null || cliente.getId() < 1) {
            throw new DifferentArgumentException(expectedMessageException);
        }
    }

    public static void valideNome(Cliente cliente, String expectedMessageException) {
        if (cliente.getNome() == null || cliente.getNome().equals("")) {
            throw new DifferentArgumentException(expectedMessageException);
        }
    }

    public static void valideEndereco(Cliente cliente, String expectedMessageExcepction) {
        if (cliente.getEndereco() == null) {
            throw new DifferentArgumentException(expectedMessageExcepction);
        }
    }

    public static void valideTelefone(Cliente cliente, String expectedMessageExcepction) {
        String regex = "(\\([0-9]{2}\\)){0,1}[0-9]{4}-[0-9]{4}";
        if (cliente.getTelefone() == null || (!cliente.getTelefone().matches(regex))) {
            throw new DifferentArgumentException(expectedMessageExcepction);
        }
    }
}
