package br.edu.ifpb.daca.dao;

import br.edu.ifpb.daca.entities.Lanche;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 * Classe que se encarrega de persistir a entidade <b>Lanche</b>.
 *
 * @see Lanche;
 */
public class LancheDao extends DAO implements Persistible<Lanche, Long> {

    public void save(Lanche lanche) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(lanche);
            transaction.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    public Lanche update(Lanche lanche) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Lanche resultado = lanche;
        try {
            resultado = em.merge(lanche);
            transaction.commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
        return resultado;
    }

    public void delete(Lanche lanche) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            lanche = em.merge(lanche);
            em.remove(lanche);
            transaction.commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    public Lanche getById(Long id) {
        EntityManager em = getEntityManager();
        Lanche resultado = null;
        try {
            resultado = em.find(Lanche.class, id);
        } catch (PersistenceException pe) {
            pe.printStackTrace();
        } finally {
            em.close();
        }

        return resultado;
    }

    public List<Lanche> getAll() {
        EntityManager em = getEntityManager();
        List<Lanche> resultado = null;
        try {
            TypedQuery<Lanche> query = em.createQuery(
                    "SELECT l FROM Lanche_Entity l", Lanche.class);
            resultado = query.getResultList();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
        } finally {
            em.close();
        }
        return resultado;
    }

}
