package br.com.churrascaria.sucesso;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.churrascaria.AbstractTest;

public class TestesSucessoObservacaoPadrao extends AbstractTest {
	
	@Test
	public void adicionarObservacaoPadrao() {
		observacaoPadraoPage.visita();
		observacaoPadraoPage.verificaERealizarLogin();
		String novaDescricao = "Sem gelo";
		observacaoPadraoPage.nova().setDescricao(novaDescricao).confirmar();
		assertTrue(observacaoPadraoPage.foiExibidaMensagemDeSucesso("Sucesso: Observação '"+ novaDescricao+"' salva"));
	}
	
	@Test
	public void excluirObservacaoPadrao() {
		observacaoPadraoPage.visita();
		observacaoPadraoPage.verificaERealizarLogin();
		observacaoPadraoPage.apagar().confirmar();
		assertTrue(observacaoPadraoPage.foiExibidaMensagemDeSucesso("Sucesso: Observação 'Sem gelo' excluída"));
	}

}
