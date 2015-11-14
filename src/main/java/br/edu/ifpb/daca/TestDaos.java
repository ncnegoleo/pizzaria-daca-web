package br.edu.ifpb.daca;

import br.edu.ifpb.daca.beans.PedidoDelete;
import br.edu.ifpb.daca.dao.PedidoDao;
import br.edu.ifpb.daca.entities.Cliente;
import br.edu.ifpb.daca.entities.ItensPedido;
import br.edu.ifpb.daca.entities.Lanche;
import br.edu.ifpb.daca.entities.Mesa;
import br.edu.ifpb.daca.entities.Pedido;
import br.edu.ifpb.daca.entities.PedidoDelivery;
import br.edu.ifpb.daca.entities.PedidoLocal;
import br.edu.ifpb.daca.entities.Sabor;
import br.edu.ifpb.daca.service.SaborService;
import br.edu.ifpb.daca.validation.DacaPersistenceException;
import br.edu.ifpb.daca.validation.DacaServiceException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class TestDaos {

    public static void main(String[] args) throws DacaServiceException, DacaPersistenceException {

        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;

        try {

            emf = Persistence.createEntityManagerFactory("daca-local");
            em = emf.createEntityManager();
           
            
            tx = em.getTransaction();
            tx.begin();
            
            List<ItensPedido> its = new ArrayList<>();
            
            Lanche lanche_1 = em.find(Lanche.class, 38703L);
            Lanche lanche_2 = em.find(Lanche.class, 38705L);
            Lanche lanche_3 = em.find(Lanche.class, 38704L);
            
            Cliente cliente = em.find(Cliente.class, 66051L);
            
            its.add(new ItensPedido(lanche_1, 2, ""));
            its.add(new ItensPedido(lanche_2, 2, ""));
            its.add(new ItensPedido(lanche_3, 4, ""));
            
            PedidoDelivery pd = new PedidoDelivery(new Date(), its, cliente);
            
            
            em.persist(pd);
            
            tx.commit();
            
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
}
