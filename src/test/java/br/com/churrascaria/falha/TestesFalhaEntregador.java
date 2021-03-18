package br.com.churrascaria.falha;

import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.churrascaria.AbstractTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestesFalhaEntregador extends AbstractTest {

	@Test
	public void test1AdicionarEntregadorSemValores() {
		entregadoresPage.visita();
		entregadoresPage.verificaERealizarLogin();
		entregadoresPage.nova().confirmar();
		// Não sei qual mensagem colocar aqui
	}

	@Test
	public void test2AdicionarEntregadorSemNome() {
		entregadoresPage.visita();
		entregadoresPage.verificaERealizarLogin();
		entregadoresPage.nova().setTelefone("1234").confirmar();
		assertTrue(entregadoresPage.foiExibidaMensagemDeFalha("Erro: O nome do entregador é necessário"));
	}

	@Test
	public void test3AdicionarEntregadorSemTelefone() {
		entregadoresPage.visita();
		entregadoresPage.verificaERealizarLogin();
		entregadoresPage.nova().setNome("Niks").confirmar();
		assertTrue(entregadoresPage.foiExibidaMensagemDeFalha("Erro: O telefone do entregador é necessário"));
	}

	@Test
	public void test4AdicionarEntregadorComTelefoneRepetido() {
		entregadoresPage.visita();
		entregadoresPage.verificaERealizarLogin();
		String nome = "Nikson";
		entregadoresPage.nova().setNome(nome).setTelefone("123").confirmar();
		
		entregadoresPage.visita();
		entregadoresPage.verificaERealizarLogin();
		entregadoresPage.nova().setNome("Niks").setTelefone("123").confirmar();
		assertTrue(entregadoresPage.foiExibidaMensagemDeFalha("Erro: O telefone do entregador não pode ser repetido"));
	}

	@Test
	public void test5AdicionarTaxaNoEntregadorSemValor() {
		entregadoresPage.visita();
		entregadoresPage.verificaERealizarLogin();
		entregadoresPage.editar().novaTaxa().setDistancia("15.5").confirmar().confirmar();
		assertTrue(entregadoresPage.foiExibidaMensagemDeSucesso("Sucesso: Entregador 'Nikson' salvo"));
	}

	@Test
	public void test6AdicionarTaxaNoEntregadorSemDistancia() {
		entregadoresPage.visita();
		entregadoresPage.verificaERealizarLogin();
		entregadoresPage.editar().novaTaxa().setValor("2.00").confirmar().confirmar();
		assertTrue(entregadoresPage.foiExibidaMensagemDeSucesso("Sucesso: Entregador 'Nikson' salvo"));
	}

}
