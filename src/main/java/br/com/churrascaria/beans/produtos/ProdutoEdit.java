package br.com.churrascaria.beans.produtos;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.beans.AbstractBean;
import br.com.churrascaria.entities.CategoriaProduto;
import br.com.churrascaria.entities.Produto;
import br.com.churrascaria.entities.ProdutoPadrao;
import br.com.churrascaria.entities.ProdutoPersonalizado;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.CategoriaProdutoServiceImplementacao;
import br.com.churrascaria.services.implementacao.ProdutoServiceImplementacao;

@ViewScoped
@Named
public class ProdutoEdit extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Produto produto;
	@Inject
	private CategoriaProdutoServiceImplementacao categoriaProdutoService;

	private CategoriaProduto categoriaProduto;

	private ProdutoServiceImplementacao produtoServiceImplementacao;

	public String init() {
		try {
			if (produto == null) {
				produto = new ProdutoPadrao();
				if (categoriaProduto != null) {
					produto.setCategoriaProduto(categoriaProdutoService.getByID(categoriaProduto.getId()));
				}
			} else {
				produto = produtoServiceImplementacao.getByID(produto.getId());
			}
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
		}
		return null;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public void setTipo(Class<? extends Produto> tipo) {
		if (tipo == ProdutoPadrao.class) {
			produto = new ProdutoPadrao();
		}
		produto = new ProdutoPersonalizado();

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
