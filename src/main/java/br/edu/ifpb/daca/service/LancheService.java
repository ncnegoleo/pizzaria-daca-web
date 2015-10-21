package br.edu.ifpb.daca.service;

import br.edu.ifpb.daca.dao.LancheDao;
import br.edu.ifpb.daca.entities.Lanche;
import java.util.List;

public class LancheService {

    protected LancheDao lancheDao;

    public LancheService() {
    }

    public LancheDao getLancheDao() {
        return lancheDao;
    }

    public void setLancheDao(LancheDao lancheDao) {
        this.lancheDao = lancheDao;
    }

    public void save(Lanche lanche) {
        lancheDao.save(lanche);
    }

    public Lanche update(Lanche lanche) {
        return lancheDao.update(lanche);
    }

    public void delete(Lanche lanche) {
        lancheDao.delete(lanche);
    }

    public Lanche getById(Long id) {

        return lancheDao.getById(id);
    }

    public List<Lanche> getAll() {
        return lancheDao.getAll();
    }

    public void closeConn() {
        lancheDao.close();
    }
}
