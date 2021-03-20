package br.com.churrascaria.beans.produtos;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.beans.AbstractBean;
import br.com.churrascaria.beans.EnderecoPaginas;
import br.com.churrascaria.entities.CategoriaProduto;
import br.com.churrascaria.entities.ObservacaoPadrao;
import br.com.churrascaria.entities.Produto;
import br.com.churrascaria.entities.ProdutoPadrao;
import br.com.churrascaria.services.CRUDService;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.CategoriaProdutoServiceImplementacao;
import br.com.churrascaria.services.implementacao.ProdutoServiceImplementacao;

@ViewScoped
@Named
public class ProdutoPadraoEdit extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProdutoPadrao produto;
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

	public String getLocalCategoriaProduto() {
		String localCategoriaProduto ;
		localCategoriaProduto = "produtos_da_categoria.xhtml?faces-redirect=true&amp;categoria="
				+ categoriaProduto.getId();
		return localCategoriaProduto;
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
				produto = new ProdutoPadrao();
				if (categoriaProduto != null) {
					produto.setCategoriaProduto(categoriaProdutoService.getByID(categoriaProduto.getId()));
				}
			} else {
				Produto p = produtoServiceImplementacao.getByID(produto.getId());
				if (p.getClass() == ProdutoPadrao.class) {
					produto = (ProdutoPadrao) p;
					listaObservacoesProduto.addAll(produto.getObservacoesPadrao());
				} else {
					produto = new ProdutoPadrao();
					reportarMensagemDeErro("Não foi possivel recuperar o produto");
				}
			}
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
		}
		return null;
	}

	private List<ObservacaoPadrao> listaObservacoesProduto = new ArrayList<>();

	public List<ObservacaoPadrao> getListaObservacoesProduto() {
		return listaObservacoesProduto;
	}

	public void setListaObservacoesProduto(List<ObservacaoPadrao> listaObservacoesProduto) {
		this.listaObservacoesProduto = listaObservacoesProduto;
	}

	public String saveProduto() {
		try {
			produto.setObservacoesPadrao(listaObservacoesProduto);
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

	public ProdutoPadrao getProduto() {
		return produto;
	}

	public void setProduto(ProdutoPadrao produto) {
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
