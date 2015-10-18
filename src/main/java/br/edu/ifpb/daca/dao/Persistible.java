package br.edu.ifpb.daca.dao;

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

	public void save(E e);

	public E update(E e);

	public void delete(E e);

	public E getById(Pk pk);

	public List<E> getAll();
}