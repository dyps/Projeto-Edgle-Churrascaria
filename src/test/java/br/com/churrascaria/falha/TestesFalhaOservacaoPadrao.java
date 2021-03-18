package br.com.churrascaria.falha;

import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.churrascaria.AbstractTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestesFalhaOservacaoPadrao extends AbstractTest {
	
	@Test
	public void test1AdicionarObservacaoSemValor() {
		observacaoPadraoPage.visita();
		observacaoPadraoPage.verificaERealizarLogin();
		observacaoPadraoPage.nova().confirmar();
		assertTrue(observacaoPadraoPage.foiExibidaMensagemDeFalha("Erro: A descrição da observação é necessário"));
	}
	
	@Test
	public void test2AdicionarObservacaoRepetida() {
		observacaoPadraoPage.visita();
		observacaoPadraoPage.verificaERealizarLogin();
		observacaoPadraoPage.nova().setDescricao("Sem gelo").confirmar();
		
		observacaoPadraoPage.visita();
		observacaoPadraoPage.verificaERealizarLogin();
		observacaoPadraoPage.nova().setDescricao("Sem gelo").confirmar();
		assertTrue(observacaoPadraoPage.foiExibidaMensagemDeFalha("Erro: A descrição da observação não pode ser repetida"));
	}

}
