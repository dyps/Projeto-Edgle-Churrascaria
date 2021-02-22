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

import br.com.churrascaria.dao.EntregadorDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.Entregador;
import br.com.churrascaria.filter.EntregadorFilter;

@Named
@RequestScoped
public class EntregadorDAOImplementacao extends InDatabaseDAO implements EntregadorDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void save(Entregador entregador) throws PersistenciaEdgleChurrascariaException {
		EntityManager en = getEntityManager();
		EntityTransaction transaction = en.getTransaction();
		transaction.begin();
		try {
			en.persist(entregador);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaEdgleChurrascariaException(
					"Ocorreu algum erro ao tentar salvar o entregador.", pe);
		}
	}

	@Override
	public List<Entregador> getAll() throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Entregador> criteriaQuery = criteriaBuilder.createQuery(Entregador.class);
		Root<Entregador> root = criteriaQuery.from(Entregador.class);

		criteriaQuery.select(root);

		TypedQuery<Entregador> typedQuery = em.createQuery(criteriaQuery);

		List<Entregador> resultado = typedQuery.getResultList();
		return resultado;
	}

	@Override
	public List<Entregador> findBy(EntregadorFilter filter) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Entregador> criteriaQuery = criteriaBuilder.createQuery(Entregador.class);
		Root<Entregador> root = criteriaQuery.from(Entregador.class);

		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, filter);
		criteriaQuery.select(root);
		criteriaQuery.where(predicate);
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("ativo")));

		TypedQuery<Entregador> typedQuery = em.createQuery(criteriaQuery);

		List<Entregador> resultado = typedQuery.getResultList();
		return resultado;
	}
	
	private Predicate[] getPredicateFilter(CriteriaBuilder criteriaBuilder, Root<Entregador> root,
			EntregadorFilter filter) {
		List<Predicate> predicate = new ArrayList<Predicate>();
		if (notEmpty(filter.getNome())) {
			predicate.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")),
					"%" + filter.getNome().toLowerCase() + "%"));
		}
		if (notEmpty(filter.getTelefone())) {
			predicate.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("telefone")),
					"%" + filter.getTelefone().toLowerCase() + "%"));
		}
		if (notEmpty(filter.getId())) {
			predicate.add(criteriaBuilder.equal(root.get("Id"), filter.getId()));
		}

		return predicate.toArray(new Predicate[0]);
	}

	@Override
	public Entregador update(Entregador entregador) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		Entregador resultado = entregador;
		try {
			em.getTransaction().begin();
			resultado = em.merge(entregador);
			em.getTransaction().commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException(
					"Ocorreu algum erro ao tentar atualizar o entregador.", pe);
		}
		return resultado;
	}

	@Override
	public void delete(Entregador obj) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		try {
			obj = em.find(Entregador.class, obj.getId());
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException(
					"Ocorreu algum erro ao tentar remover o entregador.", pe);
		}
	}

}
