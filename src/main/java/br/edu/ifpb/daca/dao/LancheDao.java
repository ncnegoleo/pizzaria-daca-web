package br.edu.ifpb.daca.dao;

import br.edu.ifpb.daca.entities.Lanche;
import br.edu.ifpb.daca.validation.DacaPersistenceException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 * Classe que se encarrega de persistir a entidade <b>Lanche</b>.
 *
 * @see Lanche;
 */
public class LancheDao extends DAO implements Persistible<Lanche, Long> {

    @Override
    public void save(Lanche lanche) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        try {
            em.persist(lanche);
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "salvar o lanche", pe);
        }
    }

    @Override
    public Lanche update(Lanche lanche) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        Lanche resultado = lanche;
        try {
            resultado = em.merge(lanche);
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "atualizar o lanche", pe);
        }
        return resultado;
    }

    @Override
    public void delete(Lanche lanche) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        try {
            lanche = em.merge(lanche);
            em.remove(lanche);
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "deletar o lanche", pe);
        }
    }

    @Override
    public Lanche getById(Long id) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        Lanche resultado = null;
        try {
            resultado = em.find(Lanche.class, id);
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "recuperar o lanche", pe);
        }

        return resultado;
    }

    @Override
    public List<Lanche> getAll() throws DacaPersistenceException {
        {
            EntityManager em = getEntityManager();
            List<Lanche> resultado = null;
            try {
                TypedQuery<Lanche> query = em.createQuery(
                        "SELECT l FROM Lanche_Entity l", Lanche.class);
                resultado = query.getResultList();
            } catch (PersistenceException pe) {
                throw new DacaPersistenceException("Ocorreu algum problema em "
                        + "recuperar os lanches", pe);
            }
            return resultado;
        }
    }

    public List<Lanche> getAllBySubClass(Class entity) throws DacaPersistenceException {
        {
            EntityManager em = getEntityManager();
            List<Lanche> resultado = null;
            try {
                TypedQuery<Lanche> query = em.createQuery(
                        "SELECT l FROM Lanche_Entity l where TYPE(l) = :entity", Lanche.class);
                query.setParameter("entity", entity);
                resultado = query.getResultList();
            } catch (PersistenceException pe) {
                throw new DacaPersistenceException("Ocorreu algum problema em "
                        + "recuperar os lanches", pe);
            }
            return resultado;
        }
    }
}
