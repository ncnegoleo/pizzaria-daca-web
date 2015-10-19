package br.edu.ifpb.daca.ejb;

import br.edu.ifpb.daca.entities.Sabor;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SaborEJBDao  extends GenericDAO<Sabor> {
    
    @PersistenceContext(name = "remote_daca")
    EntityManager em;

    public SaborEJBDao() {
        super(Sabor.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }
}
