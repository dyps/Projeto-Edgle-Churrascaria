package br.com.churrascaria.dao.implementacao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.churrascaria.dao.FuncionarioDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.Funcionario;
import br.com.churrascaria.filter.FuncionarioFilter;

@Named
@RequestScoped
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

	public Funcionario getByID(long id) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		Funcionario resultado = null;
		try {
			resultado = em.find(Funcionario.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar recuperar o funcionario com base no ID.",
					pe);
		}

		return resultado;

	}

	@Override
	public List<Funcionario> getAll() throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		List<Funcionario> resultado = new ArrayList<Funcionario>();
		try {
			TypedQuery<Funcionario> query = em.createQuery("SELECT s FROM Funcionario s", Funcionario.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException(
					"Ocorreu algum erro ao tentar recuperar todos os funcionarios.", pe);
		}
		return resultado;
	}

	@Override
	public List<Funcionario> findBy(FuncionarioFilter filter) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Funcionario> criteriaQuery = criteriaBuilder.createQuery(Funcionario.class);
		Root<Funcionario> root = criteriaQuery.from(Funcionario.class);

		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, filter);
		criteriaQuery.select(root);
		criteriaQuery.where(predicate);
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("ativo")));

		TypedQuery<Funcionario> typedQuery = em.createQuery(criteriaQuery);

		List<Funcionario> resultado = typedQuery.getResultList();
		return resultado;
	}
	private Predicate[] getPredicateFilter(CriteriaBuilder criteriaBuilder, Root<Funcionario> root,
			FuncionarioFilter filter) {

		List<Predicate> predicate = new ArrayList<Predicate>();
		if (notEmpty(filter.getLogin())) {
			predicate.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("login")), "%" + filter.getLogin().toLowerCase() + "%"));
		}
		if (notEmpty(filter.getNome())) {
			predicate.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + filter.getNome().toLowerCase() + "%"));
		}
		if (notEmpty(filter.getTipoDeFuncionario())) {
			predicate.add(criteriaBuilder.equal(root.get("tipoDeFuncionario"), filter.getTipoDeFuncionario()));
		}
		if (notEmpty(filter.getAtivo())) {
			predicate.add(criteriaBuilder.equal(root.get("ativo"), filter.getAtivo()));
		}
		if (notEmpty(filter.getId())) {
			predicate.add(criteriaBuilder.equal(root.get("Id"),   filter.getId()));
		}

		return predicate.toArray(new Predicate[0]);
	}

}
