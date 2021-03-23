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

import br.com.churrascaria.dao.PedidoDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.Pedido;

@Named
@RequestScoped
public class PedidoDAOImplementacao extends InDatabaseDAO implements PedidoDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void save(Pedido observacao) throws PersistenciaEdgleChurrascariaException {
		EntityManager en = getEntityManager();
		try {
			en.persist(observacao);
		} catch (PersistenceException pe) {
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar salvar o pedido.", pe);
		}

	}

	@Override
	public List<Pedido> getAll() throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
		Root<Pedido> root = criteriaQuery.from(Pedido.class);

		criteriaQuery.select(root);

		TypedQuery<Pedido> typedQuery = em.createQuery(criteriaQuery);

		List<Pedido> resultado = typedQuery.getResultList();
		return resultado;
	}

	@Override
	public Pedido update(Pedido observacao) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		Pedido resultado = observacao;
		try {
			resultado = em.merge(observacao);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar atualizar o pedido.",
					pe);
		}
		return resultado;
	}

	public void delete(Pedido obj) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		try {
			obj = em.find(Pedido.class, obj.getId());
			em.remove(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar remover o pedido.", pe);
		}

	}

	@Override
	public Pedido getByID(Long id) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
		Root<Pedido> root = criteriaQuery.from(Pedido.class);

		ArrayList<Predicate> criaPpredicate = new ArrayList<Predicate>();
		criaPpredicate.add(criteriaBuilder.equal(root.get("Id"), id));
		Predicate[] p = criaPpredicate.toArray(new Predicate[0]);
		criteriaQuery.select(root);
		criteriaQuery.where(p);

		TypedQuery<Pedido> typedQuery = em.createQuery(criteriaQuery);

		Pedido resultado = typedQuery.getSingleResult();
		return resultado;
	}

}