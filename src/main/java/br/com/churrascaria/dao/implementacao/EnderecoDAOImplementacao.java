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

import br.com.churrascaria.dao.EnderecoDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.Endereco;
import br.com.churrascaria.filter.EnderecoFilter;

@Named
@RequestScoped
public class EnderecoDAOImplementacao extends InDatabaseDAO implements EnderecoDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void save(Endereco entidade) throws PersistenciaEdgleChurrascariaException {
		EntityManager en = getEntityManager();
		try {
			en.persist(entidade);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar salvar o endereço.", pe);
		}
	}

	@Override
	public List<Endereco> getAll() throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Endereco> criteriaQuery = criteriaBuilder.createQuery(Endereco.class);
		Root<Endereco> root = criteriaQuery.from(Endereco.class);

		criteriaQuery.select(root);

		TypedQuery<Endereco> typedQuery = em.createQuery(criteriaQuery);

		List<Endereco> resultado = typedQuery.getResultList();
		return resultado;
	}

	@Override
	public Endereco update(Endereco entidade) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		Endereco resultado = entidade;
		try {
			resultado = em.merge(entidade);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar atualizar o endereço.", pe);
		}
		return resultado;
	}

	@Override
	public void delete(Endereco entidade) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		try {
			entidade = em.find(Endereco.class, entidade.getId());
			em.remove(entidade);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar remover o endereço.", pe);
		}
	}

	@Override
	public List<Endereco> findBy(EnderecoFilter filter) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Endereco> criteriaQuery = criteriaBuilder.createQuery(Endereco.class);
		Root<Endereco> root = criteriaQuery.from(Endereco.class);

		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, filter);
		criteriaQuery.select(root);
		criteriaQuery.where(predicate);
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("nome")));

		TypedQuery<Endereco> typedQuery = em.createQuery(criteriaQuery);

		List<Endereco> resultado = typedQuery.getResultList();
		return resultado;
	}

	private Predicate[] getPredicateFilter(CriteriaBuilder criteriaBuilder, Root<Endereco> root,
			EnderecoFilter filter) {
		List<Predicate> predicate = new ArrayList<Predicate>();
		if (notEmpty(filter.getNome())) {
			predicate.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + filter.getNome() + "%"));
		}
		if (notEmpty(filter.getLogradouro())) {
			predicate.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("logradouro")),
					"%" + filter.getLogradouro() + "%"));
		}
		if (notEmpty(filter.getNumero())) {
			predicate.add(criteriaBuilder.equal(root.get("numero"), filter.getNumero()));
		}
		if (notEmpty(filter.getComplemento())) {
			predicate.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("complemento")),
					"%" + filter.getComplemento() + "%"));
		}
		if (notEmpty(filter.getId())) {
			predicate.add(criteriaBuilder.equal(root.get("Id"), filter.getId()));
		}

		return predicate.toArray(new Predicate[0]);
	}

}
