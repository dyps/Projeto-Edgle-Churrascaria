package br.com.churrascaria.sucesso;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.churrascaria.AbstractTest;

public class TestesSucessoFuncionario extends AbstractTest {

	@Test
	public void adicionarFuncionario() {
		funcionarioPage.visita();
		funcionarioPage.verificaERealizarLogin();
		String nome = "Nikson";
		funcionarioPage.nova().setNome(nome).setLogin("nik").setSenha("123").setConfirmarSenha("123")
				.setTipoFuncionario().confirmar();
		assertTrue(funcionarioPage.foiExibidaMensagemDeSucesso("Sucesso: Funcionario '" + nome + "' salvo"));
	}

	@Test
	public void excluirFuncionario() {
		funcionarioPage.visita();
		funcionarioPage.verificaERealizarLogin();
		funcionarioPage.apagar().confirmar();
		assertTrue(funcionarioPage.foiExibidaMensagemDeSucesso("Sucesso: Funcionario 'Syble' excluído"));
	}

	@Test
	public void editarFuncionario() {
		funcionarioPage.visita();
		funcionarioPage.verificaERealizarLogin();
		String nome = "Yagg";
		funcionarioPage.editar().setNome(nome).confirmar();
		assertTrue(funcionarioPage.foiExibidaMensagemDeSucesso("Sucesso: Funcionario '" + nome + "' salvo"));
	}

	@Test
	public void buscarFuncionario() {
		funcionarioPage.visita();
		funcionarioPage.verificaERealizarLogin();
		String nome = "Yaggo";
		funcionarioPage.buscar(nome);
	}

}
