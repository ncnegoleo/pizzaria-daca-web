package br.edu.ifpb.daca.dao;

import br.edu.ifpb.daca.entities.Pedido;
import br.edu.ifpb.daca.entities.PedidoDelivery;
import br.edu.ifpb.daca.entities.PedidoLocal;
import br.edu.ifpb.daca.validation.DacaPersistenceException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 * Classe que se encarrega de persistir a entidade <b>Pedido</b>.
 *
 * @see Pedido;
 */
public class PedidoDao extends DAO implements Persistible<Pedido, Long> {

    @Override
    public void save(Pedido pedido) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        try {
            em.persist(pedido);
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "salvar o pedido", pe);
        }
    }

    @Override
    public Pedido update(Pedido pedido) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        Pedido resultado = pedido;
        try {
            resultado = em.merge(pedido);
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "atualizar o pedido", pe);
        }

        return resultado;
    }

    @Override
    public void delete(Pedido pedido) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        try {
            pedido = em.merge(pedido);
            em.remove(pedido);
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "delete o pedido", pe);
        }
    }

    @Override
    public Pedido getById(Long id) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        Pedido resultado = null;
        try {
            resultado = em.find(Pedido.class, id);
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "recuperar o pedido", pe);
        }

        return resultado;
    }

    @Override
    public List<Pedido> getAll() throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        List<Pedido> resultado = null;
        try {
            TypedQuery<Pedido> query = em.createQuery(
                    "SELECT p FROM Pedido_Entity p", Pedido.class);
            resultado = query.getResultList();
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "recuperar os pedidos", pe);
        }
        return resultado;
    }

    public List<Pedido> getAllBySubClass(Class entity) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        List<Pedido> resultado = null;
        try {
            TypedQuery<Pedido> query = em.createQuery(
                    "SELECT p FROM Pedido_Entity p where TYPE(p) = :entity", Pedido.class);
            query.setParameter("entity", entity);
            resultado = query.getResultList();
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "recuperar os pedidos", pe);
        }
        return resultado;
    }

    public List<PedidoLocal> getAllPedidoLocal() throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        List<PedidoLocal> resultado = null;
        try {
            TypedQuery<PedidoLocal> query = em.createQuery(
                    "SELECT pl FROM PedidoLocal_Entity pl", PedidoLocal.class);
            resultado = query.getResultList();
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "recuperar os pedidos", pe);
        }
        return resultado;
    }

    public List<PedidoDelivery> getAllPedidoDelivery() throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        List<PedidoDelivery> resultado = null;
        try {
            TypedQuery<PedidoDelivery> query = em.createQuery(
                    "SELECT pd FROM PedidoDelivery_Entity pd", PedidoDelivery.class);
            resultado = query.getResultList();
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "recuperar os pedidos", pe);
        }
        return resultado;
    }

}
