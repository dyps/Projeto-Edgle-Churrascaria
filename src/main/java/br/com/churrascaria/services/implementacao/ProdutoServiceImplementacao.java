package br.com.churrascaria.services.implementacao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.dao.EntidadeDAO;
import br.com.churrascaria.dao.ObservacoesPadraoDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.dao.ProdutoDAO;
import br.com.churrascaria.entities.ItemDeConfiguracao;
import br.com.churrascaria.entities.ObservacaoPadrao;
import br.com.churrascaria.entities.Opcao;
import br.com.churrascaria.entities.Produto;
import br.com.churrascaria.entities.ProdutoPadrao;
import br.com.churrascaria.entities.ProdutoPersonalizado;
import br.com.churrascaria.services.CRUDService;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.util.TransacionalCdi;

@Named
@RequestScoped
public class ProdutoServiceImplementacao extends CRUDService<Produto> {

	@Override
	protected void validar(Produto produto) throws ServiceEdgleChurrascariaException {
		if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
			throw new ServiceEdgleChurrascariaException("O nome do produto é necessário");
		}
		List<Produto> list = getAll();
		for (Produto produtos : list) {
			if (produtos.getNome().equals(produto.getNome())) {
				if (produtos.getId().equals(produto.getId()))
					return;
				else
					throw new ServiceEdgleChurrascariaException("O nome do produto não pode ser repetido");
			}
		}
		if (produto.getCategoriaProduto() == null) {
			throw new ServiceEdgleChurrascariaException("É necessário informar uma categoria para o produto");
		}

		// validacao ProdPadrao
		if (produto.getClass() == ProdutoPadrao.class) {
			ProdutoPadrao produtoPd = (ProdutoPadrao) produto;
			if (produtoPd.getValorDeVenda() == null || produtoPd.getValorDeVenda() < 0) {
				throw new ServiceEdgleChurrascariaException("O preço de venda é necessário");
			}
			if (produtoPd.getMedida() == null) {
				throw new ServiceEdgleChurrascariaException("Uma medida é necessária");
			}
		}
		// validacao ProdPerso
		if (produto.getClass() == ProdutoPersonalizado.class) {
			ProdutoPersonalizado produtoPerso = (ProdutoPersonalizado) produto;

			if (produtoPerso.getItensDeConfiguracao() == null || produtoPerso.getItensDeConfiguracao().size() == 0) {
				throw new ServiceEdgleChurrascariaException("Produto personalizado deve possuir um item de configuração");
			}
			for (ItemDeConfiguracao itemDeConfiguracao : produtoPerso.getItensDeConfiguracao()) {
				if (itemDeConfiguracao.getOpcoes() == null || itemDeConfiguracao.getOpcoes().size() == 0) {
					throw new ServiceEdgleChurrascariaException(
							"Item " + itemDeConfiguracao.getNome() + " deve possuir uma opção");
				}
			}
		}

	}

	@Override
	public Produto getByID(Long id) throws ServiceEdgleChurrascariaException {
		try {
			return entidadeDAO.getByID(id);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	@Inject
	private ProdutoDAO entidadeDAO;

	@Override
	protected EntidadeDAO<Produto> getEntidadeDAO() {
		return entidadeDAO;
	}

	public void validar(ItemDeConfiguracao itemDeConfiguracao) throws ServiceEdgleChurrascariaException {
		if (itemDeConfiguracao.getNome() == null || itemDeConfiguracao.getNome().trim().isEmpty()) {
			throw new ServiceEdgleChurrascariaException("Um nome para o item de configuração é necessário");
		}
		if (itemDeConfiguracao.getQuantidadeMaxEscolhas() == null
				|| itemDeConfiguracao.getQuantidadeMaxEscolhas() <= 0) {
			throw new ServiceEdgleChurrascariaException("É necessário informar uma quantidade máxima de escolhas do item de configuração");
		}

	}

	public void validar(Opcao opcao) throws ServiceEdgleChurrascariaException {
		if (opcao.getNome() == null || opcao.getNome().trim().isEmpty()) {
			throw new ServiceEdgleChurrascariaException("Um nome para a opção é necessário");
		}
		if (opcao.getValorDeVenda() == null || opcao.getValorDeVenda() < 0) {
			throw new ServiceEdgleChurrascariaException("É necessário informar o valor de venda da opção");
		}

	}

	@Override
	@TransacionalCdi
	public Produto update(Produto novoProduto) throws ServiceEdgleChurrascariaException {
		try {
			validar(novoProduto);

			if (novoProduto.getClass() == ProdutoPersonalizado.class) {

				ProdutoPersonalizado novoProdutoPersonalizado = (ProdutoPersonalizado) novoProduto;
				ProdutoPersonalizado produto = (ProdutoPersonalizado) getByID(novoProduto.getId());

				for (int i = 0; i < produto.getItensDeConfiguracao().size(); i++) {
					ItemDeConfiguracao itemDeConfiguracao = produto.getItensDeConfiguracao().get(i);

					if (novoProdutoPersonalizado.getItensDeConfiguracao().contains(itemDeConfiguracao)) {

						for (int j = 0; j < itemDeConfiguracao.getOpcoes().size(); j++) {
							Opcao opcao = itemDeConfiguracao.getOpcoes().get(j);
							if (!novoProdutoPersonalizado.getItensDeConfiguracao().get(i).getOpcoes().contains(opcao)) {
								delete(opcao);
							}
						}
					} else {
						delete(itemDeConfiguracao);
					}
				}
			}
			return getEntidadeDAO().update(novoProduto);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	@Inject
	private ObservacoesPadraoDAO observacaoPadraoDao;

	private ObservacaoPadrao getObservacaoById(Long id) throws PersistenciaEdgleChurrascariaException {
		return observacaoPadraoDao.getByID(id);
	}

	public ItemDeConfiguracao getItemByID(Long id) throws ServiceEdgleChurrascariaException {
		try {
			return entidadeDAO.getItemByID(id);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	public void delete(Opcao entidade) throws ServiceEdgleChurrascariaException {
		try {
			entidadeDAO.delete(entidade);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	public void delete(ItemDeConfiguracao entidade) throws ServiceEdgleChurrascariaException {
		try {
			entidadeDAO.delete(entidade);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

}
