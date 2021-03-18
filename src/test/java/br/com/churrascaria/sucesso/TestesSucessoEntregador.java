package br.com.churrascaria.sucesso;

import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.churrascaria.AbstractTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestesSucessoEntregador extends AbstractTest {
	
	@Test
	public void test1AdicionarEntregador() {
		entregadoresPage.visita();
		entregadoresPage.verificaERealizarLogin();
		String nome = "Nikson";
		entregadoresPage.nova().setNome(nome).setTelefone("123").confirmar();
		assertTrue(entregadoresPage.foiExibidaMensagemDeSucesso("Sucesso: Entregador '"+ nome+"' salvo"));
	}
	
	@Test
	public void test2AdicionarTaxaNoEntregador() {
		entregadoresPage.visita();
		entregadoresPage.verificaERealizarLogin();
		entregadoresPage.editar().novaTaxa().setValor("2.00").setDistancia("15.5").confirmar().confirmar();
		assertTrue(entregadoresPage.foiExibidaMensagemDeSucesso("Sucesso: Entregador 'Nikson' salvo"));
	}
	
	@Test
	public void test3EditarEntregador() {
		entregadoresPage.visita();
		entregadoresPage.verificaERealizarLogin();
		entregadoresPage.editar().setNome("Niks").confirmar();
		assertTrue(entregadoresPage.foiExibidaMensagemDeSucesso("Sucesso: Entregador 'Niks' salvo"));
	}
	
	@Test
	public void test4BuscarEntregador() {
		entregadoresPage.visita();
		entregadoresPage.verificaERealizarLogin();
		String nome = "Yaggo";
		entregadoresPage.buscar(nome);
	}
	
	@Test
	public void test5ExcluirTaxaNoEntregador() {
		entregadoresPage.visita();
		entregadoresPage.verificaERealizarLogin();
		entregadoresPage.editar().apagarTaxa().confirmar().confirmar();
		assertTrue(entregadoresPage.foiExibidaMensagemDeSucesso("Sucesso: Entregador 'Niks' salvo"));
	}
	
	@Test
	public void test6ExcluirEntregador() {
		entregadoresPage.visita();
		entregadoresPage.verificaERealizarLogin();
		entregadoresPage.apagar().confirmar();
		assertTrue(entregadoresPage.foiExibidaMensagemDeSucesso("Sucesso: Entregador 'Niks' excluído"));
	}
	
}
