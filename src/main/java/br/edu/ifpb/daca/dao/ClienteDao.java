package br.edu.ifpb.daca.dao;

import br.edu.ifpb.daca.entities.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 * Classe que se encarrega de persistir a entidade <b>Cliente</b>.
 *
 * @see Cliente;
 */
public class ClienteDao extends DAO implements Persistible<Cliente, Long> {

    public void save(Cliente cliente) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(cliente);
            transaction.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    public Cliente update(Cliente cliente) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Cliente resultado = cliente;
        try {
            resultado = em.merge(cliente);
            transaction.commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
        return resultado;
    }

    public void delete(Cliente cliente) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            cliente = em.merge(cliente);
            em.remove(cliente);
            transaction.commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    public Cliente getById(Long id) {
        EntityManager em = getEntityManager();
        Cliente resultado = null;
        try {
            resultado = em.find(Cliente.class, id);
        } catch (PersistenceException pe) {
            pe.printStackTrace();
        } finally {
            em.close();
        }

        return resultado;
    }

    public List<Cliente> getAll() {
        EntityManager em = getEntityManager();
        List<Cliente> resultado = null;
        try {
            TypedQuery<Cliente> query = em.createQuery(
                    "SELECT c FROM Cliente_Entity c", Cliente.class);
            resultado = query.getResultList();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
        } finally {
            em.close();
        }
        return resultado;
    }

    public List<Cliente> findClienteByNome(String nome) {
        EntityManager em = getEntityManager();
        List<Cliente> resultado = null;
        if (nome == null) {
            nome = "";
        }
        try {
            TypedQuery<Cliente> query = em.createQuery(""
                    + "SELECT c FROM Cliente_Entity c WHERE c.nome LIKE :nome", Cliente.class);
            query.setParameter("nome", "%" + nome + "%");
            resultado = query.getResultList();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
        } finally {
            em.close();
        }
        return resultado;
    }
}
