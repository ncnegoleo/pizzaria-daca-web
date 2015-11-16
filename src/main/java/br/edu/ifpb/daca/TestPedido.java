package br.edu.ifpb.daca;

import br.edu.ifpb.daca.entities.Usuario;
import br.edu.ifpb.daca.service.UsuarioService;
import br.edu.ifpb.daca.validation.DacaServiceException;

public class TestPedido {

    public static void main(String[] args) throws DacaServiceException {

        UsuarioService usuarioService = new UsuarioService();

        Usuario u = new Usuario();
        u.setSenha("123456");

        Usuario u2 = new Usuario();
        u2.setSenha("123456");

        usuarioService.criptografarSenha(u);
        usuarioService.criptografarSenha(u2);
        
        System.out.println(u.getSenha());
        System.out.println(u2.getSenha());
    }
}
