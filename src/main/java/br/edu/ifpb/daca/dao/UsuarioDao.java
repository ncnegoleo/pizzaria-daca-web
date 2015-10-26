/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.daca.dao;

import br.edu.ifpb.daca.entities.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 *
 * @author leonardo
 */
public class UsuarioDao extends DAO implements Persistible<Usuario, Long> {

    @Override
    public void save(Usuario usuario) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(usuario);
            transaction.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public Usuario update(Usuario usuario) {
                EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Usuario resultado = usuario;
        try {
            resultado = em.merge(usuario);
            transaction.commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
        return resultado;
    }

    @Override
    public void delete(Usuario usuario) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            usuario = em.merge(usuario);
            em.remove(usuario);
            transaction.commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public Usuario getById(Long id) {
                EntityManager em = getEntityManager();
        Usuario resultado = null;
        try {
            resultado = em.find(Usuario.class, id);
        } catch (PersistenceException pe) {
            pe.printStackTrace();
        } finally {
            em.close();
        }
        return resultado;
    }

    @Override
    public List<Usuario> getAll() {
                EntityManager em = getEntityManager();
        List<Usuario> resultado = null;
        try {
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario_Entity u", Usuario.class);
            resultado = query.getResultList();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
        } finally {
            em.close();
        }
        return resultado;
    }
    
}
