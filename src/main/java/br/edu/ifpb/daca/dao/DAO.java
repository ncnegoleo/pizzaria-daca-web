package br.edu.ifpb.daca.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe que cria o Entity Manager para manipular a persistencia dos objetos.
 *
 */
public abstract class DAO {

    static EntityManagerFactory emf;

    // O entity manager é criado antes da execução do construtor da classe.
    static {
        emf = Persistence.createEntityManagerFactory("daca");
    }

    /**
     * Recupera o entity Manager.
     *
     * @return {@link javax.persistence.EntityManager}
     */
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Fecha todas as conexões com o Entity Manager.
     */
    public void close() {
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
