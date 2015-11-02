package br.edu.ifpb.daca.service;

import br.edu.ifpb.daca.dao.TamanhoDao;
import br.edu.ifpb.daca.entities.Tamanho;
import br.edu.ifpb.daca.util.annotations.TransactionalCDI;
import br.edu.ifpb.daca.validation.DacaPersistenceException;
import br.edu.ifpb.daca.validation.DacaServiceException;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 * Classe que controla todas as fun��es de responsabilidade a entidade
 * <b>Tamanho</b>.
 *
 * @see Tamanho
 */
public class TamanhoService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private TamanhoDao tamanhoDao;

    /**
     * Salva um novo tamanho de pizza.
     *
     * @param tamanho.
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @TransactionalCDI
    public void save(Tamanho tamanho) throws DacaServiceException {
        try {
            tamanhoDao.save(tamanho);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }
    
    /**
     * Atualiza os dados de um tamnaho de pizza.
     *
     * @param tamanho.
     * @return Tamanho.
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @TransactionalCDI
    public Tamanho update(Tamanho tamanho) throws DacaServiceException {
        try {
            return tamanhoDao.update(tamanho);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }
    
    /**
     * Exclui um tamnaho de pizza.
     *
     * @param tamanho.
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @TransactionalCDI
    public void delete(Tamanho tamanho) throws DacaServiceException {
        try {
            tamanhoDao.delete(tamanho);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }
    
    /**
     * Retorna um tamanho cadastrado pelo id;
     *
     * @param id
     * @return Tamanho.
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    public Tamanho getById(Long id) throws DacaServiceException {
        try {
            return tamanhoDao.getById(id);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }
    
    /**
     * Recupera todos os tamanhos cadastrados.
     *
     * @return List of Tamanhos
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    public List<Tamanho> getAll() throws DacaServiceException {
        try {
            return tamanhoDao.getAll();
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }
}
