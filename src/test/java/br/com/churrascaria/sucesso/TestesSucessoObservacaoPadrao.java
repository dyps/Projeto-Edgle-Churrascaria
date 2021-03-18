package br.com.churrascaria.sucesso;

import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.churrascaria.AbstractTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestesSucessoObservacaoPadrao extends AbstractTest {
	
	@Test
	public void test1AdicionarObservacaoPadrao() {
		observacaoPadraoPage.visita();
		observacaoPadraoPage.verificaERealizarLogin();
		String novaDescricao = "Sem gelo";
		observacaoPadraoPage.nova().setDescricao(novaDescricao).confirmar();
		assertTrue(observacaoPadraoPage.foiExibidaMensagemDeSucesso("Sucesso: Observação '"+ novaDescricao+"' salva"));
	}
	
	@Test
	public void test2ExcluirObservacaoPadrao() {
		observacaoPadraoPage.visita();
		observacaoPadraoPage.verificaERealizarLogin();
		observacaoPadraoPage.apagar().confirmar();
		assertTrue(observacaoPadraoPage.foiExibidaMensagemDeSucesso("Sucesso: Observação 'Sem gelo' excluída"));
	}

}
