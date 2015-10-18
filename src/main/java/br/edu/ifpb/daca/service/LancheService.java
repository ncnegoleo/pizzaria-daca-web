package br.edu.ifpb.daca.service;

import br.edu.ifpb.daca.dao.LancheDao;
import br.edu.ifpb.daca.entities.Lanche;
import static br.edu.ifpb.daca.validation.LancheValidation.*;
import static br.edu.ifpb.daca.validation.msgs.LancheExcepMsg.*;
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
		valideDescricao(lanche, DESCRICAO_MSG_EXCEP.getValor());
		valideValorUnitario(lanche, VALOR_UNIT_MSG_EXCEP.getValor());

		lancheDao.save(lanche);
	}

	public Lanche update(Lanche lanche) {
		valideId(lanche, ID_MSG_EXCEPT.getValor());
		valideDescricao(lanche, DESCRICAO_MSG_EXCEP.getValor());
		valideValorUnitario(lanche, VALOR_UNIT_MSG_EXCEP.getValor());

		return lancheDao.update(lanche);
	}

	public void delete(Lanche lanche) {
		valideId(lanche, ID_MSG_EXCEPT.getValor());
		lancheDao.delete(lanche);
	}

	public Lanche getById(Long id) {
		Lanche aux = new Lanche();
		aux.setId(id);
		valideId(aux, ID_MSG_EXCEPT.getValor());

		return lancheDao.getById(id);
	}

	public List<Lanche> getAll() {
		return lancheDao.getAll();
	}
	
	public void closeConn() {
		lancheDao.close();
	}
}