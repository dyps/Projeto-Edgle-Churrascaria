package br.com.churrascaria.sucesso;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.churrascaria.AbstractTest;

public class TestesSucessoEntregador extends AbstractTest {
	
	@Test
	public void adicionarEntregador() {
		entregadoresPage.visita();
		entregadoresPage.verificaERealizarLogin();
		String nome = "Nikson";
		entregadoresPage.nova().setNome(nome).setTelefone("123").confirmar();
		assertTrue(entregadoresPage.foiExibidaMensagemDeSucesso("Sucesso: Entregador '"+ nome+"' salvo"));
	}
	
	@Test
	public void adicionarTaxaNoEntregador() {
		entregadoresPage.visita();
		entregadoresPage.verificaERealizarLogin();
		entregadoresPage.editar().novaTaxa().setValor("2.00").setDistancia("15.5").confirmar().confirmar();
		assertTrue(entregadoresPage.foiExibidaMensagemDeSucesso("Sucesso: Entregador 'Nikson' salvo"));
	}
	
	@Test
	public void editarEntregador() {
		entregadoresPage.visita();
		entregadoresPage.verificaERealizarLogin();
		entregadoresPage.editar().setNome("Niks").confirmar();
		assertTrue(entregadoresPage.foiExibidaMensagemDeSucesso("Sucesso: Entregador 'Niks' salvo"));
	}
	
	@Test
	public void excluirEntregador() {
		entregadoresPage.visita();
		entregadoresPage.verificaERealizarLogin();
		entregadoresPage.apagar().confirmar();
		assertTrue(entregadoresPage.foiExibidaMensagemDeSucesso("Sucesso: Entregador 'Niks' excluído"));
	}
	
	@Test
	public void excluirTaxaNoEntregador() {
		entregadoresPage.visita();
		entregadoresPage.verificaERealizarLogin();
		entregadoresPage.editar().apagarTaxa().confirmar().confirmar();
		assertTrue(entregadoresPage.foiExibidaMensagemDeSucesso("Sucesso: Entregador 'Nikson' salvo"));
	}
	
	@Test
	public void buscarEntregador() {
		entregadoresPage.visita();
		entregadoresPage.verificaERealizarLogin();
		String nome = "Yaggo";
		entregadoresPage.buscar(nome);
	}

}
