package br.com.churrascaria.falha;

import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.churrascaria.AbstractTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestesFalhaMesa extends AbstractTest {

	@Test
	public void test1AdicionarMesaSemValor() {
		mesasPage.visita();
		mesasPage.verificaERealizarLogin();
		mesasPage.nova().confirmar();
		assertTrue(mesasPage.foiExibidaMensagemDeFalha("Erro: O número da mesa é necessário"));
	}

	@Test
	public void test2AdicionarMesaComLetras() {
		mesasPage.visita();
		mesasPage.verificaERealizarLogin();
		mesasPage.nova().setNumero("abc").confirmar();
		assertTrue(mesasPage.foiExibidaMensagemDeFalha(
				"Numero: 'abc' deve ser um número entre -2147483648 e 2147483647 Exemplo: 9346"));
	}

	@Test
	public void test3AdicionarMesaRepetida() {
		mesasPage.visita();
		mesasPage.verificaERealizarLogin();
		mesasPage.nova().setNumero("1").confirmar();

		mesasPage.visita();
		mesasPage.verificaERealizarLogin();
		mesasPage.nova().setNumero("1").confirmar();
		assertTrue(mesasPage.foiExibidaMensagemDeFalha("Erro: O número da mesa não pode ser repetida"));
	}

}
