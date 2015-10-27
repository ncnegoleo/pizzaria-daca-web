
package br.edu.ifpb.daca.validation;

/**
 *
 * @author leonardo
 */
public class DacaServiceException extends DacaException {

    public DacaServiceException(String mensagem) {
        super(mensagem);
    }

    public DacaServiceException(String mensagem, Throwable throwable) {
        super(mensagem, throwable);
    }
}
