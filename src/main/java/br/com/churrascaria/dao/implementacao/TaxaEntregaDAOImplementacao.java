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

import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.dao.TaxaEntregaDAO;
import br.com.churrascaria.entities.TaxaEntrega;
import br.com.churrascaria.filter.TaxaEntregaFilter;

@Named
@RequestScoped
public class TaxaEntregaDAOImplementacao extends InDatabaseDAO implements TaxaEntregaDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void save(TaxaEntrega taxaEntrega) throws PersistenciaEdgleChurrascariaException {
		EntityManager en = getEntityManager();
		try {
			en.persist(taxaEntrega);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar salvar a taxa de entrega.",
					pe);
		}
	}

	@Override
	public List<TaxaEntrega> getAll() throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<TaxaEntrega> criteriaQuery = criteriaBuilder.createQuery(TaxaEntrega.class);
		Root<TaxaEntrega> root = criteriaQuery.from(TaxaEntrega.class);

		criteriaQuery.select(root);

		TypedQuery<TaxaEntrega> typedQuery = em.createQuery(criteriaQuery);

		List<TaxaEntrega> resultado = typedQuery.getResultList();
		return resultado;
	}

	@Override
	public TaxaEntrega update(TaxaEntrega taxaEntrega) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		TaxaEntrega resultado = taxaEntrega;
		try {
			resultado = em.merge(taxaEntrega);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException(
					"Ocorreu algum erro ao tentar atualizar a taxa de entrega.", pe);
		}
		return resultado;
	}

	@Override
	public void delete(TaxaEntrega obj) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		try {
			obj = em.find(TaxaEntrega.class, obj.getId());
			em.remove(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar remover a taxa de entrega.",
					pe);
		}
	}

	@Override
	public List<TaxaEntrega> findBy(TaxaEntregaFilter filter) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<TaxaEntrega> criteriaQuery = criteriaBuilder.createQuery(TaxaEntrega.class);
		Root<TaxaEntrega> root = criteriaQuery.from(TaxaEntrega.class);

		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, filter);
		criteriaQuery.select(root);
		criteriaQuery.where(predicate);
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("valor")));

		TypedQuery<TaxaEntrega> typedQuery = em.createQuery(criteriaQuery);

		List<TaxaEntrega> resultado = typedQuery.getResultList();
		return resultado;
	}

	private Predicate[] getPredicateFilter(CriteriaBuilder criteriaBuilder, Root<TaxaEntrega> root,
			TaxaEntregaFilter filter) {
		List<Predicate> predicate = new ArrayList<Predicate>();
		if (notEmpty(filter.getValor())) {
			predicate
					.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("valor")), "%" + filter.getValor() + "%"));
		}
		if (notEmpty(filter.getDistanciaMaxima())) {
			predicate.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("distanciaMaxima")),
					"%" + filter.getDistanciaMaxima() + "%"));
		}
		if (notEmpty(filter.getId())) {
			predicate.add(criteriaBuilder.equal(root.get("Id"), filter.getId()));
		}

		return predicate.toArray(new Predicate[0]);
	}

}
