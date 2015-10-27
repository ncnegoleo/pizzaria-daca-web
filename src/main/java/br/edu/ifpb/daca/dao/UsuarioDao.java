package br.edu.ifpb.daca.dao;

import br.edu.ifpb.daca.entities.Usuario;
import br.edu.ifpb.daca.validation.DacaPersistenceException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 *
 * @author leonardo
 */
public class UsuarioDao extends DAO implements Persistible<Usuario, Long> {

    @Override
    public void save(Usuario usuario) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        try {
            em.persist(usuario);
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "salvar o usuário", pe);
        }
    }

    @Override
    public Usuario update(Usuario usuario) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        Usuario resultado = usuario;
        try {
            resultado = em.merge(usuario);
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "atualizar o usuario", pe);
        }
        return resultado;
    }

    @Override
    public void delete(Usuario usuario) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            usuario = em.merge(usuario);
            em.remove(usuario);
            transaction.commit();
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "deletar o usuario", pe);
        }
    }

    @Override
    public Usuario getById(Long id) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        Usuario resultado = null;
        try {
            resultado = em.find(Usuario.class, id);
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "recuperar o usuario", pe);
        }
        return resultado;
    }

    @Override
    public List<Usuario> getAll() throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        List<Usuario> resultado = null;
        try {
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario_Entity u", Usuario.class);
            resultado = query.getResultList();
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "recuperar os usuários", pe);
        }
        return resultado;
    }

}
