package br.com.churrascaria.falha;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.churrascaria.AbstractTest;

public class TestesFalhaFuncionario extends AbstractTest {

	@Test
	public void adicionarFuncionarioSemValores() {
		funcionarioPage.visita();
		funcionarioPage.verificaERealizarLogin();
		funcionarioPage.nova().confirmar();
		//Não sei qual mensagem colocar aqui
	}

	@Test
	public void adicionarFuncionarioSemNome() {
		funcionarioPage.visita();
		funcionarioPage.verificaERealizarLogin();
		funcionarioPage.nova().setLogin("nik").setSenha("123").setConfirmarSenha("123").setTipoFuncionario()
				.confirmar();
		assertTrue(funcionarioPage.foiExibidaMensagemDeFalha("Nome: Erro de validação: o valor é necessário."));
	}

	@Test
	public void adicionarFuncionarioSemLogin() {
		funcionarioPage.visita();
		funcionarioPage.verificaERealizarLogin();
		funcionarioPage.nova().setNome("Nikson").setSenha("123").setConfirmarSenha("123").setTipoFuncionario()
				.confirmar();
		assertTrue(funcionarioPage.foiExibidaMensagemDeFalha("Login: Erro de validação: o valor é necessário."));
	}

	@Test
	public void adicionarFuncionarioSemSenha() {
		funcionarioPage.visita();
		funcionarioPage.verificaERealizarLogin();
		funcionarioPage.nova().setNome("Nikson").setLogin("nik").setConfirmarSenha("123").setTipoFuncionario()
				.confirmar();
		assertTrue(funcionarioPage.foiExibidaMensagemDeFalha("Senha: Erro de validação: o valor é necessário."));
	}

	@Test
	public void adicionarFuncionarioSemConfirmarSenha() {
		funcionarioPage.visita();
		funcionarioPage.verificaERealizarLogin();
		funcionarioPage.nova().setNome("Nikson").setLogin("nik").setSenha("123").setTipoFuncionario().confirmar();
		assertTrue(
				funcionarioPage.foiExibidaMensagemDeFalha("Confirmar senha: Erro de validação: o valor é necessário."));
	}

	@Test
	public void adicionarFuncionarioSemTipo() {
		funcionarioPage.visita();
		funcionarioPage.verificaERealizarLogin();
		funcionarioPage.nova().setNome("Nikson").setLogin("nik").setSenha("123").setConfirmarSenha("123").confirmar();
		assertTrue(funcionarioPage
				.foiExibidaMensagemDeFalha("Tipo do funcionario: Erro de validação: o valor é necessário."));
	}

	@Test
	public void adicionarFuncionarioComLoginRepetido() {
		funcionarioPage.visita();
		funcionarioPage.verificaERealizarLogin();
		funcionarioPage.nova().setNome("Hello World").setLogin("yaggo").setSenha("123").setConfirmarSenha("123")
				.setTipoFuncionario().confirmar();
		assertTrue(funcionarioPage.foiExibidaMensagemDeFalha("Erro: Login já existente: yaggo"));
	}

}
