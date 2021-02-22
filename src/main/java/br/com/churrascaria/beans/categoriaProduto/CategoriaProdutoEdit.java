package br.com.churrascaria.beans.categoriaProduto;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.beans.AbstractBean;
import br.com.churrascaria.beans.EnderecoPaginas;
import br.com.churrascaria.entities.CategoriaProduto;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.CategoriaProdutoServiceImplementacao;

@ViewScoped
@Named
public class CategoriaProdutoEdit extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CategoriaProdutoServiceImplementacao categoriaProdutoService;
	
	private CategoriaProduto categoriaProduto;
	
	public String init() {
		try {
			if (categoriaProduto == null) {
				categoriaProduto = new CategoriaProduto();
			} else {
				categoriaProduto = categoriaProdutoService.getByID(categoriaProduto.getId());
			}
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
		}
		return null;
	}
	
	public String saveCategoriaProduto() {
		try {
			if (isEdicaoDeCategoriaProduto()) {
				categoriaProdutoService.update(categoriaProduto);
			} else {
				categoriaProdutoService.save(categoriaProduto);
			}
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Categoria de Produto '" + categoriaProduto.getNome() + "' salva");

		return EnderecoPaginas.PAGINA_PRINCIPAL_CATEGORIAPRODUTO;
	}
	
	public boolean isEdicaoDeCategoriaProduto() {
		return categoriaProduto != null && categoriaProduto.getId() != null;
	}

	public CategoriaProduto getCategoriaProduto() {
		return categoriaProduto;
	}

	public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
		this.categoriaProduto = categoriaProduto;
	}

}
