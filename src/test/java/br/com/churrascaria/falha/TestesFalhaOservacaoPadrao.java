package br.com.churrascaria.falha;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.churrascaria.AbstractTest;

public class TestesFalhaOservacaoPadrao extends AbstractTest {
	
	@Test
	public void adicionarObservacaoSemValor() {
		observacaoPadraoPage.visita();
		observacaoPadraoPage.verificaERealizarLogin();
		observacaoPadraoPage.nova().confirmar();
		assertTrue(observacaoPadraoPage.foiExibidaMensagemDeFalha("Erro: A descrição da observação é necessário"));
	}
	
	@Test
	public void adicionarObservacaoRepetida() {
		observacaoPadraoPage.visita();
		observacaoPadraoPage.verificaERealizarLogin();
		observacaoPadraoPage.nova().setDescricao("Sem gelo").confirmar();
		assertTrue(observacaoPadraoPage.foiExibidaMensagemDeFalha("Erro: A descrição da observação não pode ser repetida"));
	}

}
