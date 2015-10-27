package br.edu.ifpb.daca.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Calsse para implantação das dependencias do JPA com CDI
 * @author leonardo
 */
public class JPAUtil {
    
    @Produces
    @ApplicationScoped
    public EntityManagerFactory createEMF() {
        return Persistence.createEntityManagerFactory("daca");
    }
    
    @Produces
    @RequestScoped
    public EntityManager createEM(EntityManagerFactory factory) {
        return factory.createEntityManager();
    }
    
    public void closeEM(@Disposes EntityManager em) {
        em.close();
    }
}
