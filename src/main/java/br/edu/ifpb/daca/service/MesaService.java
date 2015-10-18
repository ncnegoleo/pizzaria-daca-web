package br.edu.ifpb.daca.service;

import br.edu.ifpb.daca.dao.MesaDao;
import br.edu.ifpb.daca.entities.Mesa;
import static br.edu.ifpb.daca.validation.MesaValidation.*;
import java.util.List;

public class MesaService {

    private MesaDao mesaDao;

    public static final String ID_MSG_EXCEPT = "O Id da mesa não pode ser menor que 1 ou nulo";
    public static final String DESCRICAO_MSG_EXCEP = "A descrição da mesa não pode ser vazia ou nula";

    public MesaService() {
    }

    public MesaDao getMesaDao() {
        return mesaDao;
    }

    public void setMesaDao(MesaDao mesaDao) {
        this.mesaDao = mesaDao;
    }

    public void save(Mesa mesa) {
        valideDescricao(mesa, DESCRICAO_MSG_EXCEP);

        mesaDao.save(mesa);
    }

    public Mesa update(Mesa mesa) {
        valideId(mesa, ID_MSG_EXCEPT);
        valideDescricao(mesa, DESCRICAO_MSG_EXCEP);

        return mesaDao.update(mesa);
    }

    public void delete(Mesa mesa) {
        valideId(mesa, ID_MSG_EXCEPT);

        mesaDao.delete(mesa);
    }

    public Mesa getById(Long id) {
        Mesa aux = new Mesa();
        aux.setId(id);
        valideId(aux, ID_MSG_EXCEPT);

        return mesaDao.getById(id);
    }

    public List<Mesa> getAll() {
        return mesaDao.getAll();
    }

    public void closeConn() {
        mesaDao.close();
    }
}
