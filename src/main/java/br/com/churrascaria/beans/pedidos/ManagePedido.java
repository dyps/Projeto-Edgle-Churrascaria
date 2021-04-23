package br.com.churrascaria.beans.pedidos;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.beans.AbstractBean;
import br.com.churrascaria.entities.Mesa;
import br.com.churrascaria.entities.Pedido;
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

	private List<Pedido> pedidos;
	
	private List<Mesa> mesas;

	public List<Pedido> getPedidos() {
		return pedidos;
	}
	
	public List<Mesa> getMesas() {
		return mesas;
	}

	@PostConstruct
	public void init() {
		filtrar();
	}

	public String filtrar() {
		pedidos = new ArrayList<Pedido>();
		mesas = new ArrayList<Mesa>();
		try {
			pedidos.addAll(pedidoService.getAll());
			mesas.addAll(mesaService.findBy(new MesaFilter()));
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

//	public String abrirPedido(Pedido pedido) {
//		if(pedido.getItens().size() < 1)
//			return null;
//		else
//			return EnderecoPaginas.
//	}

}
