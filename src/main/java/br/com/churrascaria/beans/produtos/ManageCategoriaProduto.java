package br.com.churrascaria.beans.produtos;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.beans.AbstractBean;
import br.com.churrascaria.beans.EnderecoPaginas;
import br.com.churrascaria.entities.CategoriaProduto;
import br.com.churrascaria.filter.CategoriaProdutoFilter;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.CategoriaProdutoServiceImplementacao;

@Named
@ViewScoped
public class ManageCategoriaProduto extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaProdutoServiceImplementacao categoriaProdutoService;

	private List<CategoriaProduto> categoriaProdutos;

	private CategoriaProdutoFilter categoriaProdutoFilter;

	private CategoriaProduto novaCategoriaProduto;

	public List<CategoriaProduto> getCategoriaProdutos() {
		return categoriaProdutos;
	}

	public CategoriaProdutoFilter getCategoriaProdutoFilter() {
		return categoriaProdutoFilter;
	}

	public void setCategoriaProdutoFilter(CategoriaProdutoFilter categoriaProdutoFilter) {
		this.categoriaProdutoFilter = categoriaProdutoFilter;
	}

	public CategoriaProduto getNovaCategoriaProduto() {
		return novaCategoriaProduto;
	}

	public void setNovaCategoriaProduto(CategoriaProduto novaCategoriaProduto) {
		this.novaCategoriaProduto = novaCategoriaProduto;
	}

	public void setCategoriaProdutos(List<CategoriaProduto> categoriaProdutos) {
		this.categoriaProdutos = categoriaProdutos;
	}

	@PostConstruct
	public void init() {
		novaCategoriaProduto = new CategoriaProduto();
		categoriaProdutoFilter = new CategoriaProdutoFilter();
		filtrar();
	}

	public String filtrar() {
		ArrayList<CategoriaProduto> array = new ArrayList<CategoriaProduto>();
		CategoriaProduto categoriaProduto = new CategoriaProduto();
		categoriaProduto.setPrimeiro(true);
		array.add(categoriaProduto);
		categoriaProdutos = array;
		try {
			array.addAll(categoriaProdutoService.getAll());
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String delete(CategoriaProduto categoriaProduto) {
		try {
			categoriaProdutoService.delete(categoriaProduto);
			reportarMensagemDeSucesso("Categoria de Produto '" + categoriaProduto.getNome() + "' excluída");
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return EnderecoPaginas.PAGINA_PRINCIPAL_CATEGORIAPRODUTO;

	}

	public String saveCategoriaProduto() {
		try {
			categoriaProdutoService.save(novaCategoriaProduto);
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Categoria de Produto '" + novaCategoriaProduto.getNome() + "' salva");

		return EnderecoPaginas.PAGINA_PRINCIPAL_CATEGORIAPRODUTO;
	}

}
