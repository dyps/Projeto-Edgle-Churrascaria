package br.com.churrascaria.beans.produtos;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.beans.AbstractBean;
import br.com.churrascaria.beans.EnderecoPaginas;
import br.com.churrascaria.entities.CategoriaProduto;
import br.com.churrascaria.entities.ProdutoPadrao;
import br.com.churrascaria.entities.ProdutoPersonalizado;
import br.com.churrascaria.filter.ProdutoFilter;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.CategoriaProdutoServiceImplementacao;

@ViewScoped
@Named
public class ProdutosDaCategoria extends AbstractBean {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CategoriaProduto categoriaProduto;

	private ProdutoFilter produtoFilter;

	private List<ProdutoPadrao> produtosPadrao;
	private List<ProdutoPersonalizado> produtosPersonalizado;
	public Class<ProdutoPadrao> getTipoProdutoPadrao() {
		return ProdutoPadrao.class;

	}
	public Class<ProdutoPersonalizado> getTipoProdutoPersonalizado() {
		return ProdutoPersonalizado.class;

	}

	@Inject
	private CategoriaProdutoServiceImplementacao categoriaProdutoService;

	public String init() {
		produtoFilter = new ProdutoFilter();
		try {
			if (categoriaProduto == null) {
				return EnderecoPaginas.PAGINA_PRINCIPAL_CATEGORIAPRODUTO;
			} else {
				categoriaProduto = categoriaProdutoService.getByID(categoriaProduto.getId());
			}
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
		}
		produtoFilter.setIdCategoria(categoriaProduto.getId());
		filtrar();
		return null;
	}

	public String filtrar() {
		try {
			produtosPadrao = categoriaProdutoService.getByIDProdutosPadrao(produtoFilter);
			produtosPersonalizado = categoriaProdutoService.getByIDProdutoPersonalizado(produtoFilter);
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;

	}

	public CategoriaProduto getCategoriaProduto() {
		return categoriaProduto;
	}

	public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
		this.categoriaProduto = categoriaProduto;
	}

	public ProdutoFilter getProdutoFilter() {
		return produtoFilter;
	}

	public void setProdutoFilter(ProdutoFilter produtoFilter) {
		this.produtoFilter = produtoFilter;
	}

	public List<ProdutoPadrao> getProdutosPadrao() {
		return produtosPadrao;
	}

	public void setProdutosPadrao(List<ProdutoPadrao> produtosPadrao) {
		this.produtosPadrao = produtosPadrao;
	}

	public List<ProdutoPersonalizado> getProdutosPersonalizado() {
		return produtosPersonalizado;
	}

	public void setProdutosPersonalizado(List<ProdutoPersonalizado> produtosPersonalizado) {
		this.produtosPersonalizado = produtosPersonalizado;
	}
	

}
