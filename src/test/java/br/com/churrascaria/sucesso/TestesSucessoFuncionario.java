package br.com.churrascaria.sucesso;

import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.churrascaria.AbstractTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestesSucessoFuncionario extends AbstractTest {

	@Test
	public void test1AdicionarFuncionario() {
		funcionarioPage.visita();
		funcionarioPage.verificaERealizarLogin();
		String nome = "Nikson";
		funcionarioPage.nova().setNome(nome).setLogin("nik").setSenha("123").setConfirmarSenha("123")
				.setTipoFuncionario().confirmar();
		assertTrue(funcionarioPage.foiExibidaMensagemDeSucesso("Sucesso: Funcionario '" + nome + "' salvo"));
	}

	@Test
	public void test2EditarFuncionario() {
		funcionarioPage.visita();
		funcionarioPage.verificaERealizarLogin();
		String nome = "Yagg";
		funcionarioPage.editar().setNome(nome).confirmar();
		assertTrue(funcionarioPage.foiExibidaMensagemDeSucesso("Sucesso: Funcionario '" + nome + "' salvo"));
	}

	@Test
	public void test3BuscarFuncionario() {
		funcionarioPage.visita();
		funcionarioPage.verificaERealizarLogin();
		String nome = "Yaggo";
		funcionarioPage.buscar(nome);
	}
	
	@Test
	public void test4ExcluirFuncionario() {
		funcionarioPage.visita();
		funcionarioPage.verificaERealizarLogin();
		funcionarioPage.apagar().confirmar();
		assertTrue(funcionarioPage.foiExibidaMensagemDeSucesso("Sucesso: Funcionario 'Syble' excluído"));
	}

}
