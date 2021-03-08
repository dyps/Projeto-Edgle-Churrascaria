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

import br.com.churrascaria.dao.ClienteDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.Cliente;
import br.com.churrascaria.filter.ClienteFilter;

@Named
@RequestScoped
public class ClienteDAOImplementacao extends InDatabaseDAO implements ClienteDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void save(Cliente entidade) throws PersistenciaEdgleChurrascariaException {
		EntityManager en = getEntityManager();
		try {
			en.persist(entidade);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar salvar o cliente.", pe);
		}
	}

	@Override
	public List<Cliente> getAll() throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
		Root<Cliente> root = criteriaQuery.from(Cliente.class);

		criteriaQuery.select(root);

		TypedQuery<Cliente> typedQuery = em.createQuery(criteriaQuery);

		List<Cliente> resultado = typedQuery.getResultList();
		return resultado;
	}

	@Override
	public Cliente update(Cliente entidade) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		Cliente resultado = entidade;
		try {
			resultado = em.merge(entidade);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar atualizar o cliente.", pe);
		}
		return resultado;
	}

	@Override
	public void delete(Cliente entidade) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		try {
			entidade = em.find(Cliente.class, entidade.getId());
			em.remove(entidade);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar remover o cliente.", pe);
		}
	}

	@Override
	public List<Cliente> findBy(ClienteFilter filter) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
		Root<Cliente> root = criteriaQuery.from(Cliente.class);

		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, filter);
		criteriaQuery.select(root);
		criteriaQuery.where(predicate);
		// criteriaQuery.orderBy(criteriaBuilder.desc(root.get("nome")));

		TypedQuery<Cliente> typedQuery = em.createQuery(criteriaQuery);

		List<Cliente> resultado = typedQuery.getResultList();
		return resultado;
	}

	private Predicate[] getPredicateFilter(CriteriaBuilder criteriaBuilder, Root<Cliente> root, ClienteFilter filter) {
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

}
