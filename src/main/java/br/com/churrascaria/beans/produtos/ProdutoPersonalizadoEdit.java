package br.com.churrascaria.beans.produtos;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.beans.AbstractBean;
import br.com.churrascaria.beans.EnderecoPaginas;
import br.com.churrascaria.entities.CategoriaProduto;
import br.com.churrascaria.entities.ItemDeConfiguracao;
import br.com.churrascaria.entities.ObservacaoPadrao;
import br.com.churrascaria.entities.Opcao;
import br.com.churrascaria.entities.Produto;
import br.com.churrascaria.entities.ProdutoPersonalizado;
import br.com.churrascaria.services.CRUDService;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.CategoriaProdutoServiceImplementacao;
import br.com.churrascaria.services.implementacao.ProdutoServiceImplementacao;

@ViewScoped
@Named
public class ProdutoPersonalizadoEdit extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProdutoPersonalizado produto;
	@Inject
	private CategoriaProdutoServiceImplementacao categoriaProdutoService;

	private CategoriaProduto categoriaProduto;

	@Inject
	private ProdutoServiceImplementacao produtoServiceImplementacao;

	private List<String> categoriasProduto;

	@Inject
	private CRUDService<ObservacaoPadrao> observacoesPadraoService;

	private List<ObservacaoPadrao> observacoesPadrao;

	public List<ObservacaoPadrao> getObservacoesPadrao() {
		return observacoesPadrao;
	}

	public List<String> getCategoriasProduto() {
		return categoriasProduto;
	}

	public void setObservacoesPadrao(List<ObservacaoPadrao> observacoesPadrao) {
		this.observacoesPadrao = observacoesPadrao;
	}
	public String getLocalCategoriaProduto() {
		String localCategoriaProduto ;
		localCategoriaProduto = "produtos_da_categoria.xhtml?faces-redirect=true&amp;categoria="
				+ categoriaProduto.getId();
		return localCategoriaProduto;
	}

	private String categoriaProdutoCriado;

	public void setCategoriaProdutoCriado(String cat) {
		categoriaProdutoCriado = cat;
		for (CategoriaProduto categoriaProd : categoriaProdutosAuxiliar) {
			if (categoriaProd.getNome().equals(cat)) {
				produto.setCategoriaProduto(categoriaProd);
			}
		}
	}

	public String getCategoriaProdutoCriado() {
		return categoriaProdutoCriado;
	}

	private List<CategoriaProduto> categoriaProdutosAuxiliar;

	private ItemDeConfiguracao itemDeConfiguracao;

	private List<ItemDeConfiguracao> listaDeItemDeConfiguracao;

	private Opcao novaOpcao;

	public Opcao getNovaOpcao() {
		return novaOpcao;
	}

	public void setNovaOpcao(Opcao novaOpcao) {
		this.novaOpcao = novaOpcao;
	}

	public ItemDeConfiguracao getItemDeConfiguracao() {
		return itemDeConfiguracao;
	}

	public void setItemDeConfiguracao(ItemDeConfiguracao itemDeConfiguracao) {
		this.itemDeConfiguracao = itemDeConfiguracao;
	}

	public void editarItem(ItemDeConfiguracao item) {
		itemDeConfiguracao = item;
	}

	public void novoItem() {
		itemDeConfiguracao = new ItemDeConfiguracao();
		itemDeConfiguracao.setProdutoPersonalizado(produto);
	}

	public void deleteOpcao(Opcao opcao) {
		itemDeConfiguracao.getOpcoes().remove(opcao);
	}

	public void deleteItem(ItemDeConfiguracao item) {
		listaDeItemDeConfiguracao.remove(item);
	}

	public void saveOpcao() {
		try {
			produtoServiceImplementacao.validar(novaOpcao);
			novaOpcao.setItemDeConfiguracao(itemDeConfiguracao);
			if (itemDeConfiguracao.getOpcoes() == null)
				itemDeConfiguracao.setOpcoes(new ArrayList<Opcao>());
			itemDeConfiguracao.getOpcoes().add(novaOpcao);
			novaOpcao = new Opcao();
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public void saveItem() {
		try {
			produtoServiceImplementacao.validar(itemDeConfiguracao);
			if (!listaDeItemDeConfiguracao.contains(itemDeConfiguracao))
				listaDeItemDeConfiguracao.add(itemDeConfiguracao);
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
		}

	}

	public String init() {
		try {
			if (categoriaProduto != null)
				categoriaProdutoCriado = categoriaProduto.getNome();
			observacoesPadrao = observacoesPadraoService.getAll();
			categoriaProdutosAuxiliar = categoriaProdutoService.getAll();
			categoriasProduto = new ArrayList<>();
			for (CategoriaProduto categoriaProduto : categoriaProdutosAuxiliar) {
				categoriasProduto.add(categoriaProduto.getNome());
			}
			if (produto == null) {
				produto = new ProdutoPersonalizado();
				if (categoriaProduto != null) {
					produto.setCategoriaProduto(categoriaProdutoService.getByID(categoriaProduto.getId()));
				}
			} else {
				Produto p = produtoServiceImplementacao.getByID(produto.getId());
				if (p.getClass() == ProdutoPersonalizado.class) {
					produto = (ProdutoPersonalizado) p;
					listaObservacoesProduto.addAll(produto.getObservacoesPadrao());
				} else {
					produto = new ProdutoPersonalizado();
					reportarMensagemDeErro("Não foi possivel recuperar o produto");
				}
			}
			if (produto.getItensDeConfiguracao() == null)
				produto.setItensDeConfiguracao(new ArrayList<>());
			listaDeItemDeConfiguracao = produto.getItensDeConfiguracao();
			novoItem();
			novaOpcao = new Opcao();
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
		}
		return null;
	}

	public List<ObservacaoPadrao> getListaObservacoesProduto() {
		return listaObservacoesProduto;
	}

	public void setListaObservacoesProduto(List<ObservacaoPadrao> listaObservacoesProduto) {
		this.listaObservacoesProduto = listaObservacoesProduto;
	}

	public List<ItemDeConfiguracao> getListaDeItemDeConfiguracao() {
		return listaDeItemDeConfiguracao;
	}

	public void setListaDeItemDeConfiguracao(List<ItemDeConfiguracao> listaDeItemDeConfiguracao) {
		this.listaDeItemDeConfiguracao = listaDeItemDeConfiguracao;
	}

	private List<ObservacaoPadrao> listaObservacoesProduto = new ArrayList<>();

	public String saveProduto() {
		try {
			produto.setObservacoesPadrao(listaObservacoesProduto);
			produto.setItensDeConfiguracao(listaDeItemDeConfiguracao);
			if (isEdicaoDeFuncionario()) {
				produtoServiceImplementacao.update(produto);
			} else {
				produtoServiceImplementacao.save(produto);
			}
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Produto '" + produto.getNome() + "' salvo");

		return EnderecoPaginas.PAGINA_PRINCIPAL_CATEGORIAPRODUTO;
	}

	public boolean isEdicaoDeFuncionario() {
		return produto != null && produto.getId() != null;
	}

	public ProdutoPersonalizado getProduto() {
		return produto;
	}

	public void setProduto(ProdutoPersonalizado produto) {
		this.produto = produto;
	}

	public boolean isEdicaoDeProduto() {
		return produto != null && produto.getId() != null;
	}

	public CategoriaProduto getCategoriaProduto() {
		return categoriaProduto;
	}

	public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
		this.categoriaProduto = categoriaProduto;
	}

}
