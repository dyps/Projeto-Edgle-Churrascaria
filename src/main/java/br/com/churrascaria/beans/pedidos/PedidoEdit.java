package br.com.churrascaria.beans.pedidos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.beans.AbstractBean;
import br.com.churrascaria.beans.EnderecoPaginas;
import br.com.churrascaria.entities.CategoriaProduto;
import br.com.churrascaria.entities.Cliente;
import br.com.churrascaria.entities.Entrega;
import br.com.churrascaria.entities.Item;
import br.com.churrascaria.entities.Mesa;
import br.com.churrascaria.entities.ObservacaoPadrao;
import br.com.churrascaria.entities.Pagamento;
import br.com.churrascaria.entities.Pedido;
import br.com.churrascaria.entities.Produto;
import br.com.churrascaria.entities.ProdutoPadrao;
import br.com.churrascaria.entities.ProdutoPersonalizado;
import br.com.churrascaria.entities.TipoDePedido;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.CategoriaProdutoServiceImplementacao;
import br.com.churrascaria.services.implementacao.PedidoServiceImplementacao;
import br.com.churrascaria.services.implementacao.ProdutoServiceImplementacao;

@Named
@ViewScoped
public class PedidoEdit extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProdutoServiceImplementacao produtoServiceImplementacao;

	@Inject
	private PedidoServiceImplementacao pedidoServiceImplementacao;

	@Inject
	private CategoriaProdutoServiceImplementacao categoriaProdutoServiceImplementacao;

	private List<CategoriaProduto> categoriasDeProdutos;

	private Cliente cliente;

	private Mesa mesa;

	private Pedido pedido;

	private CategoriaProduto categoriaSelecionada = new CategoriaProduto();

	private List<? extends Produto> listProdutos;

	private Produto produtoSelecionado;

	private Item itemNovo;

	private List<Item> listItems;
	
	private List<Item> listaItemsSelecionados;
	
	private Pagamento pagamento;
	
	private Entrega entrega;
	
	private boolean editavel = true;
	
	public List<Item> getListaItemsSelecionados() {
		return listaItemsSelecionados;
	}

	public void setListaItemsSelecionados(List<Item> listaItemsSelecionados) {
		this.listaItemsSelecionados = listaItemsSelecionados;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	public boolean isEditavel() {
		return editavel;
	}

	public void setEditavel(boolean editavel) {
		this.editavel = editavel;
	}


	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
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

	public void addItem() {
		try {
			itemNovo.setProduto(produtoSelecionado);
			itemNovo.setValor(pedidoServiceImplementacao.getValorItem(itemNovo));
			pedidoServiceImplementacao.validar(itemNovo);
			listItems.add(itemNovo);
			itemNovo = new Item();
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public boolean isEdicaoDePedido() {
		return pedido != null && pedido.getId() != null;
	}

	public void init() {
		listItems = new ArrayList<Item>();
		itemNovo = new Item();
		try {
			if (isEdicaoDePedido()) {
				pedido = pedidoServiceImplementacao.getByID(pedido.getId());
				listItems = pedido.getItens();
				cliente = pedido.getCliente();

			} else {
				pedido = new Pedido();
				if (mesa!=null) {
					pedido.setTipoDePedido(TipoDePedido.MESA);
				}
			}
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
		this.categoriaSelecionada = categoriaSelecionada;
		atualizarProdutos();
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

	public ProdutoPadrao toProdutoPadrao(Produto produto) {
		return (ProdutoPadrao) produto;

	}

	public ProdutoPersonalizado toProdutoPersonalizado(Produto produto) {
		return (ProdutoPersonalizado) produto;

	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}


	public void setProdutoSelecionado(Produto produtoSelecionado) {
		try {
			this.produtoSelecionado = produtoServiceImplementacao.getByID(produtoSelecionado.getId());
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public Item getItemNovo() {
		return itemNovo;
	}

	public void setItemNovo(Item itemNovo) {
		this.itemNovo = itemNovo;
	}

	public List<Item> getListItems() {
		return listItems;
	}

	public void setListItems(List<Item> listItems) {
		this.listItems = listItems;
	}
	public String listaObservacoes(Item item) {
		String retorno = "";
		List<ObservacaoPadrao> listaobs = item.getListObservacoes();
		boolean primeiro = true;
		for (Iterator<ObservacaoPadrao> iterator = listaobs.iterator(); iterator.hasNext();) {
			ObservacaoPadrao observacaoPadrao = (ObservacaoPadrao) iterator.next();
			if (!primeiro) {
				retorno+=", ";
			}
			primeiro= false;
			retorno+=observacaoPadrao.getDescricao();
				
		}
		return retorno;
	}


}
