package br.edu.ifpb.daca.service;

import br.edu.ifpb.daca.dao.TamanhoDao;
import br.edu.ifpb.daca.entities.Tamanho;
import java.util.List;

public class TamanhoService {

    TamanhoDao tamanhoDao;

    public void setSaborDao(TamanhoDao tamanhoDao) {
        this.tamanhoDao = tamanhoDao;
    }

    public TamanhoDao getTamanhoDao() {
        return this.tamanhoDao;
    }

    public void save(Tamanho tamanho) {
        tamanho.setDisponivel(true);

        tamanhoDao.save(tamanho);
    }

    public Tamanho update(Tamanho tamanho) {
        return tamanhoDao.update(tamanho);
    }

    public void delete(Tamanho tamanho) {
        tamanhoDao.delete(tamanho);
    }

    public Tamanho getById(Long id) {
        return tamanhoDao.getById(id);
    }

    public List<Tamanho> getAll() {
        return tamanhoDao.getAll();
    }

    public void closeConn() {
        this.tamanhoDao.close();
    }
}
