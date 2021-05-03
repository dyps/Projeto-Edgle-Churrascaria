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

import br.com.churrascaria.dao.ObservacaoPadraoDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.ObservacaoPadrao;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.ObservacoesPadraoServiceImplementacao;

@RunWith(MockitoJUnitRunner.class)
public class TestServiceObservacoes {

	private ObservacoesPadraoServiceImplementacao observacoesPadraoServiceImplementacao;

	@Mock
	private ObservacaoPadraoDAO observacoesPadraoDAO;

	@Before
	public void setUp() {
		observacoesPadraoServiceImplementacao = new ObservacoesPadraoServiceImplementacao();
		observacoesPadraoServiceImplementacao.setObservacoesPadraoDAO(observacoesPadraoDAO);
	}

	@Test(expected = ServiceEdgleChurrascariaException.class)
	public void test1CriarObservacaoSemValor() throws ServiceEdgleChurrascariaException {
		ObservacaoPadrao observacao = new ObservacaoPadrao();
		observacoesPadraoServiceImplementacao.save(observacao);
	}

	@Test(expected = ServiceEdgleChurrascariaException.class)
	public void test2CriarObservacaoRepetida()
			throws ServiceEdgleChurrascariaException, PersistenciaEdgleChurrascariaException {
		ObservacaoPadrao observacao = new ObservacaoPadrao();
		observacao.setDescricao("Sem gelo");
		List<ObservacaoPadrao> lista = new ArrayList<ObservacaoPadrao>();
		lista.add(observacao);
		when(observacoesPadraoDAO.getAll()).thenReturn(lista);

		observacoesPadraoServiceImplementacao.save(observacao);
	}

	@Test
	public void test3CriarObservacaoPadrao() throws ServiceEdgleChurrascariaException {
		ObservacaoPadrao observacao = new ObservacaoPadrao();
		observacao.setDescricao("Sem gelo");
		observacoesPadraoServiceImplementacao.save(observacao);
	}

	@Test
	public void test4ExcluirObservacaoPadrao() throws ServiceEdgleChurrascariaException {
		ObservacaoPadrao observacao = new ObservacaoPadrao();
		observacao.setDescricao("Sem gelo");
		observacoesPadraoServiceImplementacao.delete(observacao);
	}

	@Test
	public void test5BuscarTodasObservacoes() throws Exception {
		ObservacaoPadrao observacao = new ObservacaoPadrao();
		observacao.setDescricao("Sem gelo");
		List<ObservacaoPadrao> lista = new ArrayList<ObservacaoPadrao>();
		lista.add(observacao);
		when(observacoesPadraoDAO.getAll()).thenReturn(lista);

		List<ObservacaoPadrao> lista2 = observacoesPadraoServiceImplementacao.getAll();
		if (lista2.size() > 0)
			assertTrue(true);
		else
			throw new Exception();
	}

}
