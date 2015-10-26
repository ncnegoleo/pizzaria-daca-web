package br.edu.ifpb.daca.service;

import br.edu.ifpb.daca.dao.UsuarioDao;
import br.edu.ifpb.daca.entities.Usuario;
import java.util.List;

public class UsuarioService {

    UsuarioDao usuarioDao;

    public void setSaborDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public UsuarioDao getTamanhoDao() {
        return this.usuarioDao;
    }

    public void save(Usuario usuario) {
        usuarioDao.save(usuario);
    }

    public Usuario update(Usuario usuario) {
        return usuarioDao.update(usuario);
    }

    public void delete(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    public Usuario getById(Long id) {
        return usuarioDao.getById(id);
    }

    public List<Usuario> getAll() {
        return usuarioDao.getAll();
    }

    public void closeConn() {
        this.usuarioDao.close();
    }

}
