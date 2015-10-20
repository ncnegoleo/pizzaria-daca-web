package br.edu.ifpb.daca.service;

import br.edu.ifpb.daca.dao.ClienteDao;
import br.edu.ifpb.daca.entities.Cliente;
import br.edu.ifpb.daca.entities.Endereco;
import static br.edu.ifpb.daca.validation.ClienteValidation.*;
import java.util.List;

/**
 * Classe que controla todas as fun��es de responsabilidade a entidade
 * <b>Cliente<b>.
 *
 * @see Cliente
 * @see Endereco
 */
public class ClienteService {

    ClienteDao clienteDao;

    public ClienteService() {

    }

    public ClienteService(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    /**
     * Recupera o DAO do Cliente
     *
     * @return ClienteDao.
     */
    public ClienteDao getClienteDao() {
        return clienteDao;
    }

    /**
     * Define DAO do Cliente a ser usado
     *
     * @param clienteDao .
     */
    public void setClienteDao(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    /**
     * Salva um novo cliente.
     *
     * @param cliente .
     */
    public void save(Cliente cliente) {
        valideNome(cliente);
        valideEndereco(cliente);
        valideTelefone(cliente);

        clienteDao.save(cliente);
    }

    /**
     * Atualiza os dados de um cliente.
     *
     * @param cliente .
     * @return Cliente.
     */
    public Cliente update(Cliente cliente) {
        valideId(cliente);
        valideNome(cliente);
        valideEndereco(cliente);
        valideTelefone(cliente);

        return clienteDao.update(cliente);
    }

    /**
     * Exclui um cliente.
     *
     * @param cliente
     */
    public void delete(Cliente cliente) {
        valideId(cliente);

        clienteDao.delete(cliente);
    }

    /**
     * Retorna um cliente cadastrado pelo id;
     *
     * @param id
     * @return Cliente.
     */
    public Cliente getById(Long id) {
        Cliente aux = new Cliente();
        aux.setId(id);
        valideId(aux);

        return clienteDao.getById(id);
    }

    /**
     * Atualiza o endere�o de um cliente.
     *
     * @param cliente
     * @param endereco
     * @return Endereco;
     */
    public Endereco updateEndereco(Cliente cliente, Endereco endereco) {
        cliente.setEndereco(endereco);
        return update(cliente).getEndereco();
    }

    /**
     * Recupera o endere�o de um cliente.
     *
     * @param cliente
     * @return Endereco;
     */
    public Endereco getEndereco(Cliente cliente) {
        return getById(cliente.getId()).getEndereco();
    }

    /**
     * Recupera todos os clientes cadastrados.
     *
     * @return List<Cliente>.
     */
    public List<Cliente> getAll() {
        return clienteDao.getAll();
    }

    /**
     * Fecha a conex�o
     */
    public void closeConn() {
        clienteDao.close();
    }
}
