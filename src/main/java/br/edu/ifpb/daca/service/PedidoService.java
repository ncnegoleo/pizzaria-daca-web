package br.edu.ifpb.daca.service;

import br.edu.ifpb.daca.dao.PedidoDao;
import br.edu.ifpb.daca.entities.Pedido;
import br.edu.ifpb.daca.entities.PedidoDelivery;
import br.edu.ifpb.daca.entities.PedidoLocal;
import br.edu.ifpb.daca.validation.DacaException;
import br.edu.ifpb.daca.validation.DacaPersistenceException;
import br.edu.ifpb.daca.validation.DacaServiceException;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * Classe que controla todas as funções de responsabilidade da entidade
 * <b>Pedido</b>.
 *
 * @see Pedido
 */
public class PedidoService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private PedidoDao pedidoDao;

    /**
     * Salva um novo pedido.
     *
     * @param pedido.
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @Transactional(rollbackOn = {DacaException.class}, value = Transactional.TxType.REQUIRED)
    public void save(Pedido pedido) throws DacaServiceException {
        try {
            pedidoDao.save(pedido);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }

    /**
     * Atualiza os daodos de um pedido
     *
     * @param pedido
     * @return Pedido
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @Transactional(rollbackOn = {DacaException.class}, value = Transactional.TxType.REQUIRED)
    public Pedido update(Pedido pedido) throws DacaServiceException {
        try {
            return pedidoDao.update(pedido);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }

    /**
     * Exclui um pedido
     *
     * @param pedido
     * @throws DacaServiceException
     */
    @Transactional(rollbackOn = {DacaException.class}, value = Transactional.TxType.REQUIRED)
    public void delete(Pedido pedido) throws DacaServiceException {
        try {
            pedidoDao.delete(pedido);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }

    /**
     * Retorna um pedido cadastrado pelo id;
     *
     * @param id
     * @return Pedido.
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @Transactional(rollbackOn = {DacaException.class}, value = Transactional.TxType.SUPPORTS)
    public Pedido getById(Long id) throws DacaServiceException {
        try {
            return pedidoDao.getById(id);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }

    /**
     * Recupera todo os pedidos cadastrados.
     *
     * @return List of Pedidos by type of entity
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @Transactional(rollbackOn = {DacaException.class}, value = Transactional.TxType.SUPPORTS)
    public List<Pedido> getAll() throws DacaServiceException {
        try {
            return pedidoDao.getAll();
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }

    /**
     * Recupera todo os pedidos cadastrados de um tipo especifico.
     *
     * @param entity
     * @return List of Pedidos by type of entity
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @Transactional(rollbackOn = {DacaException.class}, value = Transactional.TxType.SUPPORTS)
    public List<Pedido> getAllBySubClass(Class entity) throws DacaServiceException {
        try {
            return pedidoDao.getAllBySubClass(entity);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }

    /**
     * Recupera todo os pedidos locais cadastrados de um tipo especifico.
     *
     * @return List of PedidoLocal by type of entity
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @Transactional(rollbackOn = {DacaException.class}, value = Transactional.TxType.SUPPORTS)
    public List<PedidoLocal> getAllPedidoLocal() throws DacaServiceException {
        try {
            return pedidoDao.getAllPedidoLocal();
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }

    /**
     * Recupera todo os pedidos delivery cadastrados de um tipo especifico.
     *
     * @return List of PedidoLocal by type of entity
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @Transactional(rollbackOn = {DacaException.class}, value = Transactional.TxType.SUPPORTS)
    public List<PedidoDelivery> getAllPedidoDelivery() throws DacaServiceException {
        try {
            return pedidoDao.getAllPedidoDelivery();
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }
}
