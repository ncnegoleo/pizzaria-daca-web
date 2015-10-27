package br.edu.ifpb.daca.util;

import br.edu.ifpb.daca.util.annotations.TransactionalCDI;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Classe que implementa as regras comuns de negocio durante uma transação com
 * JPA.
 *
 * @author leonardo
 */
@Interceptor
@TransactionalCDI
public class TransactionalInterceptorJPA {
    
    @Inject
    EntityManager entityManager;
    
    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {
        
        Object resultado = null;
        
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        
        try {
            resultado = ctx.proceed();
            transaction.commit();
        } catch (Exception pe) {
            transaction.rollback();
            throw pe;
        }
        
        return resultado;
    }
}
