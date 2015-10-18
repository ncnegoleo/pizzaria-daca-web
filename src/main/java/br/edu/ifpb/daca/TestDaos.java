package br.edu.ifpb.daca;

import br.edu.ifpb.daca.dao.ClienteDao;
import br.edu.ifpb.daca.entities.Cliente;
import br.edu.ifpb.daca.entities.Endereco;
import br.edu.ifpb.daca.service.ClienteService;
import java.util.List;

public class TestDaos {

    public static void main(String[] args) {
        ClienteService clienteService = new ClienteService(new ClienteDao());

        /* 1 */
        Cliente cliente = simpleSaveCliente(clienteService);

        /* 2 */
        simpleGetByID(clienteService, cliente.getId());

        /* 3 */
        simpleUpdateCliente(clienteService, cliente);

        /* 4 */
        simpleGetAllClientes(clienteService);

        /* 5 */
        simpleDeleteCliente(clienteService, cliente);
        
        clienteService.closeConn();
    }

    private static Cliente simpleSaveCliente(ClienteService clienteService) {

        Cliente cliente = createCliente();

        // Salvando com serviço
        clienteService.save(cliente);

        System.err.println("SALVO");
        System.out.println(cliente);

        return cliente;
    }

    private static void simpleGetByID(ClienteService clienteService, Long id) {

        Cliente cliente = clienteService.getById(id);

        System.err.println("RESGATADO");
        System.out.println(cliente);
    }

    private static void simpleUpdateCliente(ClienteService clienteService, Cliente cliente) {

        cliente.setNome("João da Silva");
        Cliente clt = clienteService.update(cliente);

        System.err.println("ATUALIZADO");
        System.out.println(clt);
    }

    private static void simpleGetAllClientes(ClienteService clienteService) {
        List<Cliente> clientes = clienteService.getAll();

        System.err.println("TODOS RECUPERADOS");
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    private static void simpleDeleteCliente(ClienteService clienteService, Cliente cliente) {
        // Deleta clientes que não estejam atrelados a nenhum pedido
        clienteService.delete(cliente);

        System.err.println("DELETADO");
    }

    private static Cliente createCliente() {

        // Criando um novo endereço
        Endereco endereco = new Endereco();
        endereco.setRua("Rua antonieta L.");
        endereco.setBairro("Alto da Conceição");
        endereco.setNumero(40);

        // Criando um novo Cliente
        Cliente cliente = new Cliente("Leonardo Soares Rodrigues", endereco,
                "(87)9195-5410");

        return cliente;
    }
}
