package br.edu.ifpb.daca.service;

import br.edu.ifpb.daca.dao.TamanhoDao;
import br.edu.ifpb.daca.entities.Tamanho;
import br.edu.ifpb.daca.validation.TamanhoValidation;
import java.util.List;


public class TamanhoService {
	TamanhoDao tamanhoDao;

	public static final String TAMANHO_MSG_EXCEPT = "O tamanho n?o pode se nulo ou vazio";
	public static final String ID_MSG_EXCEPT = "O id do tamnaho n?o pode ser nulo ou menor que 1";

	public void setSaborDao(TamanhoDao tamanhoDao) {
		this.tamanhoDao = tamanhoDao;
	}

	public TamanhoDao getTamanhoDao() {
		return this.tamanhoDao;
	}

	public void save(Tamanho tamanho) {
		TamanhoValidation.valideNomeTamanho(tamanho, TAMANHO_MSG_EXCEPT);

		tamanho.setDisponivel(true);

		tamanhoDao.save(tamanho);
	}

	public Tamanho update(Tamanho tamanho) {
		TamanhoValidation.valideNomeTamanho(tamanho, TAMANHO_MSG_EXCEPT);

		tamanho.setDisponivel(true);

		Tamanho aux;
		aux = tamanhoDao.update(tamanho);

		return aux;
	}

	public void delete(Tamanho tamanho) {
			tamanhoDao.delete(tamanho);
	}

	public Tamanho getById(Long id) {
		Tamanho aux = new Tamanho();
		aux.setId(id);
		TamanhoValidation.validId(aux, ID_MSG_EXCEPT);

		return tamanhoDao.getById(id);
	}

	public List<Tamanho> getAll() {
		return tamanhoDao.getAll();
	}
	
	public void closeConn() {
		this.tamanhoDao.close();
	}
}

