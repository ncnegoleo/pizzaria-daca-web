package br.edu.ifpb.daca.service;

import br.edu.ifpb.daca.dao.MesaDao;
import br.edu.ifpb.daca.entities.Mesa;
import br.edu.ifpb.daca.validation.DacaException;
import br.edu.ifpb.daca.validation.DacaPersistenceException;
import br.edu.ifpb.daca.validation.DacaServiceException;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * 
 * @see Mesa
 */
public class MesaService implements Serializable {
    
     private static final long serialVersionUID = 1L;
    
    @Inject
    private MesaDao mesaDao;

    /**
     * Salva uma nova mesa.
     *
     * @param mesa.
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @Transactional(rollbackOn = {DacaException.class}, value = Transactional.TxType.REQUIRED)
    public void save(Mesa mesa) throws DacaServiceException {
        try {
            mesaDao.save(mesa);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }

    /**
     * Atualiza os dados de uma mesa.
     *
     * @param mesa
     * @return Mesa.
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @Transactional(rollbackOn = {DacaException.class}, value = Transactional.TxType.REQUIRED)
    public Mesa update(Mesa mesa) throws DacaServiceException {
        try {
            return mesaDao.update(mesa);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }

    /**
     * Exclui uma mesa.
     *
     * @param mesa
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @Transactional(rollbackOn = {DacaException.class}, value = Transactional.TxType.REQUIRED)
    public void delete(Mesa mesa) throws DacaServiceException {
        try {
            mesaDao.delete(mesa);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }

    /**
     * Retorna uma mesa cadastrada pelo id;
     *
     * @param id
     * @return Mesa.
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @Transactional(rollbackOn = {DacaException.class}, value = Transactional.TxType.SUPPORTS)
    public Mesa getById(Long id) throws DacaServiceException {
        try {
            return mesaDao.getById(id);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }

    /**
     * Recupera todos os clientes cadastrados.
     *
     * @return List of Cliente
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @Transactional(rollbackOn = {DacaException.class}, value = Transactional.TxType.SUPPORTS)
    public List<Mesa> getAll() throws DacaServiceException {
        try {
            return mesaDao.getAll();
        } catch (DacaPersistenceException ex) {
             throw new DacaServiceException(ex.getMessage(), ex);
        }
    }
}
