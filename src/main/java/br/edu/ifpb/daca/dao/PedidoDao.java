package br.edu.ifpb.daca.dao;

import br.edu.ifpb.daca.entities.Pedido;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 * Classe que se encarrega de persistir a entidade <b>Pedido</b>.
 *
 * @see Pedido;
 */
public class PedidoDao extends DAO implements Persistible<Pedido, Long> {

    public void save(Pedido pedido) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(pedido);
            transaction.commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    public Pedido update(Pedido pedido) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Pedido resultado = pedido;
        try {
            resultado = em.merge(pedido);
            transaction.commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }

        return resultado;
    }

    public void delete(Pedido pedido) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            pedido = em.merge(pedido);
            em.remove(pedido);
            transaction.commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    public Pedido getById(Long id) {
        EntityManager em = getEntityManager();
        Pedido resultado = null;
        try {
            resultado = em.find(Pedido.class, id);
        } catch (PersistenceException pe) {
            pe.printStackTrace();
        } finally {
            em.close();
        }

        return resultado;
    }

    public List<Pedido> getAll() {
        EntityManager em = getEntityManager();
        List<Pedido> resultado = null;
        try {
            TypedQuery<Pedido> query = em.createQuery(
                    "SELECT p FROM Pedido_Entity p", Pedido.class);
            resultado = query.getResultList();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
        } finally {
            em.close();
        }
        return resultado;
    }

}
