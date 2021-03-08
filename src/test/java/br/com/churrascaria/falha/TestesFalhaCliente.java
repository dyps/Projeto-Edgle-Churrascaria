package br.com.churrascaria.falha;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.churrascaria.AbstractTest;

public class TestesFalhaCliente extends AbstractTest {

	@Test
	public void adicionarClienteSemValores() {
		clientePage.visita();
		clientePage.verificaERealizarLogin();
		clientePage.nova().confirmar();
		assertTrue(clientePage.foiExibidaMensagemDeFalha("Erro: O nome do cliente é necessário"));
	}

	@Test
	public void adicionarClienteSemNome() {
		clientePage.visita();
		clientePage.verificaERealizarLogin();
		clientePage.nova().setNome("").confirmar();
		assertTrue(clientePage.foiExibidaMensagemDeFalha("Erro: O nome do cliente é necessário"));
	}

	@Test
	public void adicionarClienteComTelefoneRepetido() {
		clientePage.visita();
		clientePage.verificaERealizarLogin();
		clientePage.nova().setNome("Yaggo").setTelefone("123").confirmar();
		assertTrue(clientePage.foiExibidaMensagemDeFalha("Erro: O telefone do cliente não pode ser repetido"));
	}

	@Test
	public void adicionarEnderecoSemNomeNoCliente() {
		clientePage.visita();
		clientePage.verificaERealizarLogin();
		clientePage.editar().novoEndereco().setLogradouro("Rua a").setNumero("0").setComplemento("esquina").confirmar()
				.confirmar();
		assertTrue(clientePage.foiExibidaMensagemDeFalha("Sucesso: Cliente 'Nikson' salvo"));
	}

	@Test
	public void adicionarEnderecoSemLogradouroNoCliente() {
		clientePage.visita();
		clientePage.verificaERealizarLogin();
		clientePage.editar().novoEndereco().setNome("Casa").setNumero("0").setComplemento("esquina").confirmar()
				.confirmar();
		assertTrue(clientePage.foiExibidaMensagemDeFalha("Sucesso: Cliente 'Nikson' salvo"));
	}

	@Test
	public void adicionarEnderecoSemNumeroNoCliente() {
		clientePage.visita();
		clientePage.verificaERealizarLogin();
		clientePage.editar().novoEndereco().setNome("Casa").setLogradouro("Rua a").setComplemento("esquina").confirmar()
				.confirmar();
		assertTrue(clientePage.foiExibidaMensagemDeFalha("Sucesso: Cliente 'Nikson' salvo"));
	}

}
