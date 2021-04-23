package br.com.churrascaria.services.implementacao;

import java.time.LocalDateTime;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.entities.AcaoRealizada;
import br.com.churrascaria.entities.Item;
import br.com.churrascaria.entities.Pedido;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;

@Named
@RequestScoped
public class AbrirFecharCaixaServiceImplementacao {

	@Inject
	private PedidoServiceImplementacao pedidoServiceImplementacao;

	public Integer nextNumeroDePedido() throws ServiceEdgleChurrascariaException { //regra ira mudar ao inplementar Abrir e fechar caixa
		List<Pedido> listpedi = pedidoServiceImplementacao.getAll();
		
		LocalDateTime menorDataDeCriacaoDoPedido = null;
		int retorno = 1;
		for (Pedido pedido : listpedi) {
			List<Item> itensdopedido = pedido.getItens();
			for (Item item : itensdopedido) {
				List<AcaoRealizada> acoes = item.getListAcaoRealizada();
				for (AcaoRealizada acaoRealizada : acoes) {
					if (menorDataDeCriacaoDoPedido==null) {
						menorDataDeCriacaoDoPedido = acaoRealizada.getData();
						retorno= pedido.getNumero()+1;
					}else if (menorDataDeCriacaoDoPedido.isBefore(acaoRealizada.getData())) {
						menorDataDeCriacaoDoPedido = acaoRealizada.getData();
						retorno= pedido.getNumero()+1;
					}
				}
			}
		}
		return retorno;
	}
}
