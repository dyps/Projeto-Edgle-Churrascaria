package br.com.churrascaria.sucesso;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import br.com.churrascaria.AbstractTest;


public class TestesSucessoMesa extends AbstractTest {

	@Test
	public void adicionarMesa() {
		mesasPage.visita();
		mesasPage.verificaERealizarLogin();
		int numero = 1;
		List<Integer> salvas = mesasPage.mesasCadastradas();
		while (salvas.contains(numero)) 
			numero++;
		mesasPage.nova()
		.setNumero(numero+"")
		.confirmar();
		assertTrue(mesasPage.foiExibidaMensagemDeSucesso("Sucesso: Mesa '"+ numero+"' salva"));
	}

}
