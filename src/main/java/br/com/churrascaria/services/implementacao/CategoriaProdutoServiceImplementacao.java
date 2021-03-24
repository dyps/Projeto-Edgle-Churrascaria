package br.com.churrascaria.services.implementacao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.dao.CategoriaProdutoDAO;
import br.com.churrascaria.dao.EntidadeDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.CategoriaProduto;
import br.com.churrascaria.entities.Produto;
import br.com.churrascaria.entities.ProdutoPadrao;
import br.com.churrascaria.entities.ProdutoPersonalizado;
import br.com.churrascaria.filter.CategoriaProdutoFilter;
import br.com.churrascaria.filter.ProdutoFilter;
import br.com.churrascaria.services.CRUDService;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;

@Named
@RequestScoped
public class CategoriaProdutoServiceImplementacao extends CRUDService<CategoriaProduto> {

	@Inject
	private CategoriaProdutoDAO categoriaProdutoDAO;

	public CategoriaProduto getByID(Long Id) throws ServiceEdgleChurrascariaException {
		CategoriaProdutoFilter filter = new CategoriaProdutoFilter();
		filter.setId(Id);
		return findBy(filter).get(0);
	}

	public List<CategoriaProduto> findBy(CategoriaProdutoFilter filter) throws ServiceEdgleChurrascariaException {
		try {
			filter.validate();
			List<CategoriaProduto> list = categoriaProdutoDAO.findBy(filter);
			return list;
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
		if (entidade == null || entidade.getNome() == null || entidade.getNome().trim().equals(""))
			throw new ServiceEdgleChurrascariaException("O nome da categoria é necessário");

		List<CategoriaProduto> list = getAll();
		for (CategoriaProduto categoriaProduto : list)
			if (categoriaProduto.getNome().toLowerCase().equals(entidade.getNome().toLowerCase()))
				throw new ServiceEdgleChurrascariaException("O nome da categoria não pode ser repetida");

	}

	public List<ProdutoPadrao> getByIDProdutosPadrao(ProdutoFilter produtoFilter)
			throws ServiceEdgleChurrascariaException {
		List<Produto> list = buscarFiltrar(produtoFilter);
		List<ProdutoPadrao> retorno = new ArrayList<>();
		for (Produto produto : list)
			if (produto.getClass() == ProdutoPadrao.class)
				retorno.add((ProdutoPadrao) produto);
		return retorno;
	}

	public List<ProdutoPersonalizado> getByIDProdutoPersonalizado(ProdutoFilter produtoFilter)
			throws ServiceEdgleChurrascariaException {
		List<Produto> list = buscarFiltrar(produtoFilter);
		List<ProdutoPersonalizado> retorno = new ArrayList<>();
		for (Produto produto : list)
			if (produto.getClass() == ProdutoPersonalizado.class)
				retorno.add((ProdutoPersonalizado) produto);
		return retorno;
	}

	private List<Produto> buscarFiltrar(ProdutoFilter produtoFilter) throws ServiceEdgleChurrascariaException {
		List<Produto> list = new ArrayList<Produto>();
		List<Produto> a = getByID(produtoFilter.getIdCategoria()).getProdutos();
		list.addAll(a);
		List<Produto> retorno = new ArrayList<Produto>();
		for (Produto produto : list) {
			if (produtoFilter.getNome() == null)
				retorno.add(produto);
			else if (produto.getNome().contains(produtoFilter.getNome()))
				retorno.add(produto);
		}
		return retorno;
	}

}
