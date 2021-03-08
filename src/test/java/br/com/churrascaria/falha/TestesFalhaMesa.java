package br.com.churrascaria.falha;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.churrascaria.AbstractTest;

public class TestesFalhaMesa extends AbstractTest {

	@Test
	public void adicionarMesaSemValor() {
		mesasPage.visita();
		mesasPage.verificaERealizarLogin();
		mesasPage.nova().confirmar();
		assertTrue(mesasPage.foiExibidaMensagemDeFalha("Erro: O número da mesa é necessário"));
	}

	@Test
	public void adicionarMesaComLetras() {
		mesasPage.visita();
		mesasPage.verificaERealizarLogin();
		mesasPage.nova().setNumero("abc").confirmar();
		assertTrue(mesasPage.foiExibidaMensagemDeFalha(
				"Numero: 'abc' deve ser um número entre -2147483648 e 2147483647 Exemplo: 9346"));
	}
	
	@Test
	public void adicionarMesaRepetida() {
		mesasPage.visita();
		mesasPage.verificaERealizarLogin();
		mesasPage.nova()
		.setNumero("1")
		.confirmar();
		assertTrue(mesasPage.foiExibidaMensagemDeFalha("Erro: O número da mesa não pode ser repetida"));
	}

}
