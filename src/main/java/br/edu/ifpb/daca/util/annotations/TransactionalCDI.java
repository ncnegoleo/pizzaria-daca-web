package br.edu.ifpb.daca.util.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.interceptor.InterceptorBinding;

/**
 *  Annotação para definir o interceptador CDI para as 
 * operações de transação com o banco de daodos
 * @author leonardo
 */
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
public @interface TransactionalCDI {}
