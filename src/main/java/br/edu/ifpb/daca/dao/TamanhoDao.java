package br.edu.ifpb.daca.dao;

import br.edu.ifpb.daca.entities.Tamanho;
import br.edu.ifpb.daca.validation.DacaPersistenceException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 * Classe que se encarrega de persistir a entidade <b>Tamanho</b>.
 *
 * @see Tamanho;
 */
public class TamanhoDao extends DAO implements Persistible<Tamanho, Long> {

    @Override
    public void save(Tamanho tamanho) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        try {
            em.persist(tamanho);
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "salvar o tamanho", pe);
        }
    }

    @Override
    public Tamanho update(Tamanho tamanho) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        Tamanho resultado = tamanho;
        try {
            resultado = em.merge(tamanho);
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "atualizar o tamanho", pe);
        }
        return resultado;
    }

    @Override
    public void delete(Tamanho tamanho) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        try {
            tamanho = em.merge(tamanho);
            em.remove(tamanho);
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "deletar o tamanho", pe);
        }
    }

    @Override
    public Tamanho getById(Long id) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        Tamanho resultado = null;
        try {
            resultado = em.find(Tamanho.class, id);
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "recuperar o tamanho", pe);
        }

        return resultado;
    }

    @Override
    public List<Tamanho> getAll() throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        List<Tamanho> resultado = null;
        try {
            TypedQuery<Tamanho> query = em.createQuery(
                    "SELECT t FROM Tamanho_Entity t", Tamanho.class);
            resultado = query.getResultList();
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "recuperar os tamanhos", pe);
        }
        return resultado;
    }

}
