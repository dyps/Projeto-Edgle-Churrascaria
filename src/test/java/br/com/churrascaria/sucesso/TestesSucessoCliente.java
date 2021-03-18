package br.com.churrascaria.sucesso;

import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.churrascaria.AbstractTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestesSucessoCliente extends AbstractTest {

	@Test
	public void test1AdicionarCliente() {
		clientePage.visita();
		clientePage.verificaERealizarLogin();
		clientePage.nova().setNome("Nikson").setTelefone("123").confirmar();
		assertTrue(clientePage.foiExibidaMensagemDeSucesso("Sucesso: Cliente 'Nikson' salvo"));
	}

	@Test
	public void test2AdicionarEnderecoNoCliente() {
		clientePage.visita();
		clientePage.verificaERealizarLogin();
		clientePage.editar().novoEndereco().setNome("Casa").setLogradouro("Rua a").setNumero("0")
				.setComplemento("esquina").confirmar().confirmar();
		assertTrue(clientePage.foiExibidaMensagemDeSucesso("Sucesso: Cliente 'Nikson' salvo"));
	}

	@Test
	public void test3EditarCliente() {
		clientePage.visita();
		clientePage.verificaERealizarLogin();
		clientePage.editar().setNome("Niks").confirmar();
		assertTrue(clientePage.foiExibidaMensagemDeSucesso("Sucesso: Cliente 'Niks' salvo"));
	}
	
	@Test
	public void test4BuscarCliente() {
		clientePage.visita();
		clientePage.verificaERealizarLogin();
		clientePage.buscar("Yaggo");
	}
	
	@Test
	public void test5ExcluirEnderecoNoCliente() {
		clientePage.visita();
		clientePage.verificaERealizarLogin();
		clientePage.editar().apagarEndereco().confirmar().confirmar();
		assertTrue(clientePage.foiExibidaMensagemDeSucesso("Sucesso: Cliente 'Niks' salvo"));
	}

	@Test
	public void test6ExcluirCliente() {
		clientePage.visita();
		clientePage.verificaERealizarLogin();
		clientePage.apagar().confirmar();
		assertTrue(clientePage.foiExibidaMensagemDeSucesso("Sucesso: Cliente 'Niks' excluído"));
	}

}
