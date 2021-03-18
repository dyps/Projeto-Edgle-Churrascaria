package br.com.churrascaria.falha;

import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.churrascaria.AbstractTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestesFalhaCliente extends AbstractTest {

	@Test
	public void test1AdicionarClienteSemValores() {
		clientePage.visita();
		clientePage.verificaERealizarLogin();
		clientePage.nova().confirmar();
		assertTrue(clientePage.foiExibidaMensagemDeFalha("Erro: O nome do cliente é necessário"));
	}

	@Test
	public void test2AdicionarClienteSemNome() {
		clientePage.visita();
		clientePage.verificaERealizarLogin();
		clientePage.nova().setNome("").confirmar();
		assertTrue(clientePage.foiExibidaMensagemDeFalha("Erro: O nome do cliente é necessário"));
	}

	@Test
	public void test3AdicionarClienteComTelefoneRepetido() {
		clientePage.visita();
		clientePage.verificaERealizarLogin();
		clientePage.nova().setNome("Nikson").setTelefone("123").confirmar();

		clientePage.visita();
		clientePage.verificaERealizarLogin();
		clientePage.nova().setNome("Yaggo").setTelefone("123").confirmar();
		assertTrue(clientePage.foiExibidaMensagemDeFalha("Erro: O telefone do cliente não pode ser repetido"));
	}

	@Test
	public void test4AdicionarEnderecoSemNomeNoCliente() {
		clientePage.visita();
		clientePage.verificaERealizarLogin();
		clientePage.editar().novoEndereco().setLogradouro("Rua a").setNumero("0").setComplemento("esquina").confirmar()
				.confirmar();
		assertTrue(clientePage.foiExibidaMensagemDeFalha("Sucesso: Cliente 'Nikson' salvo"));
	}

	@Test
	public void test5AdicionarEnderecoSemLogradouroNoCliente() {
		clientePage.visita();
		clientePage.verificaERealizarLogin();
		clientePage.editar().novoEndereco().setNome("Casa").setNumero("0").setComplemento("esquina").confirmar()
				.confirmar();
		assertTrue(clientePage.foiExibidaMensagemDeFalha("Sucesso: Cliente 'Nikson' salvo"));
	}

	@Test
	public void test6AdicionarEnderecoSemNumeroNoCliente() {
		clientePage.visita();
		clientePage.verificaERealizarLogin();
		clientePage.editar().novoEndereco().setNome("Casa").setLogradouro("Rua a").setComplemento("esquina").confirmar()
				.confirmar();
		assertTrue(clientePage.foiExibidaMensagemDeFalha("Sucesso: Cliente 'Nikson' salvo"));
	}

}
