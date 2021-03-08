package br.com.churrascaria.sucesso;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.churrascaria.AbstractTest;

public class TestesSucessoCliente extends AbstractTest {

	@Test
	public void adicionarCliente() {
		clientePage.visita();
		clientePage.verificaERealizarLogin();
		clientePage.nova().setNome("Nikson").setTelefone("123").confirmar();
		assertTrue(clientePage.foiExibidaMensagemDeSucesso("Sucesso: Cliente 'Nikson' salvo"));
	}

	@Test
	public void adicionarEnderecoNoCliente() {
		clientePage.visita();
		clientePage.verificaERealizarLogin();
		clientePage.editar().novoEndereco().setNome("Casa").setLogradouro("Rua a").setNumero("0")
				.setComplemento("esquina").confirmar().confirmar();
		assertTrue(clientePage.foiExibidaMensagemDeSucesso("Sucesso: Cliente 'Nikson' salvo"));
	}

	@Test
	public void editarCliente() {
		clientePage.visita();
		clientePage.verificaERealizarLogin();
		clientePage.editar().setNome("Niks").confirmar();
		assertTrue(clientePage.foiExibidaMensagemDeSucesso("Sucesso: Cliente 'Niks' salvo"));
	}

	@Test
	public void excluirCliente() {
		clientePage.visita();
		clientePage.verificaERealizarLogin();
		clientePage.apagar().confirmar();
		assertTrue(clientePage.foiExibidaMensagemDeSucesso("Sucesso: Cliente 'Niks' excluído"));
	}

	@Test
	public void excluirEnderecoNoCliente() {
		clientePage.visita();
		clientePage.verificaERealizarLogin();
		clientePage.editar().apagarEndereco().confirmar().confirmar();
		assertTrue(clientePage.foiExibidaMensagemDeSucesso("Sucesso: Cliente 'Nikson' salvo"));
	}

	@Test
	public void buscarCliente() {
		clientePage.visita();
		clientePage.verificaERealizarLogin();
		clientePage.buscar("Yaggo");
	}

}
