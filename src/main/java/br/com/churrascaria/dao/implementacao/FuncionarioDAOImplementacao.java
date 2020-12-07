package br.com.churrascaria.dao.implementacao;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import br.com.churrascaria.dao.FuncionarioDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.Funcionario;

public class FuncionarioDAOImplementacao extends InDatabaseDAO implements FuncionarioDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3668601969837763513L;

	public void save(Funcionario funcionario) throws PersistenciaEdgleChurrascariaException {
		EntityManager en = getEntityManager();
		EntityTransaction transaction = en.getTransaction();
		transaction.begin();
		try {
			en.persist(funcionario);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar salvar o funcionario.", pe);
		}

	}

	public void deletar(int id) {
		EntityManager en = getEntityManager();
		try {
			en.remove(en.find(Funcionario.class, id));
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			// TODO: handle exception
		}
//		finally {
//			en.close();
//		}

	}

	public void atualizar(Funcionario obj) {
		EntityManager en = getEntityManager();
		EntityTransaction transaction = en.getTransaction();
		transaction.begin();
		try {
			en.merge(obj);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			// TODO: handle exception
		}
//		finally {
//			en.close();
//		}
	}

	public Funcionario buscar(int id) {
		EntityManager en = getEntityManager();
		EntityTransaction transaction = en.getTransaction();
		transaction.begin();
		try {
			return en.find(Funcionario.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			// TODO: handle exception
		}
//		finally {
//			en.close();
//		}
		return null;

	}

}
