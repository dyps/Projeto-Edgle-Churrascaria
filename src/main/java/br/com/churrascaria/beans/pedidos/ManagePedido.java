package br.com.churrascaria.beans.pedidos;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.beans.AbstractBean;
import br.com.churrascaria.entities.AcaoRealizada;
import br.com.churrascaria.entities.Item;
import br.com.churrascaria.entities.Mesa;
import br.com.churrascaria.entities.Pedido;
import br.com.churrascaria.entities.TipoAcaoItemPedido;
import br.com.churrascaria.filter.MesaFilter;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.MesaServiceImplementacao;
import br.com.churrascaria.services.implementacao.PedidoServiceImplementacao;

@Named
@ViewScoped
public class ManagePedido extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private PedidoServiceImplementacao pedidoService;

	@Inject
	private MesaServiceImplementacao mesaService;

	private List<Pedido> pedidosEncerrados;

	private List<Pedido> pedidosBalcao;

	private List<Pedido> pedidosEntrega;

	private List<Mesa> mesas;

	public List<Pedido> getPedidosEncerrados() {
		return pedidosEncerrados;
	}

	public List<Pedido> getPedidosBalcao() {
		return pedidosBalcao;
	}

	public List<Pedido> getPedidosEntrega() {
		return pedidosEntrega;
	}

	public List<Mesa> getMesas() {
		return mesas;
	}

	@PostConstruct
	public void init() {
		filtrar();
//		pedidosDoTipoBalcao();
	}

	public String filtrar() {
		pedidosEncerrados = new ArrayList<Pedido>();
		Pedido primeiroPedido = new Pedido();
		primeiroPedido.setPrimeiro(true);
		//pedidos.add(primeiroPedido);
		pedidosBalcao = new ArrayList<Pedido>();
		pedidosBalcao.add(primeiroPedido);
		pedidosEntrega = new ArrayList<Pedido>();
		pedidosEntrega.add(primeiroPedido);
		mesas = new ArrayList<Mesa>();
		try {
			for (Pedido pedido : pedidoService.getAll()) {
				if(pedido.isFinalizado())
					pedidosEncerrados.add(pedido);
			}
			pedidosBalcao.addAll(pedidoService.getPedidosDoTipoBalcao());
			pedidosEntrega.addAll(pedidoService.getPedidosDoTipoEntrega());
			mesas.addAll(mesaService.findBy(new MesaFilter()));
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String clienteDoPedidoMesa(Mesa mesa) {
		try {
			Pedido pedido = pedidoService.getPedidosDaMesa(mesa.getId());
			return pedido.getCliente().getNome();
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
		}
		return null;
	}

	public String clienteDoPedido(Pedido pedido) {
		return pedido.getCliente().getNome();
	}

	public String enderecoDoPedido(Pedido pedido) {
		return pedido.getEntrega().getEndereco().getNome();
	}

	public String itensDoPedidosRealizadosMesa(Mesa mesa) {
		try {
			Pedido pedido = pedidoService.getPedidosDaMesa(mesa.getId());
			int quantidade = 0;
			for (Item item : pedido.getItens()) {
				boolean entregou = false;
				boolean realizou = false;
				for (AcaoRealizada acao : item.getListAcaoRealizada()) {
					if (acao.getTipoAcaoItemPedido() == TipoAcaoItemPedido.REALIZOUPEDIDO)
						realizou = true;
					if (acao.getTipoAcaoItemPedido() == TipoAcaoItemPedido.ENTREGOU)
						entregou = true;
				}
				if (realizou && !entregou)
					quantidade++;
			}
			return "" + quantidade;
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
		}
		return null;
	}

	public String itensDoPedidosRealizados(Pedido pedido) {
		int quantidade = 0;
		for (Item item : pedido.getItens()) {
			boolean entregou = false;
			boolean realizou = false;
			for (AcaoRealizada acao : item.getListAcaoRealizada()) {
				if (acao.getTipoAcaoItemPedido() == TipoAcaoItemPedido.REALIZOUPEDIDO)
					realizou = true;
				if (acao.getTipoAcaoItemPedido() == TipoAcaoItemPedido.ENTREGOU)
					entregou = true;
			}
			if (realizou && !entregou)
				quantidade++;
		}
		return "" + quantidade;
	}

	public String itensDoPedidoEntreguesMesa(Mesa mesa) {
		try {
			Pedido pedido = pedidoService.getPedidosDaMesa(mesa.getId());
			int quantidade = 0;
			for (Item item : pedido.getItens()) {
				for (AcaoRealizada acao : item.getListAcaoRealizada()) {
					if (acao.getTipoAcaoItemPedido().getNome() == "Entregou")
						quantidade = quantidade + 1;
				}
			}
			return "" + quantidade;
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
		}
		return null;
	}

	public String itensDoPedidoEntregues(Pedido pedido) {
		int quantidade = 0;
		for (Item item : pedido.getItens()) {
			for (AcaoRealizada acao : item.getListAcaoRealizada()) {
				if (acao.getTipoAcaoItemPedido().getNome() == "Entregou")
					quantidade = quantidade + 1;
			}
		}
		return "" + quantidade;
	}

	public float valorTotalMesa(Mesa mesa) {
		try {
			Pedido pedido = pedidoService.getPedidosDaMesa(mesa.getId());
			float valorTotal = 0;
			for (Item item : pedido.getItens()) {
				valorTotal = valorTotal + (item.getValor() * item.getQuantidade());
			}
			return valorTotal;
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
		}
		return 0;
	}

	public float valorTotal(Pedido pedido) {
		float valorTotal = 0;
		for (Item item : pedido.getItens()) {
			valorTotal = valorTotal + (item.getValor() * item.getQuantidade());
		}
		return valorTotal;
	}

}
