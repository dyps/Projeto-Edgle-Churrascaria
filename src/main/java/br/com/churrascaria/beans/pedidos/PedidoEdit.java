package br.com.churrascaria.beans.pedidos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.beans.AbstractBean;
import br.com.churrascaria.beans.EnderecoPaginas;
import br.com.churrascaria.entities.AcaoRealizada;
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
import br.com.churrascaria.entities.TipoAcaoItemPedido;
import br.com.churrascaria.entities.TipoDeMedida;
import br.com.churrascaria.entities.TipoDePedido;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.CategoriaProdutoServiceImplementacao;
import br.com.churrascaria.services.implementacao.ClienteServiceImplementacao;
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

	@Inject
	private ClienteServiceImplementacao clienteServiceImplementacao;

	private List<CategoriaProduto> categoriasDeProdutos;

	private Mesa mesa;

	private Boolean delivery;

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

	private Item itemLupa;

	private List<Cliente> clientes;

	public boolean mostrarSearch(Item item) {
		if (item.getListAcaoRealizada() == null)
			return false;
		else
			return item.getListAcaoRealizada().size() > 0;
	}

	public boolean mostrarCheck(Item item) {
		return pedidoServiceImplementacao.podeSerEntregue(item);
	}

	public boolean mostrarPencil(Item item) {
		return false;
//		return pedidoServiceImplementacao.podeSerEditado(item);
	}

	public boolean mostrarTrash(Item item) {
		return pedidoServiceImplementacao.podeSerCancelado(item);
	}

	public void apertouSearch(Item item) {
		itemLupa = item;
	}

	public String apertouCheck(Item item) {
		try {
			if (!isEdicaoDePedido()) {
				List<Item> list = new ArrayList<Item>();
				list.add(item);
				pedido.setItens(list);
				pedido.setTipoDePedido(getTipoDePedido());
				pedidoServiceImplementacao.entregarItem(pedido, getFuncionarioLogado());
			} else {
				pedidoServiceImplementacao.entregarItem(pedido.getId(), item, getFuncionarioLogado());
			}
			reportarMensagemDeSucesso("Pronto! Enviado.");
			return EnderecoPaginas.PAGINA_NOVO_PEDIDO + "&amp;pedido=" + pedido.getId();
		} catch (ServiceEdgleChurrascariaException | IOException e) {
			reportarMensagemDeErro(e.getMessage());
		}
		return null;
	}

	public void apertouPencil(Item item) {
		reportarMensagemDeErro("Erro interno");
	}

	public void apertouTrash(Item item) {
		if (item.getId() == null)
			listItems.remove(item);
		else
			try {
				pedidoServiceImplementacao.cancelarItem(item, getFuncionarioLogado());
			} catch (ServiceEdgleChurrascariaException | IOException e) {
				reportarMensagemDeErro(e.getMessage());
			}

	}

	public String corItem(Item item) {
		if (Contem(item, TipoAcaoItemPedido.CANCELOU)) {
			return "#00000080";
		}
		if (Contem(item, TipoAcaoItemPedido.ENTREGOU)) {
			return "#4caf50bf";
		}
		if (Contem(item, TipoAcaoItemPedido.ALTEROU)) {
			return "#00BCD4";
		}
		if (Contem(item, TipoAcaoItemPedido.REALIZOUPEDIDO)) {
			return "#990000c2"; // #f91e4e
		}
		return "#ffffff";
	}

	private boolean Contem(Item item, TipoAcaoItemPedido acao) {
		List<AcaoRealizada> list = item.getListAcaoRealizada();
		if (list == null)
			return false;
		for (AcaoRealizada acaoRealizada : list) {
			if (acaoRealizada.getTipoAcaoItemPedido().equals(acao)) {
				return true;
			}
		}
		return false;
	}

	public List<Item> getListaItemsSelecionados() {
		return listaItemsSelecionados;
	}

	public void setListaItemsSelecionados(List<Item> listaItemsSelecionados) {
		this.listaItemsSelecionados = listaItemsSelecionados;
	}

	public boolean isTipoEQuilograma() {
		try {
			ProdutoPadrao p = (ProdutoPadrao) produtoSelecionado;
			if (p.getMedida() == TipoDeMedida.QUILOGRAMA) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
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

	public String receberPorMesa() {

		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if (id == null) {
			return null;
		}
		return EnderecoPaginas.PAGINA_NOVO_PEDIDO + "&amp;mesa=" + id;
	}

	public String receberPorDelivery() {
		return EnderecoPaginas.PAGINA_NOVO_PEDIDO + "&amp;delivery=true";
	}

	public String receberPorBalcao() {
		return EnderecoPaginas.PAGINA_NOVO_PEDIDO;

	}

	public String enviarParaACozinha() {
		try {
			if (listaItemsSelecionados == null || listaItemsSelecionados.size() == 0) {
				reportarMensagemDeErro("Nenhum item selecionado");
				return null;
			}
			if (!isEdicaoDePedido()) {
				pedido.setItens(listaItemsSelecionados);
				pedido.setTipoDePedido(getTipoDePedido());
				pedidoServiceImplementacao.enviarParaCozinha(pedido, getFuncionarioLogado());
			} else {
				pedidoServiceImplementacao.enviarParaCozinha(pedido.getId(), listaItemsSelecionados,
						getFuncionarioLogado());
			}
			reportarMensagemDeSucesso("Pronto! Enviado.");
			return EnderecoPaginas.PAGINA_NOVO_PEDIDO + "&amp;pedido=" + pedido.getId();
		} catch (ServiceEdgleChurrascariaException | IOException e) {
			reportarMensagemDeErro(e.getMessage());
		}
		return null;
	}

	public TipoDePedido getTipoDePedido() {
		if (mesa != null)
			return TipoDePedido.MESA;
		else if (entrega != null)
			return TipoDePedido.DELIVERY;
		else
			return TipoDePedido.BALCAO;
	}

	public List<Item> getItensNaoEnviados() {
		List<Item> retorno = new ArrayList<>();
		for (Item item : listItems) {
			if (item.getId() == null) {
				retorno.add(item);
			}
		}
		return retorno;

	}

	public void addItem() {
		itemNovo.setPedido(pedido);
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

	public String init() {
		listItems = new ArrayList<Item>();
		itemNovo = new Item();
		clientes = new ArrayList<Cliente>();
		try {
			clientes.addAll(clienteServiceImplementacao.getAll());
			if (isEdicaoDePedido()) {
				pedido = pedidoServiceImplementacao.getByID(pedido.getId());
				if (pedido.isFinalizado()) {
					reportarMensagemDeErro("Pedido já foi finalizado");
					return EnderecoPaginas.PAGINA_PRINCIPAL_VENDAS;
				}
				listItems = pedido.getItens();
				entrega = pedido.getEntrega();
				mesa = pedido.getMesa();
			} else {
				pedido = new Pedido();
				if (mesa != null) {
					pedido.setMesa(mesa);
					pedido.setTipoDePedido(TipoDePedido.MESA);
				} else if (delivery != null) {
					entrega = new Entrega();
					pedido.setEntrega(entrega);
					pedido.setTipoDePedido(TipoDePedido.DELIVERY);
				}
			}
			categoriasDeProdutos = categoriaProdutoServiceImplementacao.getAll();
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
		}
		return null;
	}

	public String finalizarPedido() {
		try {
			pedidoServiceImplementacao.finalizarPedido(pedido);
			return EnderecoPaginas.PAGINA_PRINCIPAL_VENDAS;
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
		}
		return null;
	}

	public void setCliente(Object obj) {
		String aux = obj.toString().replaceAll("Cliente \\[Id=", "");
		String ret = aux.split(",")[0];
		try {
			pedido.setCliente(clienteServiceImplementacao.getByID(Long.parseLong(ret)));
		} catch (NumberFormatException | ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			;
		}
	}

	public Object getCliente() {
		return pedido.getCliente() == null ? null : pedido.getCliente().getNome();

	}

	public boolean podeFinalizarPedido() {
		return pedidoServiceImplementacao.podeFinalizarPedido(pedido);

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
				retorno += ", ";
			}
			primeiro = false;
			retorno += observacaoPadrao.getDescricao();

		}
		return retorno;
	}

	public Boolean getDelivery() {
		return delivery;
	}

	public void setDelivery(Boolean delivery) {
		this.delivery = delivery;
	}

	public Item getItemLupa() {
		return itemLupa;
	}

	public void setItemLupa(Item itemLupa) {
		this.itemLupa = itemLupa;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

}
