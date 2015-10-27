package br.edu.ifpb.daca.util.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.interceptor.InterceptorBinding;

/**
 *  Annota��o para definir o interceptador CDI para as 
 * opera��es de transa��o com o banco de daodos
 * @author leonardo
 */
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
public @interface TransactionalCDI {}
