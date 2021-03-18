package br.com.churrascaria.falha;

import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.churrascaria.AbstractTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestesFalhaFuncionario extends AbstractTest {

	@Test
	public void test1AdicionarFuncionarioSemValores() {
		funcionarioPage.visita();
		funcionarioPage.verificaERealizarLogin();
		funcionarioPage.nova().confirmar();
		//Não sei qual mensagem colocar aqui
	}

	@Test
	public void test2AdicionarFuncionarioSemNome() {
		funcionarioPage.visita();
		funcionarioPage.verificaERealizarLogin();
		funcionarioPage.nova().setLogin("nik").setSenha("123").setConfirmarSenha("123").setTipoFuncionario()
				.confirmar();
		assertTrue(funcionarioPage.foiExibidaMensagemDeFalha("Nome: Erro de validação: o valor é necessário."));
	}

	@Test
	public void test3AdicionarFuncionarioSemLogin() {
		funcionarioPage.visita();
		funcionarioPage.verificaERealizarLogin();
		funcionarioPage.nova().setNome("Nikson").setSenha("123").setConfirmarSenha("123").setTipoFuncionario()
				.confirmar();
		assertTrue(funcionarioPage.foiExibidaMensagemDeFalha("Login: Erro de validação: o valor é necessário."));
	}

	@Test
	public void test4AdicionarFuncionarioSemSenha() {
		funcionarioPage.visita();
		funcionarioPage.verificaERealizarLogin();
		funcionarioPage.nova().setNome("Nikson").setLogin("nik").setConfirmarSenha("123").setTipoFuncionario()
				.confirmar();
		assertTrue(funcionarioPage.foiExibidaMensagemDeFalha("Senha: Erro de validação: o valor é necessário."));
	}

	@Test
	public void test5AdicionarFuncionarioSemConfirmarSenha() {
		funcionarioPage.visita();
		funcionarioPage.verificaERealizarLogin();
		funcionarioPage.nova().setNome("Nikson").setLogin("nik").setSenha("123").setTipoFuncionario().confirmar();
		assertTrue(
				funcionarioPage.foiExibidaMensagemDeFalha("Confirmar senha: Erro de validação: o valor é necessário."));
	}

	@Test
	public void test6AdicionarFuncionarioSemTipo() {
		funcionarioPage.visita();
		funcionarioPage.verificaERealizarLogin();
		funcionarioPage.nova().setNome("Nikson").setLogin("nik").setSenha("123").setConfirmarSenha("123").confirmar();
		assertTrue(funcionarioPage
				.foiExibidaMensagemDeFalha("Tipo do funcionario: Erro de validação: o valor é necessário."));
	}

	@Test
	public void test7AdicionarFuncionarioComLoginRepetido() {
		funcionarioPage.visita();
		funcionarioPage.verificaERealizarLogin();
		funcionarioPage.nova().setNome("Hello World").setLogin("yaggo").setSenha("123").setConfirmarSenha("123")
				.setTipoFuncionario().confirmar();
		assertTrue(funcionarioPage.foiExibidaMensagemDeFalha("Erro: Login já existente: yaggo"));
	}

}
