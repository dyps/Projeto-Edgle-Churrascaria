package br.com.churrascaria.util;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Interceptor
@TransacionalCdi
public class TransacionalInterceptor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;

	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception {

		Object resultado = null;
		boolean criador = false;
		EntityTransaction trx = em.getTransaction();

		try {
			if (!trx.isActive()) {
				trx.begin();
				criador = true;
			}
			
			resultado = ctx.proceed();
		} catch (Exception pe) {
			pe.printStackTrace();
			if (trx.isActive() && criador) {
				// Rollback if any error happens before reaching commit
				trx.rollback();
			}
			throw pe;
		} finally {
			if (trx.isActive() && criador) {
				trx.commit();
			}
		}

		return resultado;
	}

}