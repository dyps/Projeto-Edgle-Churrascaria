package br.com.churrascaria.unidade;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.churrascaria.dao.EntregadorDAO;
import br.com.churrascaria.dao.TaxaEntregaDAO;
import br.com.churrascaria.entities.Entregador;
import br.com.churrascaria.entities.TaxaEntrega;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.EntregadorServiceImplementacao;

@RunWith(MockitoJUnitRunner.class)
public class TestBeanManagerEntregador {

	private EntregadorServiceImplementacao entregadorServiceImplementacao;

	@Mock
	private EntregadorDAO entregadorDAO;

	@Mock
	private TaxaEntregaDAO taxaEntregaDAO;

	@Before
	public void setUp() {
		entregadorServiceImplementacao = new EntregadorServiceImplementacao();
		entregadorServiceImplementacao.setEntregadorDAO(entregadorDAO);
		entregadorServiceImplementacao.setTaxaEntregaDAO(taxaEntregaDAO);
	}

	@Test(expected = ServiceEdgleChurrascariaException.class)
	public void test1CriarEntregadorSemValores() throws ServiceEdgleChurrascariaException {
		Entregador entregador = new Entregador();
		entregadorServiceImplementacao.save(entregador);
	}

	@Test
	public void test1CriarEntregador() throws ServiceEdgleChurrascariaException {
		Entregador entregador = new Entregador();
		entregador.setId((long) 1);
		entregador.setNome("Yaggo");
		entregador.setTelefone("123");
		entregadorServiceImplementacao.save(entregador);
	}

	@Test
	public void test2AdicionarTaxaNoEntregador() throws ServiceEdgleChurrascariaException {
		Entregador entregador = new Entregador();
		entregador.setId((long) 1);
		entregador.setNome("Yaggo");
		entregador.setTelefone("123");

		TaxaEntrega taxa = new TaxaEntrega();
		taxa.setValor((double) 10);
		taxa.setDistanciaMaxima((double) 5);
		taxa.setEntregador(entregador);
		entregadorServiceImplementacao.saveTaxa(taxa);
		entregadorServiceImplementacao.save(entregador);

	}

//	@Test
//	public void test3EditarEntregador() {
//
//	}

	@Test
	public void test4BuscarEntregador() throws Exception {
		Entregador entregador = new Entregador();
		entregador.setId((long) 1);
		entregador.setNome("Yaggo");
		entregador.setTelefone("123");
		List<Entregador> lista = new ArrayList<Entregador>();
		lista.add(entregador);
		when(entregadorDAO.getAll()).thenReturn(lista);

		List<Entregador> lista2 = entregadorServiceImplementacao.getAll();
		for (Entregador funcionarioAux : lista2) {
			if (funcionarioAux.getNome() == "Yaggo") {
				assertTrue(true);
				return;
			}
		}
		throw new Exception();
	}

	@Test
	public void test5ExcluirTaxaNoEntregador() throws ServiceEdgleChurrascariaException {
		Entregador entregador = new Entregador();
		entregador.setId((long) 1);
		entregador.setNome("Yaggo");
		entregador.setTelefone("123");
		
		TaxaEntrega taxa = new TaxaEntrega();
		taxa.setValor((double) 10);
		taxa.setDistanciaMaxima((double) 5);
		taxa.setEntregador(entregador);
		entregadorServiceImplementacao.deleteTaxa(taxa);
	}

	@Test
	public void test6ExcluirEntregador() throws ServiceEdgleChurrascariaException {
		Entregador entregador = new Entregador();
		entregador.setId((long) 1);
		entregador.setNome("Yaggo");
		entregador.setTelefone("123");
		entregadorServiceImplementacao.delete(entregador);
	}

}
