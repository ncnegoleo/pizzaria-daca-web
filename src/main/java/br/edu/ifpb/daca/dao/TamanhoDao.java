package br.edu.ifpb.daca.dao;

import br.edu.ifpb.daca.entities.Tamanho;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 * Classe que se encarrega de persistir a entidade <b>Tamanho</b>.
 *
 * @see Tamanho;
 */
public class TamanhoDao extends DAO implements Persistible<Tamanho, Long>{

    public void save(Tamanho tamanho) {
         EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(tamanho);
            transaction.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    public Tamanho update(Tamanho tamanho) {
          EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Tamanho resultado = tamanho;
        try {
            resultado = em.merge(tamanho);
            transaction.commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
        return resultado;
    }

    public void delete(Tamanho tamanho) {
          EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            tamanho = em.merge(tamanho);
            em.remove(tamanho);
            transaction.commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    public Tamanho getById(Long id) {
         EntityManager em = getEntityManager();
        Tamanho resultado = null;
        try {
            resultado = em.find(Tamanho.class, id);
        } catch (PersistenceException pe) {
            pe.printStackTrace();
        } finally {
            em.close();
        }

        return resultado;
    }

    public List<Tamanho> getAll() {
                EntityManager em = getEntityManager();
        List<Tamanho> resultado = null;
        try {
            TypedQuery<Tamanho> query = em.createQuery(
                    "SELECT t FROM Tamanho_Entity t", Tamanho.class);
            resultado = query.getResultList();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
        } finally {
            em.close();
        }
        return resultado;
    }
    
}
