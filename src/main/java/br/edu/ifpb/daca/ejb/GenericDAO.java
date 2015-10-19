package br.edu.ifpb.daca.ejb;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class GenericDAO<T> {

    private final Class<T> entityClass;

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void persist(T entity) {
        getEntityManager().getTransaction().begin();
        try {
        getEntityManager().persist(entity);
        getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            getEntityManager().getTransaction().rollback();
        } finally {
            getEntityManager().close();
        }
    }

    public T update(T entity) {
        getEntityManager().merge(entity);
        return entity;
    }

    public void remove(T entity) {
        getEntityManager().remove(entity);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        Query q = getEntityManager().createQuery("SELECT e FROM "
                + entityClass.getName() + " e");
        List<T> list = q.getResultList();
        return list;
    }

    public T find(Long id) {
        T e = getEntityManager().find(entityClass, id);
        return e;
    }
    
    protected abstract EntityManager getEntityManager();
}
