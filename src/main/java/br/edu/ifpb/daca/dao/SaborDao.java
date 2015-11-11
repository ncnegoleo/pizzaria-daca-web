package br.edu.ifpb.daca.dao;

import br.edu.ifpb.daca.entities.Pizza;
import br.edu.ifpb.daca.entities.Sabor;
import br.edu.ifpb.daca.validation.DacaPersistenceException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 * Classe que se encarrega de persistir a entidade <b>Sabor</b>.
 *
 * @see Sabor;
 */
public class SaborDao extends DAO implements Persistible<Sabor, Long> {

    @Override
    public void save(Sabor sabor) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        try {
            em.persist(sabor);
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "salvar o sabor", pe);
        }
    }

    @Override
    public Sabor update(Sabor sabor) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        Sabor resultado = sabor;
        try {
            resultado = em.merge(sabor);
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "atualizar o sabor", pe);
        }
        return resultado;
    }

    @Override
    public void delete(Sabor sabor) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        try {
            sabor = em.merge(sabor);
            em.remove(sabor);
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "deletar o sabor", pe);
        }
    }

    @Override
    public Sabor getById(Long id) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        Sabor resultado = null;
        try {
            resultado = em.find(Sabor.class, id);
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "recuperar o sabor", pe);
        }

        return resultado;
    }

    @Override
    public List<Sabor> getAll() throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        List<Sabor> resultado = null;
        try {
            TypedQuery<Sabor> query = em.createQuery(
                    "SELECT s FROM Sabor_Entity s", Sabor.class);
            resultado = query.getResultList();
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "recuperar os sabores", pe);
        }
        return resultado;
    }

    public List<Sabor> getAllDisponivel() throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        List<Sabor> resultado = null;
        try {
            TypedQuery<Sabor> query = em.createQuery("SELECT s FROM Sabor_Entity s"
                    + " WHERE s.disponivel = TRUE", Sabor.class);
            resultado = query.getResultList();
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "recuperar os sabores", pe);
        }
        return resultado;
    }

    public List<Sabor> getAllDisponivelExcept(Long id) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        List<Sabor> resultado = null;
        try {
            TypedQuery<Sabor> query = em.createQuery("SELECT s FROM Sabor_Entity s"
                    + " WHERE s.disponivel = TRUE and s.id <> :id", Sabor.class);
            query.setParameter("id", id);
            resultado = query.getResultList();
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "recuperar os sabores", pe);
        }
        return resultado;
    }
    
    public boolean isInPizzaAssociation(Sabor sabor) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
            List<Pizza> resultado = null;
            try {
                TypedQuery<Pizza> query = em.createQuery(
                        "SELECT p FROM Pizza_Entity p WHERE :sabor MEMBER OF p.sabores", Pizza.class);
                query.setParameter("sabor", sabor);
                resultado = query.getResultList();
                return resultado.size() > 0;
            } catch (PersistenceException pe) {
                throw new DacaPersistenceException("Ocorreu algum problema em "
                        + "recuperar os lanches", pe);
            }
    }
}
