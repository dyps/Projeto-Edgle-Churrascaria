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

import br.com.churrascaria.dao.ItemDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.AcaoRealizada;
import br.com.churrascaria.entities.Item;

@Named
@RequestScoped
public class ItemDAOImplementacao extends InDatabaseDAO implements ItemDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void save(Item item) throws PersistenciaEdgleChurrascariaException {
		EntityManager en = getEntityManager();
		try {
			en.persist(item);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar salvar o item.", pe);
		}

	}

	@Override
	public List<Item> getAll() throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
		Root<Item> root = criteriaQuery.from(Item.class);

		criteriaQuery.select(root);

		TypedQuery<Item> typedQuery = em.createQuery(criteriaQuery);

		List<Item> resultado = typedQuery.getResultList();
		return resultado;
	}

	@Override
	public Item update(Item item) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		Item resultado = item;
		try {
			resultado = em.merge(item);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar atualizar o item.", pe);
		}
		return resultado;
	}

	@Override
	public void delete(Item obj) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		try {
			obj = em.find(Item.class, obj.getId());
			em.remove(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar remover o item.", pe);
		}

	}

	@Override
	public Item getByID(Long id) throws PersistenciaEdgleChurrascariaException {
		List<Item> lista = getAll();
		for (Item observacoesPadrao : lista)
			if (observacoesPadrao.getId().equals(id))
				return observacoesPadrao;
		return null;
	}

	@Override
	public void novaAcao(AcaoRealizada acaoRealizada) throws PersistenciaEdgleChurrascariaException {
		EntityManager en = getEntityManager();
		try {
			en.persist(acaoRealizada);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar salvar a ação realizada.",
					pe);
		}
	}
}