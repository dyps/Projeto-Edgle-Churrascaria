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

import br.com.churrascaria.dao.BandeiraDeCartaoDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.BandeiraDeCartao;

@Named
@RequestScoped
public class BandeiraDeCartaoDAOImplementacao extends InDatabaseDAO implements BandeiraDeCartaoDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void save(BandeiraDeCartao observacao) throws PersistenciaEdgleChurrascariaException {
		EntityManager en = getEntityManager();
		try {
			en.persist(observacao);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar salvar a bandeira.", pe);
		}

	}

	@Override
	public List<BandeiraDeCartao> getAll() throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<BandeiraDeCartao> criteriaQuery = criteriaBuilder.createQuery(BandeiraDeCartao.class);
		Root<BandeiraDeCartao> root = criteriaQuery.from(BandeiraDeCartao.class);

		criteriaQuery.select(root);

		TypedQuery<BandeiraDeCartao> typedQuery = em.createQuery(criteriaQuery);

		List<BandeiraDeCartao> resultado = typedQuery.getResultList();
		return resultado;
	}

	@Override
	public BandeiraDeCartao update(BandeiraDeCartao observacao) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		BandeiraDeCartao resultado = observacao;
		try {
			resultado = em.merge(observacao);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar atualizar a bandeira.",
					pe);
		}
		return resultado;
	}

	public void delete(BandeiraDeCartao obj) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		try {
			obj = em.find(BandeiraDeCartao.class, obj.getId());
			em.remove(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar remover a bandeira.", pe);
		}

	}

	@Override
	public BandeiraDeCartao getByID(Long id) throws PersistenciaEdgleChurrascariaException {
		List<BandeiraDeCartao> lista = getAll();
		for (BandeiraDeCartao observacoesPadrao : lista)
			if (observacoesPadrao.getId().equals(id))
				return observacoesPadrao;
		return null;
	}

}