package br.com.churrascaria.sucesso;

import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.churrascaria.AbstractTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestestSucessoProduto extends AbstractTest {

	@Test
	public void test01CadastrarCategoriaDeProduto() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.nova().setNome("Bebidas").confirmar();
		assertTrue(produtoPage.foiExibidaMensagemDeSucesso("Sucesso: Categoria de Produto 'Bebidas' salva"));
	}

	@Test
	public void test02NovoProdutoPadrao() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.abrir().novoProdutoPadrao().setNome("Refrigerante 2L").setPrecoDeVenda("10").setUnidadeDeMedida()
				.confirmar();
		assertTrue(produtoPage.foiExibidaMensagemDeSucesso("Sucesso: Produto 'Refrigerante 2L' salvo"));
	}

	@Test
	public void test03NovoProdutoPersonalizado() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.abrir().novoProdutoPersonalizado().setNome("Suco").novoItemConfiguracao().setNome("Sabor")
				.setQuantidadeMaxima("10").confirmar().novaOpcao().setNome("Laranja").setValorVenda("4").confirmar()
				.confirmar();
		assertTrue(produtoPage.foiExibidaMensagemDeSucesso("Sucesso: Produto 'Suco' salvo"));
	}

	@Test
	public void test04EditarProdutoPadrao() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.abrir().editarProdutoPadrao().setNome("Refrigerante 3L").confirmar();
		assertTrue(produtoPage.foiExibidaMensagemDeSucesso("Sucesso: Produto 'Refrigerante 3L' salvo"));
	}

	@Test
	public void test05EditarProdutoPersonalizado() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.abrir().abaProdutoPersonalizado().editarProdutoPersonalizado().setNome("Sucos")
				.novoItemConfiguracao().setNome("Com adoçante").setQuantidadeMaxima("10").confirmar().novaOpcao2()
				.setNome("Stevia").setValorVenda("1").confirmar().confirmar();
		assertTrue(produtoPage.foiExibidaMensagemDeSucesso("Sucesso: Produto 'Sucos' salvo"));
	}

	@Test
	public void test06EditarItemDeConfiguracao() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.abrir().abaProdutoPersonalizado().editarProdutoPersonalizado().editarItemConfiguracao()
				.setQuantidadeMaxima("5").confirmar().novaOpcao().setNome("Uva").setValorVenda("3,50").confirmar()
				.confirmar();
		assertTrue(produtoPage.foiExibidaMensagemDeSucesso("Sucesso: Produto 'Sucos' salvo"));
	}

	@Test
	public void test07BuscarCategoriaDeProduto() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.buscar("pizzas");
	}

	@Test
	public void test08BuscarProdutoPadrao() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.abrir().buscar("Sucos");
	}

	@Test
	public void test09BuscarProdutoPersonalizado() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.abrir().abaProdutoPersonalizado().buscar("Refrigerante");
	}

	@Test
	public void test10ExcluirOpcaoDeItemDeConfiguracao() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.abrir().abaProdutoPersonalizado().editarProdutoPersonalizado().excluirOpcao().confirmar()
				.confirmar();
		assertTrue(produtoPage.foiExibidaMensagemDeSucesso("Sucesso: Produto 'Sucos' salvo"));
	}

	@Test
	public void test11ExcluirItemDeConfiguracao() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.abrir().abaProdutoPersonalizado().editarProdutoPersonalizado().excluirItemConfiguracao().confirmar()
				.confirmar();
		assertTrue(produtoPage.foiExibidaMensagemDeSucesso("Sucesso: Produto 'Sucos' salvo"));
	}

	@Test
	public void test12ExcluirProdutoPadrao() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.abrir().apagarProdutoPadrao().confirmar();
		assertTrue(produtoPage.foiExibidaMensagemDeSucesso("Sucesso: Produto 'Refrigerante 3L' excluído"));
	}

	@Test
	public void test13ExcluirProdutoPersonalizado() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.abrir().abaProdutoPersonalizado().apagarProdutoPersonalizado().confirmar();
		assertTrue(produtoPage.foiExibidaMensagemDeSucesso("Sucesso: Produto 'Sucos' excluído"));
	}

	@Test
	public void test14ExcluirCategoriaDeProduto() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.apagar().confirmar();
		assertTrue(produtoPage.foiExibidaMensagemDeSucesso("Sucesso: Categoria de Produto 'Bebidas' excluída"));
	}

}
