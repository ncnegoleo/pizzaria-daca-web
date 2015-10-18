package br.edu.ifpb.daca.service;

import br.edu.ifpb.daca.dao.SaborDao;
import br.edu.ifpb.daca.entities.Sabor;
import br.edu.ifpb.daca.validation.SaborValidation;
import java.util.List;

public class SaborService {

    SaborDao saborDao;

    public static final String SABOR_MSG_EXCEPT = "O nome do sabor n?o pode se nulo ou vazio";
    public static final String ID_MSG_EXCEPT = "O id do sabor n?o pode ser nulo ou menor que 1";

    public void setSaborDao(SaborDao saborDao) {
        this.saborDao = saborDao;
    }

    public SaborDao getSaborDao() {
        return this.saborDao;
    }

    public void save(Sabor sabor) {
        SaborValidation.valideNomeSabor(sabor, SABOR_MSG_EXCEPT);

        sabor.setDisponivel(true);

        saborDao.save(sabor);
    }

    public Sabor update(Sabor sabor) {
        SaborValidation.valideNomeSabor(sabor, SABOR_MSG_EXCEPT);

        sabor.setDisponivel(true);

        Sabor aux;
        aux = saborDao.update(sabor);

        return aux;
    }

    public void delete(Sabor sabor) {
        saborDao.delete(sabor);
    }

    public Sabor getById(Long id) {
        Sabor aux = new Sabor();
        aux.setId(id);
        SaborValidation.validId(aux, ID_MSG_EXCEPT);

        return saborDao.getById(id);
    }

    public List<Sabor> getAll() {
        return saborDao.getAll();
    }

    public void closeConn() {
        this.saborDao.close();
    }
}
