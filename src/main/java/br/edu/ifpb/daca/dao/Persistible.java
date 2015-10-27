package br.edu.ifpb.daca.dao;

import br.edu.ifpb.daca.validation.DacaPersistenceException;
import java.util.List;

/**
 * Interface que define a arquitetura básica e obrigatória das entidades que
 * serão persistidas.
 *
 * @param <E>
 *            Entidade a ser persitida.
 * @param <Pk>
 *            Chave primária.
 */
public interface Persistible<E, Pk> {

	public void save(E e) throws DacaPersistenceException;

	public E update(E e) throws DacaPersistenceException;

	public void delete(E e) throws DacaPersistenceException;

	public E getById(Pk pk) throws DacaPersistenceException;

	public List<E> getAll() throws DacaPersistenceException;
}