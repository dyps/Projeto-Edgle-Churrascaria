package br.com.churrascaria.beans.pedidos;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.beans.AbstractBean;
import br.com.churrascaria.beans.EnderecoPaginas;
import br.com.churrascaria.entities.CategoriaProduto;
import br.com.churrascaria.entities.Cliente;
import br.com.churrascaria.entities.Item;
import br.com.churrascaria.entities.Mesa;
import br.com.churrascaria.entities.Pedido;
import br.com.churrascaria.entities.Produto;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.CategoriaProdutoServiceImplementacao;
import br.com.churrascaria.services.implementacao.PedidoServiceImplementacao;

@Named
@ViewScoped
public class PedidoEdit extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Cliente cliente;

	private Mesa mesa;

	private List<Item> listItem;

	private Pedido pedido;

	private CategoriaProduto categoriaSelecionada = new CategoriaProduto();

	private List<? extends Produto> listProdutos;

	@Inject
	PedidoServiceImplementacao pedidoServiceImplementacao;

	@Inject
	private CategoriaProdutoServiceImplementacao categoriaProdutoServiceImplementacao;

	private List<CategoriaProduto> categoriasDeProdutos;

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public List<Item> getListItem() {
		return listItem;
	}

	public void setListItem(List<Item> listItem) {
		this.listItem = listItem;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String receberPorMesa() {

		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if (id == null) {
			return null;
		}
		return EnderecoPaginas.PAGINA_NOVO_PEDIDO + "&amp;mesa=" + id;

	}

	public void init() {
		try {
			categoriasDeProdutos = categoriaProdutoServiceImplementacao.getAll();
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public List<CategoriaProduto> getCategoriasDeProdutos() {
		return categoriasDeProdutos;
	}

	public CategoriaProduto getCategoriaSelecionada() {
		return categoriaSelecionada;
	}

	public void setCategoriaSelecionada(CategoriaProduto categoriaSelecionada) {
		atualizarProdutos();
		this.categoriaSelecionada = categoriaSelecionada;
	}

	private void atualizarProdutos() {
		try {
			List<? extends Produto> retornoDoBanco = categoriaProdutoServiceImplementacao
					.getByID(categoriaSelecionada.getId()).getProdutos();
			List<Produto> listHabilitados = new ArrayList<Produto>();
			for (Produto produto : retornoDoBanco)
				if (produto.isHabilitado())
					listHabilitados.add(produto);
			listProdutos = listHabilitados;
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public List<? extends Produto> getListProdutos() {
		return listProdutos;
	}

	public void setListProdutos(List<? extends Produto> listProdutos) {
		this.listProdutos = listProdutos;
	}

}
