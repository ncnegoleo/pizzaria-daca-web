package br.edu.ifpb.daca.dao;

import br.edu.ifpb.daca.entities.Cliente;
import br.edu.ifpb.daca.validation.DacaPersistenceException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 * Classe que se encarrega de persistir a entidade <b>Cliente</b>.
 *
 * @see Cliente;
 */
public class ClienteDao extends DAO implements Persistible<Cliente, Long>,
        Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public void save(Cliente cliente) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        try {
            em.persist(cliente);
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "salvar o cliente", pe);
        }
    }

    @Override
    public Cliente update(Cliente cliente) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        Cliente resultado = cliente;
        try {
            resultado = em.merge(cliente);
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "atualizar o cliente", pe);
        }
        return resultado;
    }

    @Override
    public void delete(Cliente cliente) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        try {
            cliente = em.merge(cliente);
            em.remove(cliente);
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "deletar o cliente", pe);
        }
    }

    @Override
    public Cliente getById(Long id) throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        Cliente resultado = null;
        try {
            resultado = em.find(Cliente.class, id);
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "recuperar  o cliente", pe);
        }

        return resultado;
    }

    @Override
    public List<Cliente> getAll() throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        List<Cliente> resultado = null;
        try {
            TypedQuery<Cliente> query = em.createQuery(
                    "SELECT c FROM Cliente_Entity c", Cliente.class);
            resultado = query.getResultList();
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "recuperar os clientes", pe);
        }
        return resultado;
    }

    public List<Cliente> findClienteByNome(String nome)
            throws DacaPersistenceException {
        EntityManager em = getEntityManager();
        List<Cliente> resultado = null;
        if (nome == null) {
            nome = "";
        }
        try {
            TypedQuery<Cliente> query = em.createQuery(""
                    + "SELECT c FROM Cliente_Entity c WHERE c.nome LIKE "
                    + ":nome", Cliente.class);
            query.setParameter("nome", "%" + nome + "%");
            resultado = query.getResultList();
        } catch (PersistenceException pe) {
            throw new DacaPersistenceException("Ocorreu algum problema em "
                    + "recuperar os clientes", pe);
        }
        return resultado;
    }
}
