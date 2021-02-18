package br.com.churrascaria.dao.implementacao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.churrascaria.dao.ObservacoesPadraoDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.ObservacaoPadrao;

@Named
@RequestScoped
public class ObservacoesPadraoDAOImplementacao extends InDatabaseDAO implements ObservacoesPadraoDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void save(ObservacaoPadrao observacao) throws PersistenciaEdgleChurrascariaException {
		EntityManager en = getEntityManager();
		EntityTransaction transaction = en.getTransaction();
		transaction.begin();
		try {
			en.persist(observacao);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar salvar a Observação.", pe);
		}

	}

	@Override
	public List<ObservacaoPadrao> getAll() throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<ObservacaoPadrao> criteriaQuery = criteriaBuilder.createQuery(ObservacaoPadrao.class);
		Root<ObservacaoPadrao> root = criteriaQuery.from(ObservacaoPadrao.class);

		criteriaQuery.select(root);

		TypedQuery<ObservacaoPadrao> typedQuery = em.createQuery(criteriaQuery);

		List<ObservacaoPadrao> resultado = typedQuery.getResultList();
		return resultado;
	}

	@Override
	public ObservacaoPadrao update(ObservacaoPadrao observacao) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		ObservacaoPadrao resultado = observacao;
		try {
			em.getTransaction().begin();
			resultado = em.merge(observacao);
			em.getTransaction().commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar atualizar a observacao.",
					pe);
		}
		return resultado;
	}

	public void delete(ObservacaoPadrao obj) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		try {
			obj = em.find(ObservacaoPadrao.class, obj.getId());
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar remover a observacao.", pe);
		}

	}

	@Override
	public ObservacaoPadrao getByID(Long id) throws PersistenciaEdgleChurrascariaException {
		List<ObservacaoPadrao> lista = getAll();
		for (ObservacaoPadrao observacoesPadrao : lista)
			if (observacoesPadrao.getId().equals(id))
				return observacoesPadrao;
		return null;
	}

}