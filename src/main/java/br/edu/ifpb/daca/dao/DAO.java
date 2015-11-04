package br.edu.ifpb.daca.dao;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Classe que cria o Entity Manager para manipular a persistencia dos objetos.
 *
 */
public abstract class DAO implements Serializable {

    private static final long serialVersionUID = 1L;

    // Injeção de dependencia
    @PersistenceContext(unitName = "daca")
    EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
