package br.com.churrascaria.dao.implementacao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.churrascaria.dao.TipoDePagamentoDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.TipoDePagamento;

@Named
@RequestScoped
public class TipoDePagamentoDAOImplementacao extends InDatabaseDAO implements TipoDePagamentoDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void save(TipoDePagamento observacao) throws PersistenciaEdgleChurrascariaException {
		EntityManager en = getEntityManager();
		try {
			en.persist(observacao);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar salvar o tipo.", pe);
		}

	}

	@Override
	public List<TipoDePagamento> getAll() throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<TipoDePagamento> criteriaQuery = criteriaBuilder.createQuery(TipoDePagamento.class);
		Root<TipoDePagamento> root = criteriaQuery.from(TipoDePagamento.class);

		criteriaQuery.select(root);

		TypedQuery<TipoDePagamento> typedQuery = em.createQuery(criteriaQuery);

		List<TipoDePagamento> resultado = typedQuery.getResultList();
		return resultado;
	}

	@Override
	public TipoDePagamento update(TipoDePagamento observacao) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		TipoDePagamento resultado = observacao;
		try {
			resultado = em.merge(observacao);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar atualizar o tipo.",
					pe);
		}
		return resultado;
	}

	public void delete(TipoDePagamento obj) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		try {
			obj = em.find(TipoDePagamento.class, obj.getId());
			em.remove(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar remover o tipo.", pe);
		}

	}

	@Override
	public TipoDePagamento getByID(Long id) throws PersistenciaEdgleChurrascariaException {
		List<TipoDePagamento> lista = getAll();
		for (TipoDePagamento observacoesPadrao : lista)
			if (observacoesPadrao.getId().equals(id))
				return observacoesPadrao;
		return null;
	}

}