package br.edu.ifpb.daca.dao;

import br.edu.ifpb.daca.entities.Sabor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 * Classe que se encarrega de persistir a entidade <b>Sabor</b>.
 *
 * @see Sabor;
 */
public class SaborDao extends DAO implements Persistible<Sabor, Long> {

    public void save(Sabor sabor) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(sabor);
            transaction.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    public Sabor update(Sabor sabor) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Sabor resultado = sabor;
        try {
            resultado = em.merge(sabor);
            transaction.commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
        return resultado;
    }

    public void delete(Sabor sabor) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            sabor = em.merge(sabor);
            em.remove(sabor);
            transaction.commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    public Sabor getById(Long id) {
        EntityManager em = getEntityManager();
        Sabor resultado = null;
        try {
            resultado = em.find(Sabor.class, id);
        } catch (PersistenceException pe) {
            pe.printStackTrace();
        } finally {
            em.close();
        }

        return resultado;
    }

    public List<Sabor> getAll() {
        EntityManager em = getEntityManager();
        List<Sabor> resultado = null;
        try {
            TypedQuery<Sabor> query = em.createQuery(
                    "SELECT s FROM Sabor_Entity s", Sabor.class);
            resultado = query.getResultList();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
        } finally {
            em.close();
        }
        return resultado;
    }
}
