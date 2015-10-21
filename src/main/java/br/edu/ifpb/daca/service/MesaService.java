package br.edu.ifpb.daca.service;

import br.edu.ifpb.daca.dao.MesaDao;
import br.edu.ifpb.daca.entities.Mesa;
import java.util.List;

public class MesaService {

    private MesaDao mesaDao;

    public MesaService() {
    }

    public MesaDao getMesaDao() {
        return mesaDao;
    }

    public void setMesaDao(MesaDao mesaDao) {
        this.mesaDao = mesaDao;
    }

    public void save(Mesa mesa) {
        mesaDao.save(mesa);
    }

    public Mesa update(Mesa mesa) {
        return mesaDao.update(mesa);
    }

    public void delete(Mesa mesa) {
        mesaDao.delete(mesa);
    }

    public Mesa getById(Long id) {
        return mesaDao.getById(id);
    }

    public List<Mesa> getAll() {
        return mesaDao.getAll();
    }

    public void closeConn() {
        mesaDao.close();
    }
}
