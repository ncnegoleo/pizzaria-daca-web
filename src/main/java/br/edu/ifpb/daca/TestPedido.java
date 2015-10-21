package br.edu.ifpb.daca;

import br.edu.ifpb.daca.dao.ClienteDao;
import br.edu.ifpb.daca.entities.Cliente;
import br.edu.ifpb.daca.service.ClienteService;
import java.util.List;

public class TestPedido {
    
    public static void main(String[] args) {
        
        ClienteService cs = new ClienteService(new ClienteDao());
        
        List<Cliente> clientes = cs.getByNome("Jo");
        
        clientes.forEach(c -> System.out.println(c));
        
    }
}
