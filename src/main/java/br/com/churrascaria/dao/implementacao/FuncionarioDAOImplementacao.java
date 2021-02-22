package br.com.churrascaria.dao.implementacao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
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
		try {
			en.persist(funcionario);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar salvar o funcionario.", pe);
		}

	}

	@Override
	public List<Funcionario> getAll() throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Funcionario> criteriaQuery = criteriaBuilder.createQuery(Funcionario.class);
		Root<Funcionario> root = criteriaQuery.from(Funcionario.class);

		criteriaQuery.select(root);

		TypedQuery<Funcionario> typedQuery = em.createQuery(criteriaQuery);

		List<Funcionario> resultado = typedQuery.getResultList();
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
			predicate.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("login")),
					"%" + filter.getLogin().toLowerCase() + "%"));
		}
		if (notEmpty(filter.getNome())) {
			predicate.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")),
					"%" + filter.getNome().toLowerCase() + "%"));
		}
		if (notEmpty(filter.getTipoDeFuncionario())) {
			predicate.add(criteriaBuilder.equal(root.get("tipoDeFuncionario"), filter.getTipoDeFuncionario()));
		}
		if (notEmpty(filter.getAtivo())) {
			predicate.add(criteriaBuilder.equal(root.get("ativo"), filter.getAtivo()));
		}
		if (notEmpty(filter.getId())) {
			predicate.add(criteriaBuilder.equal(root.get("Id"), filter.getId()));
		}

		return predicate.toArray(new Predicate[0]);
	}

	@Override
	public boolean existeUsuarioComLogin(Funcionario func) {

		if (empty(func) || empty(func.getLogin())) {
			return false;
		}

		// Usar estratégia de contabilizar quantos usuários existem com o dado login, e
		// que não seja ele mesmo.
		// Existe algum usuário com o login caso a contagem seja diferente de zero.
		// Usar COUNT(*), já que cláusula EXISTS não pode ser usada no SELECT pela BNF
		// do JPQL:
		// https://javaee.github.io/tutorial/persistence-querylanguage006.html

		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Funcionario> rootFunc = criteriaQuery.from(Funcionario.class);

		List<Predicate> predicate = new ArrayList<Predicate>();
		if (notEmpty(func.getLogin())) {
			predicate.add(
					criteriaBuilder.equal(criteriaBuilder.lower(rootFunc.get("login")), func.getLogin().toLowerCase()));
		}
		if (notEmpty(func.getId())) {
			predicate.add(criteriaBuilder.notEqual(rootFunc.get("Id"), func.getId()));
		}
		Expression<Long> root = criteriaBuilder.count(rootFunc);
		criteriaQuery.select(root);

		criteriaQuery.where(predicate.toArray(new Predicate[0]));

		TypedQuery<Long> typedQuery = em.createQuery(criteriaQuery);

		Long count = typedQuery.getSingleResult();
		return count > 0;
	}

	@Override
	public Funcionario update(Funcionario funcionario) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		Funcionario resultado = funcionario;
		try {
			resultado = em.merge(funcionario);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar atualizar o funcionario.",
					pe);
		}
		return resultado;
	}

	@Override
	public void delete(Funcionario obj) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		try {
			obj = em.find(Funcionario.class, obj.getId());
			em.remove(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar remover o usuário.", pe);
		}

	}

}
