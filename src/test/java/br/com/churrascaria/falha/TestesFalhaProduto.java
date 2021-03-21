package br.com.churrascaria.falha;

import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.churrascaria.AbstractTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestesFalhaProduto extends AbstractTest {

	@Test
	public void test1CategoriaDeProdutoSemNome() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.nova().confirmar();
		assertTrue(produtoPage.foiExibidaMensagemDeFalha("Erro: O nome da categoria é necessário"));
	}

	@Test
	public void test2CategoriaDeProdutoComNomeRepetido() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.nova().setNome("Bebidas").confirmar();

		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.nova().setNome("Bebidas").confirmar();
		assertTrue(produtoPage.foiExibidaMensagemDeFalha("Erro: O nome da categoria não pode ser repetida"));
	}

	@Test
	public void test3ProdutoPersonalizadoComNomeRepetido() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.abrir().novoProdutoPersonalizado().setNome("Suco").novoItemConfiguracao().setNome("Sabor")
				.setQuantidadeMaxima("10").confirmar().novaOpcao().setNome("Laranja").setValorVenda("4").confirmar()
				.confirmar();

		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.abrir().novoProdutoPersonalizado().setNome("Suco").novoItemConfiguracao().setNome("Com adoçante")
				.setQuantidadeMaxima("5").confirmar().novaOpcao().setNome("Stevia").setValorVenda("1").confirmar()
				.confirmar();
		assertTrue(produtoPage.foiExibidaMensagemDeFalha("Erro: O nome do produto não pode ser repetido"));
	}

	@Test
	public void test4ProdutoPadraoComNomeRepetido() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.abrir().novoProdutoPadrao().setNome("Refrigerante 2L").setPrecoDeVenda("10").setUnidadeDeMedida()
				.confirmar();

		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.abrir().novoProdutoPadrao().setNome("Refrigerante 2L").setPrecoDeVenda("5").setUnidadeDeMedida()
				.confirmar();
		assertTrue(produtoPage.foiExibidaMensagemDeFalha("Erro: O nome do produto não pode ser repetido"));
	}

	@Test
	public void test5ProdutoPersonalizadoSemNome() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.abrir().novoProdutoPersonalizado().novoItemConfiguracao().setNome("Com adoçante")
				.setQuantidadeMaxima("5").confirmar().novaOpcao().setNome("Stevia").setValorVenda("1").confirmar()
				.confirmar();
		assertTrue(produtoPage.foiExibidaMensagemDeFalha("Erro: O nome do produto é necessário"));
	}

	@Test
	public void test6ProdutoPadraoSemNome() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.abrir().novoProdutoPadrao().setPrecoDeVenda("5").setUnidadeDeMedida().confirmar();
		assertTrue(produtoPage.foiExibidaMensagemDeFalha("Erro: O nome do produto é necessário"));
	}

	@Test
	public void test7ProdutoPadraoSemValorDeVenda() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.abrir().novoProdutoPadrao().setNome("Refrigerante 2L").setUnidadeDeMedida().confirmar();
		assertTrue(produtoPage.foiExibidaMensagemDeFalha("Erro: O preço de venda é necessário"));
	}

	@Test
	public void test8ProdutoPadraoComValorNegativo() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.abrir().novoProdutoPadrao().setNome("Refrigerante 2L").setPrecoDeVenda("-10").setUnidadeDeMedida()
				.confirmar();
		assertTrue(produtoPage.foiExibidaMensagemDeFalha("Erro: O preço de venda é necessário"));
	}

	@Test
	public void test9ProdutoPadraoSemTipoDeMedida() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.abrir().novoProdutoPadrao().setNome("Refrigerante 2L").setPrecoDeVenda("10").confirmar();
		assertTrue(produtoPage.foiExibidaMensagemDeFalha("Erro: Uma medida é necessária"));
	}

	@Test
	public void test10ProdutoPersonalizadoSemItemDeConfiguracao() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.abrir().novoProdutoPersonalizado().setNome("Suco").confirmar();
		assertTrue(produtoPage
				.foiExibidaMensagemDeFalha("Erro: Produto personalizado deve possuir um item de configuração"));
	}

	@Test
	public void test11ItemDeConfiguracaoSemOpcao() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.abrir().novoProdutoPersonalizado().setNome("Suco").novoItemConfiguracao().setNome("Sabor")
				.setQuantidadeMaxima("10").confirmar().confirmar();
		assertTrue(produtoPage.foiExibidaMensagemDeFalha("Erro: Item Sabor deve possuir uma opção"));
	}

	@Test
	public void test12ItemDeConfiguracaoSemNome() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.abrir().novoProdutoPersonalizado().setNome("Suco").novoItemConfiguracao().setQuantidadeMaxima("10")
				.confirmar();
		assertTrue(produtoPage.foiExibidaMensagemDeFalha("Erro: Um nome para o item de configuração é necessário"));
	}

	@Test
	public void test13ItemDeConfiguracaoSemQuantidade() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.abrir().novoProdutoPersonalizado().setNome("Suco").novoItemConfiguracao().setNome("Sabor")
				.confirmar();
		assertTrue(produtoPage.foiExibidaMensagemDeFalha(
				"Erro: É necessário informar uma quantidade máxima de escolhas do item de configuração"));
	}

	@Test
	public void test14ItemDeConfiguracaoComQuantidadeNegativa() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.abrir().novoProdutoPersonalizado().setNome("Suco").novoItemConfiguracao().setNome("Sabor")
				.setQuantidadeMaxima("-5").confirmar();
		assertTrue(produtoPage.foiExibidaMensagemDeFalha(
				"Erro: É necessário informar uma quantidade máxima de escolhas do item de configuração"));
	}

	@Test
	public void test15OpcaoDeItemDeConfiguracaoSemNome() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.abrir().novoProdutoPersonalizado().setNome("Suco").novoItemConfiguracao().setNome("Sabor")
				.setQuantidadeMaxima("10").confirmar().novaOpcao().setValorVenda("4").confirmar().confirmar();
		assertTrue(produtoPage.foiExibidaMensagemDeFalha("Erro: Um nome para a opção é necessário"));
	}

	@Test
	public void test16OpcaoDeItemDeConfiguracaoSemValor() {
		produtoPage.visita();
		produtoPage.verificaERealizarLogin();
		produtoPage.abrir().novoProdutoPersonalizado().setNome("Suco").novoItemConfiguracao().setNome("Sabor")
				.setQuantidadeMaxima("10").confirmar().novaOpcao().setNome("Laranja").confirmar().confirmar();
		assertTrue(produtoPage.foiExibidaMensagemDeFalha("Erro: É necessário informar o valor de venda da opção"));
	}

}
