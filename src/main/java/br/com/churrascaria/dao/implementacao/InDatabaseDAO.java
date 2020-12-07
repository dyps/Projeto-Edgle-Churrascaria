package br.com.churrascaria.dao.implementacao;


import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public abstract class InDatabaseDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 807230061124569753L;
	
	private EntityManager entityManager;

	public InDatabaseDAO() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistenceEdgleChurrascaria");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
