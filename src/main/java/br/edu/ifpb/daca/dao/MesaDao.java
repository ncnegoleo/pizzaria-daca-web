package br.edu.ifpb.daca.dao;

import br.edu.ifpb.daca.entities.Mesa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 * Classe que se encarrega de persistir a entidade <b>Mesa</b>.
 *
 * @see Mesa;
 */
public class MesaDao extends DAO implements Persistible<Mesa, Long> {

    public void save(Mesa mesa) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(mesa);
            transaction.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    public Mesa update(Mesa mesa) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Mesa resultado = mesa;
        try {
            resultado = em.merge(mesa);
            transaction.commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
        return resultado;
    }

    public void delete(Mesa mesa) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            mesa = em.merge(mesa);
            em.remove(mesa);
            transaction.commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    public Mesa getById(Long id) {
        EntityManager em = getEntityManager();
        Mesa resultado = null;
        try {
            resultado = em.find(Mesa.class, id);
        } catch (PersistenceException pe) {
            pe.printStackTrace();
        } finally {
            em.close();
        }

        return resultado;
    }

    public List<Mesa> getAll() {
        EntityManager em = getEntityManager();
        List<Mesa> resultado = null;
        try {
            TypedQuery<Mesa> query = em.createQuery(
                    "SELECT m FROM Mesa_Entity m", Mesa.class);
            resultado = query.getResultList();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
        } finally {
            em.close();
        }
        return resultado;
    }
}
