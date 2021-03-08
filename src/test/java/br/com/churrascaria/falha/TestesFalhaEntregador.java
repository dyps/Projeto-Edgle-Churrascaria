package br.com.churrascaria.falha;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.churrascaria.AbstractTest;

public class TestesFalhaEntregador extends AbstractTest {

	@Test
	public void adicionarEntregadorSemValores() {
		entregadoresPage.visita();
		entregadoresPage.verificaERealizarLogin();
		entregadoresPage.nova().confirmar();
		// Não sei qual mensagem colocar aqui
	}

	@Test
	public void adicionarEntregadorSemNome() {
		entregadoresPage.visita();
		entregadoresPage.verificaERealizarLogin();
		entregadoresPage.nova().setTelefone("1234").confirmar();
		assertTrue(entregadoresPage.foiExibidaMensagemDeFalha("Erro: O nome do entregador é necessário"));
	}

	@Test
	public void adicionarEntregadorSemTelefone() {
		entregadoresPage.visita();
		entregadoresPage.verificaERealizarLogin();
		entregadoresPage.nova().setNome("Niks").confirmar();
		assertTrue(entregadoresPage.foiExibidaMensagemDeFalha("Erro: O telefone do entregador é necessário"));
	}

	@Test
	public void adicionarEntregadorComTelefoneRepetido() {
		entregadoresPage.visita();
		entregadoresPage.verificaERealizarLogin();
		entregadoresPage.nova().setNome("Niks").setTelefone("123").confirmar();
		assertTrue(entregadoresPage.foiExibidaMensagemDeFalha("Erro: O telefone do entregador não pode ser repetido"));
	}

	@Test
	public void adicionarTaxaNoEntregadorSemValor() {
		entregadoresPage.visita();
		entregadoresPage.verificaERealizarLogin();
		entregadoresPage.editar().novaTaxa().setDistancia("15.5").confirmar().confirmar();
		assertTrue(entregadoresPage.foiExibidaMensagemDeSucesso("Sucesso: Entregador 'Nikson' salvo"));
	}

	@Test
	public void adicionarTaxaNoEntregadorSemDistancia() {
		entregadoresPage.visita();
		entregadoresPage.verificaERealizarLogin();
		entregadoresPage.editar().novaTaxa().setValor("2.00").confirmar().confirmar();
		assertTrue(entregadoresPage.foiExibidaMensagemDeSucesso("Sucesso: Entregador 'Nikson' salvo"));
	}

}
