package br.com.churrascaria.falha;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.churrascaria.AbstractTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestesFalhaPedidos extends AbstractTest {

	// Pré requisitos - cadastrar uma mesa, cadastrar uma observação, cadastrar um
	// produto

	@Test
	public void test1SelecionarProdutoComQuantidade0() {
		pedidoPage.visita();
		pedidoPage.verificaERealizarLogin();
		pedidoPage.abaMesas().novoPedidoMesa().setObservacao("Tocar campainha").selecionarCategoriaDoProduto()
				.selecionarProduto().adicionarObservacao().setQuantidade0().confirmar();
	}

	@Test
	public void test2EnviarParaCozinhaSemSelecionarItem() {
		pedidoPage.visita();
		pedidoPage.verificaERealizarLogin();
		pedidoPage.visita();
		pedidoPage.verificaERealizarLogin();
		pedidoPage.abaMesas().novoPedidoMesa().setObservacao("Tocar campainha").selecionarCategoriaDoProduto()
				.selecionarProduto().adicionarObservacao().setQuantidade("3").confirmar().enviarProdutoParaCozinha()
				.confirmar();
	}

}
