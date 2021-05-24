package br.com.churrascaria.services.implementacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.dao.EntidadeDAO;
import br.com.churrascaria.dao.ItemDAO;
import br.com.churrascaria.dao.PedidoDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.AcaoRealizada;
import br.com.churrascaria.entities.Funcionario;
import br.com.churrascaria.entities.Item;
import br.com.churrascaria.entities.Opcao;
import br.com.churrascaria.entities.Pedido;
import br.com.churrascaria.entities.ProdutoPadrao;
import br.com.churrascaria.entities.TipoAcaoItemPedido;
import br.com.churrascaria.services.CRUDService;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.util.TransacionalCdi;

@Named
@RequestScoped
public class PedidoServiceImplementacao extends CRUDService<Pedido> {

	@Inject
	private PedidoDAO entidadeDAO;
	@Inject
	private ItemDAO itemDAO;

	@Inject
	private AbrirFecharCaixaServiceImplementacao abrirFecharCaixaServiceImplementacao;

	@Override
	protected void validar(Pedido entidade) throws ServiceEdgleChurrascariaException {
		if (entidade.getItens().size() == 0) {
			throw new ServiceEdgleChurrascariaException("Pedido teve ter ao menos um item.");
		}
		for (Item item : entidade.getItens()) {
			validar(item);
		}

	}

	@Override
	public void save(Pedido entidade) throws ServiceEdgleChurrascariaException {
		throw new ServiceEdgleChurrascariaException("Não é assim que salva um pedido");
	}

	@Override
	public Pedido getByID(Long id) throws ServiceEdgleChurrascariaException {
		try {
			return entidadeDAO.getByID(id);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	@Override
	protected EntidadeDAO<Pedido> getEntidadeDAO() {
		return entidadeDAO;
	}

	@TransacionalCdi
	public void enviarParaCozinha(Pedido pedido, Funcionario funcionario) throws ServiceEdgleChurrascariaException {
		pedido.setNumero(abrirFecharCaixaServiceImplementacao.nextNumeroDePedido());
		for (Item item : pedido.getItens()) {
			ArrayList<AcaoRealizada> acoes = new ArrayList<>();
			AcaoRealizada acaoRealizada = new AcaoRealizada();
			acaoRealizada.setData(LocalDateTime.now(ZoneId.systemDefault()));
			acaoRealizada.setTipoAcaoItemPedido(TipoAcaoItemPedido.REALIZOUPEDIDO);
			acaoRealizada.setFuncionario(funcionario);
			if (funcionario == null) {
				throw new ServiceEdgleChurrascariaException("Nenhum funcionario logado");
			}
			acaoRealizada.setItem(item);
			acoes.add(acaoRealizada);
			item.setListAcaoRealizada(acoes);
		}
		super.save(pedido);
	}

	@TransacionalCdi
	public void enviarParaCozinha(Long id, List<Item> items, Funcionario funcionario)
			throws ServiceEdgleChurrascariaException {
		Pedido pedido = getByID(id);
		for (Item item : items) {
			ArrayList<AcaoRealizada> acoes = new ArrayList<>();
			AcaoRealizada acaoRealizada = new AcaoRealizada();
			acaoRealizada.setData(LocalDateTime.now(ZoneId.systemDefault()));
			acaoRealizada.setTipoAcaoItemPedido(TipoAcaoItemPedido.REALIZOUPEDIDO);
			acaoRealizada.setFuncionario(funcionario);
			if (funcionario == null) {
				throw new ServiceEdgleChurrascariaException("Nenhum funcionario logado");
			}
			acaoRealizada.setItem(item);
			acoes.add(acaoRealizada);
			item.setListAcaoRealizada(acoes);
			item.setPedido(pedido);
			salvarItem(item);
		}
	}

	private void salvarItem(Item item) throws ServiceEdgleChurrascariaException {
		validar(item);
		try {
			if (item.getId() == null) {
				itemDAO.save(item);
			} else {
				for (AcaoRealizada acaoRealizada : item.getListAcaoRealizada()) {
					if (acaoRealizada.getId() == null) {
						itemDAO.novaAcao(acaoRealizada);
					}
				}
			}
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException("Erro ao salvar o item");
		}

	}

	@TransacionalCdi
	public void entregarItem(Pedido pedido, Funcionario funcionarioLogado) throws ServiceEdgleChurrascariaException {
		pedido.setNumero(abrirFecharCaixaServiceImplementacao.nextNumeroDePedido());
		for (Item item : pedido.getItens()) {
			ArrayList<AcaoRealizada> acoes = new ArrayList<>();
			AcaoRealizada acaoRealizada = new AcaoRealizada();
			acaoRealizada.setData(LocalDateTime.now(ZoneId.systemDefault()));
			acaoRealizada.setTipoAcaoItemPedido(TipoAcaoItemPedido.ENTREGOU);
			acaoRealizada.setFuncionario(funcionarioLogado);
			if (funcionarioLogado == null) {
				throw new ServiceEdgleChurrascariaException("Nenhum funcionario logado");
			}
			acaoRealizada.setItem(item);
			acoes.add(acaoRealizada);
			item.setListAcaoRealizada(acoes);
		}
		super.save(pedido);

	}

	@TransacionalCdi
	public void entregarItem(Long id, Item item, Funcionario funcionarioLogado)
			throws ServiceEdgleChurrascariaException {
		Pedido pedido = getByID(id);
		item.setPedido(pedido);
		List<AcaoRealizada> acoes;
		if (item.getListAcaoRealizada() == null) {
			acoes = new ArrayList<>();
		} else {
			acoes = item.getListAcaoRealizada();
		}
		AcaoRealizada acaoRealizada = new AcaoRealizada();
		acaoRealizada.setData(LocalDateTime.now(ZoneId.systemDefault()));
		acaoRealizada.setTipoAcaoItemPedido(TipoAcaoItemPedido.ENTREGOU);
		acaoRealizada.setFuncionario(funcionarioLogado);
		if (funcionarioLogado == null) {
			throw new ServiceEdgleChurrascariaException("Nenhum funcionario logado");
		}
		acaoRealizada.setItem(item);
		acoes.add(acaoRealizada);
		item.setListAcaoRealizada(acoes);
		item.setPedido(pedido);
		salvarItem(item);
	}

	@TransacionalCdi
	public void cancelarItem(Item item, Funcionario funcionarioLogado) throws ServiceEdgleChurrascariaException {
		AcaoRealizada acaoRealizada = new AcaoRealizada();
		acaoRealizada.setData(LocalDateTime.now(ZoneId.systemDefault()));
		acaoRealizada.setTipoAcaoItemPedido(TipoAcaoItemPedido.CANCELOU);
		acaoRealizada.setFuncionario(funcionarioLogado);
		try {
			item = itemDAO.getByID(item.getId());
			item.getListAcaoRealizada().add(acaoRealizada);
			itemDAO.update(item);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage());
		}
	}

	public void validar(Item item) throws ServiceEdgleChurrascariaException {
		if (item.getQuantidade() <= 0) {
			throw new ServiceEdgleChurrascariaException("Item deve ter quantidade maior que 0.");
		}
		if (item.getValor() <= 0) {
			throw new ServiceEdgleChurrascariaException("Item deve ter valor maior que 0.");
		}
		if (item.getProduto() == null) {
			throw new ServiceEdgleChurrascariaException("Item deve ter produto.");
		}

	}

	public float getValorItem(Item itemNovo) {
		if (itemNovo.getProduto() instanceof ProdutoPadrao) {
			ProdutoPadrao produto = (ProdutoPadrao) itemNovo.getProduto();
			return produto.getValorDeVenda();
		} else {
			float valor = 0;
			List<Opcao> list = itemNovo.getListOpcoes();
			for (Opcao opcao : list) {
				valor += opcao.getValorDeVenda();
			}
			return valor;
		}
	}

	public boolean podeSerEntregue(Item item) {
		if (jaFoi(item, TipoAcaoItemPedido.ENTREGOU)) {
			return false;
		} else if (jaFoi(item, TipoAcaoItemPedido.CANCELOU)) {
			return false;
		}
		return true;
	}

	public boolean podeSerEditado(Item item) {
		if (jaFoi(item, TipoAcaoItemPedido.ENTREGOU))
			return false;
		else if (jaFoi(item, TipoAcaoItemPedido.CANCELOU))
			return false;
		else if (item.getId() == null)
			return false;
		return true;
	}

	private boolean jaFoi(Item item, TipoAcaoItemPedido tipoAcaoItemPedido) {
		List<AcaoRealizada> list = item.getListAcaoRealizada();
		if (list == null) {
			return false;
		}
		for (AcaoRealizada acaoRealizada : list) {
			if (acaoRealizada.getTipoAcaoItemPedido() == tipoAcaoItemPedido)
				return true;
		}
		return false;
	}

	public boolean podeSerCancelado(Item item) {
		if (jaFoi(item, TipoAcaoItemPedido.ENTREGOU))
			return false;
		else if (jaFoi(item, TipoAcaoItemPedido.CANCELOU))
			return false;
		return true;
	}

	@TransacionalCdi
	public void finalizarPedido(Pedido pedido) throws ServiceEdgleChurrascariaException {
		if (podeFinalizarPedido(pedido)) {
			try {
				pedido.setFinalizado(LocalDateTime.now(ZoneId.systemDefault()));
				entidadeDAO.update(pedido);
			} catch (PersistenciaEdgleChurrascariaException e) {
				throw new ServiceEdgleChurrascariaException(e.getMessage());
			}
		}

	}

	public boolean podeFinalizarPedido(Pedido pedido) {
		if (pedido.getId() == null) {
			return true;
		}
		for (Item item : pedido.getItens()) {
			if (!itemContemStatus(item, TipoAcaoItemPedido.ENTREGOU)) {
				return false;
			}
		}
		return true;
	}

	private boolean itemContemStatus(Item item, TipoAcaoItemPedido acao) {
		for (AcaoRealizada acaoRealizada : item.getListAcaoRealizada()) {
			if (acaoRealizada.getTipoAcaoItemPedido().equals(acao)) {
				return true;
			}
		}
		return false;
	}

	public Pedido getPedidosDaMesa(Long id) throws ServiceEdgleChurrascariaException {
		try {
			List<Pedido> pedidos = entidadeDAO.getAll();
			for (Pedido pedido : pedidos) {
				if (pedido.getMesa() != null) {
					if (pedido.getMesa().getId() == id && pedido.getFinalizado() == null) {
						return pedido;
					}
				}
			}
			return null;
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage());
		}
	}

	public List<Pedido> getPedidosDoTipoBalcao() throws ServiceEdgleChurrascariaException {
		try {
			List<Pedido> pedidos = entidadeDAO.getAll();
			List<Pedido> pedidosBalcao = new ArrayList<Pedido>();
			for (Pedido pedido : pedidos) {
				if (pedido.getTipoDePedido().getNome() == "Balcão" && pedido.getFinalizado() == null)
					pedidosBalcao.add(pedido);
			}
			return pedidosBalcao;
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage());
		}
	}

	public List<Pedido> getPedidosDoTipoEntrega() throws ServiceEdgleChurrascariaException {
		try {
			List<Pedido> pedidos = entidadeDAO.getAll();
			List<Pedido> pedidosEntrega = new ArrayList<Pedido>();
			for (Pedido pedido : pedidos) {
				if (pedido.getTipoDePedido().getNome() == "Delivery" && pedido.getFinalizado() == null)
					pedidosEntrega.add(pedido);
			}
			return pedidosEntrega;
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage());
		}
	}

	public List<Pedido> getEncerredos(LocalDate dataInicio, LocalDate dataFim)
			throws ServiceEdgleChurrascariaException {
		if (!((!dataFim.isBefore(dataInicio)) || dataFim.isEqual(dataInicio))) {
			throw new ServiceEdgleChurrascariaException("Data de inicio deve ser antes da data de fim");
		}
		try {
			List<Pedido> pedidosEncerrados = new ArrayList<Pedido>();
			for (Pedido pedido : entidadeDAO.getAll()) {

				LocalDate dataCriaçãoDoPedido = dataCriaçãoDoPedido(pedido);
				if (pedido.getFinalizado() != null
						&& (dataInicio.isBefore(dataCriaçãoDoPedido) || dataInicio.isEqual(dataCriaçãoDoPedido))
						&& (dataFim.isAfter(dataCriaçãoDoPedido) || dataFim.isEqual(dataCriaçãoDoPedido))) {
					pedidosEncerrados.add(pedido);
				}
			}
			return pedidosEncerrados;
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage());
		}
	}

	private LocalDate dataCriaçãoDoPedido(Pedido pedido) {
		LocalDateTime retorno = null;
		for (Item item : pedido.getItens()) {
			for (AcaoRealizada acaoRealizada : item.getListAcaoRealizada()) {
				if (retorno == null || acaoRealizada.getData().isBefore(retorno)) {
					retorno = acaoRealizada.getData();
				}
			}
		}
		return retorno.toLocalDate();
	}

}
