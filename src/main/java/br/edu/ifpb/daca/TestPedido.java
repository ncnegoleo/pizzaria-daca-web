package br.edu.ifpb.daca;

import br.edu.ifpb.daca.entities.Cliente;
import br.edu.ifpb.daca.entities.Endereco;
import br.edu.ifpb.daca.service.ClienteService;

public class TestPedido {
    
    public static void main(String[] args) {
        
        ClienteService cs = new ClienteService();
        
        Cliente cliente = new Cliente();
        cliente.setNome("leo");
        Endereco endereco = new Endereco();
        cliente.setEndereco(endereco);
        cliente.setTelefone("(87)9195-5410");
        
        cs.save(cliente);
    }
}
