package br.edu.ifpb.daca.service;

import br.edu.ifpb.daca.dao.ClienteDao;
import br.edu.ifpb.daca.entities.Cliente;
import br.edu.ifpb.daca.entities.Endereco;
import br.edu.ifpb.daca.util.annotations.TransactionalCDI;
import br.edu.ifpb.daca.validation.DacaPersistenceException;
import br.edu.ifpb.daca.validation.DacaServiceException;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 * Classe que controla todas as funções de responsabilidade a entidade
 * <b>Cliente</b>.
 *
 * @see Cliente
 * @see Endereco
 */
public class ClienteService implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private ClienteDao clienteDao;

    /**
     * Salva um novo cliente.
     *
     * @param cliente.
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @TransactionalCDI
    public void save(Cliente cliente) throws DacaServiceException {
        try {
            clienteDao.save(cliente);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }

    /**
     * Atualiza os dados de um cliente.
     *
     * @param cliente .
     * @return Cliente.
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @TransactionalCDI
    public Cliente update(Cliente cliente) throws DacaServiceException {
        try {
            return clienteDao.update(cliente);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }

    /**
     * Exclui um cliente.
     *
     * @param cliente
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @TransactionalCDI
    public void delete(Cliente cliente) throws DacaServiceException {
        try {
            clienteDao.delete(cliente);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }

    /**
     * Retorna um cliente cadastrado pelo id;
     *
     * @param id
     * @return Cliente.
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    public Cliente getById(Long id) throws DacaServiceException {
        try {
            return clienteDao.getById(id);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }

    /**
     * Recupera todos os clientes cadastrados.
     *
     * @return List of Cliente
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    public List<Cliente> getAll() throws DacaServiceException {
        try {
            return clienteDao.getAll();
        } catch (DacaPersistenceException ex) {
             throw new DacaServiceException(ex.getMessage(), ex);
        }
    }
    
    /**
     * Recupera os clinetes cadastrados pelo nome.
     * 
     * @param nome do cliente
     * @return Lista de Cliente
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    public List<Cliente> findByNome(String nome) throws DacaServiceException {
        try {
            return clienteDao.findClienteByNome(nome);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }
}
