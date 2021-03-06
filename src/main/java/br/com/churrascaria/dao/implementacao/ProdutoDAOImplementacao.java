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
import br.com.churrascaria.dao.ProdutoDAO;
import br.com.churrascaria.entities.ItemDeConfiguracao;
import br.com.churrascaria.entities.Opcao;
import br.com.churrascaria.entities.Produto;

@Named
@RequestScoped
public class ProdutoDAOImplementacao extends InDatabaseDAO implements ProdutoDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void save(Produto produto) throws PersistenciaEdgleChurrascariaException {
		EntityManager en = getEntityManager();
		try {
			en.persist(produto);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar salvar o produto.", pe);
		}

	}

	@Override
	public List<Produto> getAll() throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
		Root<Produto> root = criteriaQuery.from(Produto.class);

		criteriaQuery.select(root);

		TypedQuery<Produto> typedQuery = em.createQuery(criteriaQuery);

		List<Produto> resultado = typedQuery.getResultList();
		return resultado;
	}

	@Override
	public Produto update(Produto produto) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		Produto resultado = produto;
		try {
			resultado = em.merge(produto);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar atualizar o produto.", pe);
		}
		return resultado;
	}

	public void delete(Produto obj) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		try {
			obj = em.find(Produto.class, obj.getId());
			em.remove(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar remover o produto.", pe);
		}

	}

	@Override
	public Produto getByID(Long id) throws PersistenciaEdgleChurrascariaException {
		List<Produto> lista = getAll();
		for (Produto observacoesPadrao : lista)
			if (observacoesPadrao.getId().equals(id))
				return observacoesPadrao;
		return null;
	}

	@Override
	public void delete(Opcao obj) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		try {
			obj = em.find(Opcao.class, obj.getId());
			em.remove(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException("Ocorreu algum erro ao tentar remover a Opcao.", pe);
		}

	}

	@Override
	public void delete(ItemDeConfiguracao obj) throws PersistenciaEdgleChurrascariaException {
		EntityManager em = getEntityManager();
		try {
			obj = em.find(ItemDeConfiguracao.class, obj.getId());
			em.remove(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaEdgleChurrascariaException(
					"Ocorreu algum erro ao tentar remover o Item De Configuracao.", pe);
		}
	}

	@Override
	public ItemDeConfiguracao getItemByID(Long id) throws PersistenciaEdgleChurrascariaException {

		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<ItemDeConfiguracao> criteriaQuery = criteriaBuilder.createQuery(ItemDeConfiguracao.class);
		Root<ItemDeConfiguracao> root = criteriaQuery.from(ItemDeConfiguracao.class);

		ArrayList<Predicate> criaPpredicate = new ArrayList<Predicate>();
		criaPpredicate.add(criteriaBuilder.equal(root.get("Id"), id));
		Predicate[] p = criaPpredicate.toArray(new Predicate[0]);
		criteriaQuery.select(root);
		criteriaQuery.where(p);

		TypedQuery<ItemDeConfiguracao> typedQuery = em.createQuery(criteriaQuery);

		ItemDeConfiguracao resultado = typedQuery.getSingleResult();
		return resultado;
	}

}