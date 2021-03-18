package br.com.churrascaria.sucesso;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.churrascaria.AbstractTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestesSucessoMesa extends AbstractTest {

	@Test
	public void test1AdicionarMesa() {
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
	
	@Test
	public void test2BuscarMesa() {
		mesasPage.visita();
		mesasPage.verificaERealizarLogin();
		String numeroMesa = "1";
		mesasPage.buscar(numeroMesa);
	}
	
	@Test
	public void test3ExcluirMesa() {
		mesasPage.visita();
		mesasPage.verificaERealizarLogin();
		mesasPage.apagar().confirmar();
		assertTrue(mesasPage.foiExibidaMensagemDeSucesso("Sucesso: Mesa '1' excluída"));
	}

}
