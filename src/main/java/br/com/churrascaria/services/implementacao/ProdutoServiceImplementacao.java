package br.com.churrascaria.services.implementacao;

import java.util.ArrayList;
import java.util.Arrays;

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

		// puxando as obs do banco
		ArrayList<ObservacaoPadrao> obsEscolhidas = new ArrayList<ObservacaoPadrao>();
		for (ObservacaoPadrao observacaoPadrao : produto.getObservacoesPadrao()) {
			try {
				obsEscolhidas.add(getObservacaoById(observacaoPadrao.getId()));
			} catch (PersistenciaEdgleChurrascariaException e) {
				throw new ServiceEdgleChurrascariaException(e.getMessage());
			}
		}
		produto.setObservacoesPadrao(obsEscolhidas);

		if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
			throw new ServiceEdgleChurrascariaException("Nome invalido");
		}
		if (produto.getCategoriaProduto() == null) {
			throw new ServiceEdgleChurrascariaException("Informe uma categoria");
		}

		// validacao ProdPadrao
		if (produto.getClass() == ProdutoPadrao.class) {
			ProdutoPadrao produtoPd = (ProdutoPadrao) produto;
			if (produtoPd.getValorDeVenda() == null || produtoPd.getValorDeVenda() < 0) {
				throw new ServiceEdgleChurrascariaException("Valor invalido");
			}
			if (produtoPd.getMedida() == null) {
				throw new ServiceEdgleChurrascariaException("Medida invalida");
			}
		}
		// validacao ProdPerso
		if (produto.getClass() == ProdutoPersonalizado.class) {
			ProdutoPersonalizado produtoPerso = (ProdutoPersonalizado) produto;

			if (produtoPerso.getItensDeConfiguracao() == null || produtoPerso.getItensDeConfiguracao().size() == 0) {
				throw new ServiceEdgleChurrascariaException("Produto deve ter um item de configuração");
			}
			for (ItemDeConfiguracao itemDeConfiguracao : produtoPerso.getItensDeConfiguracao()) {
				if (itemDeConfiguracao.getOpcoes() == null || itemDeConfiguracao.getOpcoes().size() == 0) {
					throw new ServiceEdgleChurrascariaException(
							"Item " + itemDeConfiguracao.getNome() + " deve ter uma opção");
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
			throw new ServiceEdgleChurrascariaException("Nome invalido");
		}
		if (itemDeConfiguracao.getQuantidadeMaxEscolhas() == null
				|| itemDeConfiguracao.getQuantidadeMaxEscolhas() <= 0) {
			throw new ServiceEdgleChurrascariaException("Quantidade invalida");

		}

	}

	public void validar(Opcao opcao) throws ServiceEdgleChurrascariaException {
		if (opcao.getNome() == null || opcao.getNome().trim().isEmpty()) {
			throw new ServiceEdgleChurrascariaException("Nome invalido");
		}
		if (opcao.getValorDeVenda() == null || opcao.getValorDeVenda() < 0) {
			throw new ServiceEdgleChurrascariaException("Valor invalido");
		}

	}

	@Override
	@TransacionalCdi
	public Produto update(Produto novoProduto) throws ServiceEdgleChurrascariaException {
		try {
			validar(novoProduto);

			System.out.println(Arrays.toString(novoProduto.getObservacoesPadrao().toArray()));
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
