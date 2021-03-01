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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.churrascaria.dao.CategoriaProdutoDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.CategoriaProduto;
import br.com.churrascaria.filter.CategoriaProdutoFilter;

@Named
@RequestScoped
public class CategoriaProdutoDAOImplementacao extends InDatabaseDAO implements CategoriaProdutoDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void save(CategoriaProduto categoriaProduto) throws PersistenciaEdgleChurrascariaException {
		EntityManager en = getEntityManager();
		try {
			en.persist(categoriaProduto);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException(
					"Ocorreu algum erro ao tentar salvar a categoria de produto.", pe);
		}
	}

	@Override
	public List<CategoriaProduto> getAll() throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<CategoriaProduto> criteriaQuery = criteriaBuilder.createQuery(CategoriaProduto.class);
		Root<CategoriaProduto> root = criteriaQuery.from(CategoriaProduto.class);

		criteriaQuery.select(root);

		TypedQuery<CategoriaProduto> typedQuery = em.createQuery(criteriaQuery);

		List<CategoriaProduto> resultado = typedQuery.getResultList();
		return resultado;
	}

	@Override
	public List<CategoriaProduto> findBy(CategoriaProdutoFilter filter) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<CategoriaProduto> criteriaQuery = criteriaBuilder.createQuery(CategoriaProduto.class);
		Root<CategoriaProduto> root = criteriaQuery.from(CategoriaProduto.class);

		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, filter);
		criteriaQuery.select(root);
		criteriaQuery.where(predicate);

		TypedQuery<CategoriaProduto> typedQuery = em.createQuery(criteriaQuery);

		List<CategoriaProduto> resultado = typedQuery.getResultList();
		return resultado;
	}

	private Predicate[] getPredicateFilter(CriteriaBuilder criteriaBuilder, Root<CategoriaProduto> root,
			CategoriaProdutoFilter filter) {
		List<Predicate> predicate = new ArrayList<Predicate>();
		if (notEmpty(filter.getNome())) {
			predicate.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")),
					"%" + filter.getNome().toLowerCase() + "%"));
		}
		if (notEmpty(filter.getId())) {
			predicate.add(criteriaBuilder.equal(root.get("Id"), filter.getId()));
		}

		return predicate.toArray(new Predicate[0]);
	}

	@Override
	public CategoriaProduto update(CategoriaProduto categoriaProduto) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		CategoriaProduto resultado = categoriaProduto;
		try {
			resultado = em.merge(categoriaProduto);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException(
					"Ocorreu algum erro ao tentar atualizar a categoria de produto.", pe);
		}
		return resultado;
	}

	@Override
	public void delete(CategoriaProduto obj) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		try {
			obj = em.find(CategoriaProduto.class, obj.getId());
			em.remove(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException(
					"Ocorreu algum erro ao tentar remover a categoria de produto.", pe);
		}
	}

}
