package br.com.churrascaria.sucesso;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.churrascaria.AbstractTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestestSucessoPedidos extends AbstractTest {

	// Pré requisitos - cadastrar uma mesa, cadastrar uma observação, cadastrar um
	// produto

	@Test
	public void test1CriarPedidoMesa() {
		pedidoPage.visita();
		pedidoPage.verificaERealizarLogin();
		pedidoPage.abaMesas().novoPedidoMesa().setObservacao("Tocar campainha").selecionarCategoriaDoProduto()
				.selecionarProduto().adicionarObservacao().setQuantidade("3").confirmar().enviarProdutoParaCozinha()
				.selecionarProduto().confirmar();
	}

	@Test
	public void test2CriarPedidoBalcao() {
		pedidoPage.visita();
		pedidoPage.verificaERealizarLogin();
		pedidoPage.abaBalcao().novoPedidoBalcao().setObservacao("Tocar campainha").selecionarCategoriaDoProduto()
				.selecionarProduto().adicionarObservacao().confirmar().enviarProdutoParaCozinha().selecionarProduto()
				.confirmar();
	}

	@Test
	public void test3VisualizarDetalhesDoItemMesa() {
		pedidoPage.visita();
		pedidoPage.verificaERealizarLogin();
		pedidoPage.abaMesas().verPedidoDaMesa().verDetalhesDoItem();
	}

	@Test
	public void test4VisualizarDetalhesDoItemBalcao() {
		pedidoPage.visita();
		pedidoPage.verificaERealizarLogin();
		pedidoPage.abaBalcao().verPedidoDoBalcao().verDetalhesDoItem();
	}

	@Test
	public void test5CancelarItemMesa() {
		pedidoPage.visita();
		pedidoPage.verificaERealizarLogin();
		pedidoPage.abaMesas().verPedidoDaMesa().selecionarCategoriaDoProduto().selecionarProduto().adicionarObservacao()
				.confirmar().cancelarItem();
	}

	@Test
	public void test6CancelarItemBalcao() {
		pedidoPage.visita();
		pedidoPage.verificaERealizarLogin();
		pedidoPage.abaBalcao().verPedidoDoBalcao().selecionarCategoriaDoProduto().selecionarProduto()
				.adicionarObservacao().confirmar().cancelarItem();
	}

	@Test
	public void test7EditarPedidoMesa() {
		pedidoPage.visita();
		pedidoPage.verificaERealizarLogin();
		pedidoPage.abaMesas().verPedidoDaMesa().marcarProdutoComoEntregue().confirmar();
	}

	@Test
	public void test8EditarPedidoBalcao() {
		pedidoPage.visita();
		pedidoPage.verificaERealizarLogin();
		pedidoPage.abaBalcao().verPedidoDoBalcao().marcarProdutoComoEntregue().confirmar();
	}

	@Test
	public void test9FinalizarPedidoMesa() {
		pedidoPage.visita();
		pedidoPage.verificaERealizarLogin();
		pedidoPage.abaMesas().verPedidoDaMesa().finalizarPedido();
	}

	@Test
	public void testFinalFinalizarPedidoBalcao() {
		pedidoPage.visita();
		pedidoPage.verificaERealizarLogin();
		pedidoPage.abaBalcao().verPedidoDoBalcao().finalizarPedido();
	}

}
