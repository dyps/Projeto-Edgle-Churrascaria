package br.com.churrascaria.services.implementacao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.dao.CategoriaProdutoDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.CategoriaProduto;
import br.com.churrascaria.filter.CategoriaProdutoFilter;
import br.com.churrascaria.services.CRUDService;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;

@Named
@RequestScoped
public class CategoriaProdutoServiceImplementacao implements CRUDService<CategoriaProduto> {
	
	@Inject
	private CategoriaProdutoDAO categoriaProdutoDAO;

	@Override
	public void save(CategoriaProduto categoriaProduto) throws ServiceEdgleChurrascariaException {
		try {
			categoriaProdutoDAO.save(categoriaProduto);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	@Override
	public CategoriaProduto update(CategoriaProduto categoriaProduto) throws ServiceEdgleChurrascariaException {
		try {
			return categoriaProdutoDAO.update(categoriaProduto);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	@Override
	public void delete(CategoriaProduto categoriaProduto) throws ServiceEdgleChurrascariaException {
		try {
			categoriaProdutoDAO.delete(categoriaProduto);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	public CategoriaProduto getByID(Long userId) throws ServiceEdgleChurrascariaException {
		CategoriaProdutoFilter filter = new CategoriaProdutoFilter();
		filter.setId(userId);
		return findBy(filter).get(0);
	}

	public List<CategoriaProduto> findBy(CategoriaProdutoFilter filter) throws ServiceEdgleChurrascariaException {
		try {
			filter.validate();
			return categoriaProdutoDAO.findBy(filter);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	@Override
	public List<CategoriaProduto> getAll() throws ServiceEdgleChurrascariaException {
		try {
			return categoriaProdutoDAO.getAll();
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

}
