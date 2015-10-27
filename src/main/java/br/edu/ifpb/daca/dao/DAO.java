package br.edu.ifpb.daca.dao;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Classe que cria o Entity Manager para manipular a persistencia dos objetos.
 *
 */
public abstract class DAO implements Serializable {

    private static final long serialVersionUID = 1L;

    // Injeção de dependencia
    @Inject
    EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
