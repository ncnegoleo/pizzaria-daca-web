package br.edu.ifpb.daca.dao;

import br.edu.ifpb.daca.entities.Mesa;
import br.edu.ifpb.daca.validation.DacaPersistenceException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 * Classe que se encarrega de persistir a entidade <b>Mesa</b>.
 *
 * @see Mesa;
 */
public class MesaDao extends DAO implements Persistible<Mesa, Long> {

    @Override
    public void save(Mesa mesa) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        try {
            em.persist(mesa);
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "salvar a mesa", pe);
        }
    }

    @Override
    public Mesa update(Mesa mesa) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        Mesa resultado = mesa;
        try {
            resultado = em.merge(mesa);
        } catch (PersistenceException pe) {
           throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "atualizar a mesa", pe);
        }
        return resultado;
    }

    @Override
    public void delete(Mesa mesa) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        try {
            mesa = em.merge(mesa);
            em.remove(mesa);
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "deletar a mesa", pe);
        }
    }

    @Override
    public Mesa getById(Long id) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        Mesa resultado = null;
        try {
            resultado = em.find(Mesa.class, id);
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "reculperar a mesa", pe);
        }

        return resultado;
    }

    @Override
    public List<Mesa> getAll() throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        List<Mesa> resultado = null;
        try {
            TypedQuery<Mesa> query = em.createQuery(
                    "SELECT m FROM Mesa_Entity m", Mesa.class);
            resultado = query.getResultList();
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "recuperar as mesas", pe);
        }
        return resultado;
    }
}
