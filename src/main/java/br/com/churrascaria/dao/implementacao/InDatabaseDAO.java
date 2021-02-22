package br.com.churrascaria.dao.implementacao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public abstract class InDatabaseDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 807230061124569753L;

	@Inject
	private EntityManager entityManager;

	public InDatabaseDAO() {
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistenceEdgleChurrascaria");
//		if (entityManager == null) {
//			entityManager = factory.createEntityManager();
//		}
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	protected boolean notEmpty(Object obj) {
		return !empty(obj);
	}

	protected boolean empty(Object obj) {
		return obj == null;
	}

	protected boolean notEmpty(String obj) {
		return obj != null && !obj.trim().isEmpty();
	}

}
