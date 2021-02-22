package br.com.churrascaria.services.implementacao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.dao.CategoriaProdutoDAO;
import br.com.churrascaria.dao.EntidadeDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.CategoriaProduto;
import br.com.churrascaria.filter.CategoriaProdutoFilter;
import br.com.churrascaria.services.CRUDService;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;

@Named
@RequestScoped
public class CategoriaProdutoServiceImplementacao extends CRUDService<CategoriaProduto> {

	@Inject
	private CategoriaProdutoDAO categoriaProdutoDAO;

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
	protected EntidadeDAO<CategoriaProduto> getEntidadeDAO() {
		return categoriaProdutoDAO;
	}

	@Override
	protected void validar(CategoriaProduto entidade) throws ServiceEdgleChurrascariaException {
		if (entidade == null || entidade.getNome() == null || entidade.getNome().equals("") ) {
			throw new ServiceEdgleChurrascariaException("O nome da categoria é necessário");
		}
		List<CategoriaProduto> list = getAll();
        for (CategoriaProduto categoriaProduto : list) {
            if(categoriaProduto.getNome().toLowerCase().equals(entidade.getNome().toLowerCase()))
                throw new ServiceEdgleChurrascariaException("O nome da categoria não pode ser repetida");
        }
	}

}
