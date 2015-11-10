package br.edu.ifpb.daca.service;

import br.edu.ifpb.daca.dao.LancheDao;
import br.edu.ifpb.daca.entities.Lanche;
import br.edu.ifpb.daca.validation.DacaException;
import br.edu.ifpb.daca.validation.DacaPersistenceException;
import br.edu.ifpb.daca.validation.DacaServiceException;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * Classe que controla todas as funções de responsabilidade da entidade
 * <b>Lanche</b>.
 *
 * @see Lanche
 */
public class LancheService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private LancheDao lancheDao;

    /**
     * Salva um novo lanche.
     *
     * @param lanche.
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @Transactional(rollbackOn = {DacaException.class}, value = Transactional.TxType.REQUIRED)
    public void save(Lanche lanche) throws DacaServiceException {
        try {
            lancheDao.save(lanche);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }

    /**
     * Atualiza os daodos de um lanche
     *
     * @param lanche
     * @return Lanche
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @Transactional(rollbackOn = {DacaException.class}, value = Transactional.TxType.REQUIRED)
    public Lanche update(Lanche lanche) throws DacaServiceException {
        try {
            return lancheDao.update(lanche);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }

    /**
     * Exclui um lanche
     *
     * @param lanche
     * @throws DacaServiceException
     */
    @Transactional(rollbackOn = {DacaException.class}, value = Transactional.TxType.REQUIRED)
    public void delete(Lanche lanche) throws DacaServiceException {
        try {
            lancheDao.delete(lanche);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }

    /**
     * Retorna um lanche cadastrado pelo id;
     *
     * @param id
     * @return Lanche.
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @Transactional(rollbackOn = {DacaException.class}, value = Transactional.TxType.SUPPORTS)
    public Lanche getById(Long id) throws DacaServiceException {
        try {
            return lancheDao.getById(id);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }

    /**
     * Recupera todo os lanches cadastrados.
     *
     * @return List of Lanches by type of entity
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @Transactional(rollbackOn = {DacaException.class}, value = Transactional.TxType.SUPPORTS)
    public List<Lanche> getAl() throws DacaServiceException {
        try {
            return lancheDao.getAll();
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }

    /**
     * Recupera todo os lanches cadastrados de um tipo especifico.
     *
     * @param entity
     * @return List of Lanches by type of entity
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @Transactional(rollbackOn = {DacaException.class}, value = Transactional.TxType.SUPPORTS)
    public List<Lanche> getAllBySubClass(Class entity) throws DacaServiceException {
        try {
            return lancheDao.getAllBySubClass(entity);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }
}
