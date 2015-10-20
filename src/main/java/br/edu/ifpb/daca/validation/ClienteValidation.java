package br.edu.ifpb.daca.validation;

import br.edu.ifpb.daca.entities.Cliente;
import br.edu.ifpb.daca.validation.exception.DifferentArgumentException;
import static br.edu.ifpb.daca.validation.msgs.ClienteExceptMsgs.*;

public class ClienteValidation {
   
    
    public static void valideId(Cliente cliente) {
        if (cliente.getId() == null || cliente.getId() < 1) {
            throw new DifferentArgumentException(ID_MSG_EXCEPT.getValor());
        }
    }

    public static void valideNome(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().equals("")) {
            throw new DifferentArgumentException(NOME_MSG_EXCEPT.getValor());
        }
    }

    public static void valideEndereco(Cliente cliente) {
        if (cliente.getEndereco() == null) {
            throw new DifferentArgumentException(ENDERECO_MSG_EXCEPT.getValor());
        }
    }

    public static void valideTelefone(Cliente cliente) {
        String regex = "(\\([0-9]{2}\\)){0,1}[0-9]{4}-[0-9]{4}";
        if (cliente.getTelefone() == null || (!cliente.getTelefone().matches(regex))) {
            throw new DifferentArgumentException(TELEFONE_MSG_EXCEPT.getValor());
        }
    }
}
