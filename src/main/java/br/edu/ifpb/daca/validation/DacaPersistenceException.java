package br.edu.ifpb.daca.validation;

/**
 *
 * @author leonardo
 */
public class DacaPersistenceException extends DacaException {

    public DacaPersistenceException(String mensagem) {
        super(mensagem);
    }

    public DacaPersistenceException(String mensagem, Throwable throwable) {
        super(mensagem, throwable);
    }
    
}
