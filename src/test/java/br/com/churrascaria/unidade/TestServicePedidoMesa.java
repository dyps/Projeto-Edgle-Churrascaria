package br.com.churrascaria.unidade;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.churrascaria.dao.ItemDAO;
import br.com.churrascaria.dao.PedidoDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.AcaoRealizada;
import br.com.churrascaria.entities.CategoriaProduto;
import br.com.churrascaria.entities.Cliente;
import br.com.churrascaria.entities.Funcionario;
import br.com.churrascaria.entities.Item;
import br.com.churrascaria.entities.Mesa;
import br.com.churrascaria.entities.Pedido;
import br.com.churrascaria.entities.Produto;
import br.com.churrascaria.entities.ProdutoPadrao;
import br.com.churrascaria.entities.TipoDeFuncionario;
import br.com.churrascaria.entities.TipoDeMedida;
import br.com.churrascaria.entities.TipoDePedido;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.AbrirFecharCaixaServiceImplementacao;
import br.com.churrascaria.services.implementacao.PedidoServiceImplementacao;

@RunWith(MockitoJUnitRunner.class)
public class TestServicePedidoMesa {

	private PedidoServiceImplementacao pedidoServiceImplementacao;

	private AbrirFecharCaixaServiceImplementacao caixa;

	@Mock
	private PedidoDAO pedidoDAO;

	@Mock
	private ItemDAO itemDAO;

	@Before
	public void setUp() {
		pedidoServiceImplementacao = new PedidoServiceImplementacao();
		caixa = new AbrirFecharCaixaServiceImplementacao();
		pedidoServiceImplementacao.setAbrirFecharCaixaServiceImplementacao(caixa);
		caixa.setPedidoServiceImplementacao(this.pedidoServiceImplementacao);
		pedidoServiceImplementacao.setPedidoDAO(pedidoDAO);
		pedidoServiceImplementacao.setItemDAO(itemDAO);
	}

	@Test
	public void test1CriarPedidoMesa() throws ServiceEdgleChurrascariaException {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Nikson");
		funcionario.setLogin("nikson");
		funcionario.setSenha("123");
		funcionario.setTipoDeFuncionario(TipoDeFuncionario.GERENTE);
		Cliente cliente = new Cliente();
		cliente.setNome("Yaggo");
		cliente.setTelefone("123");
		Mesa mesa = new Mesa();
		mesa.setNumero(1);
		CategoriaProduto categoria = new CategoriaProduto();
		categoria.setNome("Bebidas");
		ProdutoPadrao produto = new ProdutoPadrao();
		produto.setNome("Refrigerante 2L");
		produto.setHabilitado(true);
		produto.setValorDeVenda(10f);
		produto.setMedida(TipoDeMedida.UNIDADE);
		produto.setCategoriaProduto(categoria);
		List<Produto> produtos = new ArrayList<Produto>();
		produtos.add(produto);
		categoria.setProdutos(produtos);

		Pedido pedido = new Pedido();
		pedido.setNumero(1);
		pedido.setTipoDePedido(TipoDePedido.MESA);
		pedido.setCliente(cliente);
		pedido.setMesa(mesa);
		Item item = new Item();
		item.setProduto(produto);
		item.setQuantidade(4);
		item.setValor(4 * produto.getValorDeVenda());
		item.setPedido(pedido);
		pedido.getItens().add(item);

		pedidoServiceImplementacao.enviarParaCozinha(pedido, funcionario);
	}

	@Test
	public void test2CriarPedidoBalcao() throws ServiceEdgleChurrascariaException {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Nikson");
		funcionario.setLogin("nikson");
		funcionario.setSenha("123");
		funcionario.setTipoDeFuncionario(TipoDeFuncionario.GERENTE);
		Cliente cliente = new Cliente();
		cliente.setNome("Yaggo");
		cliente.setTelefone("123");
		CategoriaProduto categoria = new CategoriaProduto();
		categoria.setNome("Bebidas");
		ProdutoPadrao produto = new ProdutoPadrao();
		produto.setNome("Refrigerante 2L");
		produto.setHabilitado(true);
		produto.setValorDeVenda(10f);
		produto.setMedida(TipoDeMedida.UNIDADE);
		produto.setCategoriaProduto(categoria);
		List<Produto> produtos = new ArrayList<Produto>();
		produtos.add(produto);
		categoria.setProdutos(produtos);

		Pedido pedido = new Pedido();
		pedido.setNumero(1);
		pedido.setTipoDePedido(TipoDePedido.BALCAO);
		pedido.setCliente(cliente);
		Item item = new Item();
		item.setProduto(produto);
		item.setQuantidade(4);
		item.setValor(4 * produto.getValorDeVenda());
		item.setPedido(pedido);
		pedido.getItens().add(item);

		pedidoServiceImplementacao.enviarParaCozinha(pedido, funcionario);
	}

	@Test
	public void test3CancelarItemMesa()
			throws PersistenciaEdgleChurrascariaException, ServiceEdgleChurrascariaException {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Nikson");
		funcionario.setLogin("nikson");
		funcionario.setSenha("123");
		funcionario.setTipoDeFuncionario(TipoDeFuncionario.GERENTE);
		Cliente cliente = new Cliente();
		cliente.setNome("Yaggo");
		cliente.setTelefone("123");
		Mesa mesa = new Mesa();
		mesa.setNumero(1);
		CategoriaProduto categoria = new CategoriaProduto();
		categoria.setNome("Bebidas");
		ProdutoPadrao produto = new ProdutoPadrao();
		produto.setNome("Refrigerante 2L");
		produto.setHabilitado(true);
		produto.setValorDeVenda(10f);
		produto.setMedida(TipoDeMedida.UNIDADE);
		produto.setCategoriaProduto(categoria);
		List<Produto> produtos = new ArrayList<Produto>();
		produtos.add(produto);
		categoria.setProdutos(produtos);

		Pedido pedido = new Pedido();
		pedido.setNumero(1);
		pedido.setTipoDePedido(TipoDePedido.MESA);
		pedido.setCliente(cliente);
		pedido.setMesa(mesa);
		Item item = new Item();
		item.setListAcaoRealizada(new ArrayList<AcaoRealizada>());
		item.setProduto(produto);
		item.setQuantidade(4);
		item.setValor(4 * produto.getValorDeVenda());
		item.setPedido(pedido);
		pedido.getItens().add(item);

		when(itemDAO.getByID(item.getId())).thenReturn(item);

		pedidoServiceImplementacao.cancelarItem(item, funcionario);
	}

	@Test
	public void test4CancelarItemBalcao()
			throws ServiceEdgleChurrascariaException, PersistenciaEdgleChurrascariaException {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Nikson");
		funcionario.setLogin("nikson");
		funcionario.setSenha("123");
		funcionario.setTipoDeFuncionario(TipoDeFuncionario.GERENTE);
		Cliente cliente = new Cliente();
		cliente.setNome("Yaggo");
		cliente.setTelefone("123");
		CategoriaProduto categoria = new CategoriaProduto();
		categoria.setNome("Bebidas");
		ProdutoPadrao produto = new ProdutoPadrao();
		produto.setNome("Refrigerante 2L");
		produto.setHabilitado(true);
		produto.setValorDeVenda(10f);
		produto.setMedida(TipoDeMedida.UNIDADE);
		produto.setCategoriaProduto(categoria);
		List<Produto> produtos = new ArrayList<Produto>();
		produtos.add(produto);
		categoria.setProdutos(produtos);

		Pedido pedido = new Pedido();
		pedido.setNumero(1);
		pedido.setTipoDePedido(TipoDePedido.BALCAO);
		pedido.setCliente(cliente);
		Item item = new Item();
		item.setListAcaoRealizada(new ArrayList<AcaoRealizada>());
		item.setProduto(produto);
		item.setQuantidade(4);
		item.setValor(4 * produto.getValorDeVenda());
		item.setPedido(pedido);
		pedido.getItens().add(item);

		when(itemDAO.getByID(item.getId())).thenReturn(item);

		pedidoServiceImplementacao.cancelarItem(item, funcionario);
	}

	@Test
	public void test5FinalizarPedidoMesa() throws ServiceEdgleChurrascariaException {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Nikson");
		funcionario.setLogin("nikson");
		funcionario.setSenha("123");
		funcionario.setTipoDeFuncionario(TipoDeFuncionario.GERENTE);
		Cliente cliente = new Cliente();
		cliente.setNome("Yaggo");
		cliente.setTelefone("123");
		Mesa mesa = new Mesa();
		mesa.setNumero(1);
		CategoriaProduto categoria = new CategoriaProduto();
		categoria.setNome("Bebidas");
		ProdutoPadrao produto = new ProdutoPadrao();
		produto.setNome("Refrigerante 2L");
		produto.setHabilitado(true);
		produto.setValorDeVenda(10f);
		produto.setMedida(TipoDeMedida.UNIDADE);
		produto.setCategoriaProduto(categoria);
		List<Produto> produtos = new ArrayList<Produto>();
		produtos.add(produto);
		categoria.setProdutos(produtos);

		Pedido pedido = new Pedido();
		pedido.setNumero(1);
		pedido.setTipoDePedido(TipoDePedido.MESA);
		pedido.setCliente(cliente);
		pedido.setMesa(mesa);
		Item item = new Item();
		item.setProduto(produto);
		item.setQuantidade(4);
		item.setValor(4 * produto.getValorDeVenda());
		item.setPedido(pedido);
		pedido.getItens().add(item);

		pedidoServiceImplementacao.finalizarPedido(pedido);
	}

	@Test
	public void test6FinalizarPedidoBalcao() throws ServiceEdgleChurrascariaException {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Nikson");
		funcionario.setLogin("nikson");
		funcionario.setSenha("123");
		funcionario.setTipoDeFuncionario(TipoDeFuncionario.GERENTE);
		Cliente cliente = new Cliente();
		cliente.setNome("Yaggo");
		cliente.setTelefone("123");
		CategoriaProduto categoria = new CategoriaProduto();
		categoria.setNome("Bebidas");
		ProdutoPadrao produto = new ProdutoPadrao();
		produto.setNome("Refrigerante 2L");
		produto.setHabilitado(true);
		produto.setValorDeVenda(10f);
		produto.setMedida(TipoDeMedida.UNIDADE);
		produto.setCategoriaProduto(categoria);
		List<Produto> produtos = new ArrayList<Produto>();
		produtos.add(produto);
		categoria.setProdutos(produtos);

		Pedido pedido = new Pedido();
		pedido.setNumero(1);
		pedido.setTipoDePedido(TipoDePedido.BALCAO);
		pedido.setCliente(cliente);
		Item item = new Item();
		item.setProduto(produto);
		item.setQuantidade(4);
		item.setValor(4 * produto.getValorDeVenda());
		item.setPedido(pedido);
		pedido.getItens().add(item);

		pedidoServiceImplementacao.finalizarPedido(pedido);
	}

}
