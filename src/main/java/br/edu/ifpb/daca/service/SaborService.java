package br.edu.ifpb.daca.service;

import br.edu.ifpb.daca.dao.SaborDao;
import br.edu.ifpb.daca.entities.Sabor;
import br.edu.ifpb.daca.util.annotations.TransactionalCDI;
import br.edu.ifpb.daca.validation.DacaPersistenceException;
import br.edu.ifpb.daca.validation.DacaServiceException;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 * Classe que controla todas as funções de responsabilidade a entidade
 * <b>Sabor</b>.
 *
 * @see Sabor
 */
public class SaborService implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private SaborDao saborDao;
    
    /**
     * Salva um novo sabor.
     *
     * @param sabor.
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @TransactionalCDI
    public void save(Sabor sabor) throws DacaServiceException {
        try {
            saborDao.save(sabor);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }
    
    /**
     * Atualiza os dados de um sabor.
     *
     * @param sabor.
     * @return Sabor.
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @TransactionalCDI
    public Sabor update(Sabor sabor) throws DacaServiceException {
        try {
            return saborDao.update(sabor);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }
    
    /**
     * Exclui um sabor.
     *
     * @param sabor.
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @TransactionalCDI
    public void delete(Sabor sabor) throws DacaServiceException {
        try {
            saborDao.delete(sabor);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }
    
    /**
     * Retorna um sabor cadastrado pelo id;
     *
     * @param id
     * @return Sabor.
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    public Sabor getById(Long id) throws DacaServiceException {
        try {
            return saborDao.getById(id);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }
    
    /**
     * Recupera todos os sabores cadastrados.
     *
     * @return List of Sabores
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    public List<Sabor> getAll() throws DacaServiceException {
        try {
            return saborDao.getAll();
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }
}
