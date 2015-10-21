package br.edu.ifpb.daca.service;

import br.edu.ifpb.daca.dao.SaborDao;
import br.edu.ifpb.daca.entities.Sabor;
import java.util.List;

public class SaborService {

    SaborDao saborDao;

    public void setSaborDao(SaborDao saborDao) {
        this.saborDao = saborDao;
    }

    public SaborDao getSaborDao() {
        return this.saborDao;
    }

    public void save(Sabor sabor) {
        sabor.setDisponivel(true);
        saborDao.save(sabor);
    }

    public Sabor update(Sabor sabor) {
        return saborDao.update(sabor);
    }

    public void delete(Sabor sabor) {
        saborDao.delete(sabor);
    }

    public Sabor getById(Long id) {
        return saborDao.getById(id);
    }

    public List<Sabor> getAll() {
        return saborDao.getAll();
    }

    public void closeConn() {
        this.saborDao.close();
    }
}
