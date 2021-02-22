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

import br.com.churrascaria.dao.MesaDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.Mesa;
import br.com.churrascaria.filter.MesaFilter;

@Named
@RequestScoped
public class MesaDAOImplementacao extends InDatabaseDAO implements MesaDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void save(Mesa mesa) throws PersistenciaEdgleChurrascariaException {
		EntityManager en = getEntityManager();
		EntityTransaction transaction = en.getTransaction();
		transaction.begin();
		try {
			en.persist(mesa);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar salvar a mesa.", pe);
		}
	}

	@Override
	public List<Mesa> getAll() throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Mesa> criteriaQuery = criteriaBuilder.createQuery(Mesa.class);
		Root<Mesa> root = criteriaQuery.from(Mesa.class);

		criteriaQuery.select(root);

		TypedQuery<Mesa> typedQuery = em.createQuery(criteriaQuery);

		List<Mesa> resultado = typedQuery.getResultList();
		return resultado;
	}

	@Override
	public List<Mesa> findBy(MesaFilter filter) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Mesa> criteriaQuery = criteriaBuilder.createQuery(Mesa.class);
		Root<Mesa> root = criteriaQuery.from(Mesa.class);

		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, filter);
		criteriaQuery.select(root);
		criteriaQuery.where(predicate);
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("ativo")));

		TypedQuery<Mesa> typedQuery = em.createQuery(criteriaQuery);

		List<Mesa> resultado = typedQuery.getResultList();
		return resultado;
	}

	private Predicate[] getPredicateFilter(CriteriaBuilder criteriaBuilder, Root<Mesa> root, MesaFilter filter) {
		List<Predicate> predicate = new ArrayList<Predicate>();
		if (notEmpty(filter.getNumero())) {
			predicate.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("numero")),
					"%" + filter.getNumero() + "%"));
		}
		if (notEmpty(filter.getId())) {
			predicate.add(criteriaBuilder.equal(root.get("Id"), filter.getId()));
		}

		return predicate.toArray(new Predicate[0]);
	}

	@Override
	public Mesa update(Mesa mesa) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		Mesa resultado = mesa;
		try {
			em.getTransaction().begin();
			resultado = em.merge(mesa);
			em.getTransaction().commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar atualizar a mesa.",
					pe);
		}
		return resultado;
	}

	@Override
	public void delete(Mesa obj) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		try {
			obj = em.find(Mesa.class, obj.getId());
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar remover a mesa.", pe);
		}
	}

}
