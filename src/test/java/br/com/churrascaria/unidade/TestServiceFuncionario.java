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

import br.com.churrascaria.dao.FuncionarioDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.Funcionario;
import br.com.churrascaria.entities.TipoDeFuncionario;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.FuncionarioServiceImplementacao;

@RunWith(MockitoJUnitRunner.class)
public class TestServiceFuncionario {

	private FuncionarioServiceImplementacao funcionarioServiceImplementacao;

	@Mock
	private FuncionarioDAO funcionarioDAO;

	@Before
	public void setUp() {
		funcionarioServiceImplementacao = new FuncionarioServiceImplementacao();
		funcionarioServiceImplementacao.setFuncionarioDAO(funcionarioDAO);
	}

	@Test(expected = ServiceEdgleChurrascariaException.class)
	public void test1CriarFuncionarioSemValores() throws ServiceEdgleChurrascariaException {
		Funcionario funcionario = new Funcionario();
		funcionarioServiceImplementacao.save(funcionario);
	}

	@Test(expected = ServiceEdgleChurrascariaException.class)
	public void test2CriarFuncionarioComLoginRepetido()
			throws ServiceEdgleChurrascariaException, PersistenciaEdgleChurrascariaException {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(1l);
		funcionario.setNome("Yaggo");
		funcionario.setLogin("yaggo");
		funcionario.setSenha("123");
		funcionario.setTipoDeFuncionario(TipoDeFuncionario.GERENTE);
		List<Funcionario> lista = new ArrayList<Funcionario>();
		lista.add(funcionario);
		when(funcionarioDAO.getAll()).thenReturn(lista);
		
		Funcionario funcionario2 = new Funcionario();
		funcionario2.setId(2l);
		funcionario2.setNome("Nikson");
		funcionario2.setLogin("yaggo");
		funcionario2.setSenha("321");
		funcionario2.setTipoDeFuncionario(TipoDeFuncionario.GERENTE);

		funcionarioServiceImplementacao.save(funcionario2);
	}

	@Test
	public void test3CriarFuncionario() throws ServiceEdgleChurrascariaException {
		Funcionario funcionario = new Funcionario();
		funcionario.setId((long) 1);
		funcionario.setNome("Yaggo");
		funcionario.setLogin("yaggo");
		funcionario.setSenha("123");
		funcionario.setTipoDeFuncionario(TipoDeFuncionario.GERENTE);
		funcionarioServiceImplementacao.save(funcionario);
	}

//	@Test
//	public void test4EditarFuncionario() {
//
//	}

	@Test
	public void test5BuscarFuncionario() throws Exception {
		Funcionario funcionario = new Funcionario();
		funcionario.setId((long) 1);
		funcionario.setNome("Yaggo");
		funcionario.setLogin("yaggo");
		funcionario.setSenha("123");
		funcionario.setTipoDeFuncionario(TipoDeFuncionario.GERENTE);
		List<Funcionario> lista = new ArrayList<Funcionario>();
		lista.add(funcionario);
		when(funcionarioDAO.getAll()).thenReturn(lista);

		List<Funcionario> lista2 = funcionarioServiceImplementacao.getAll();
		for (Funcionario funcionarioAux : lista2) {
			if (funcionarioAux.getNome() == "Yaggo") {
				assertTrue(true);
				return;
			}
		}
		throw new Exception();
	}

	@Test
	public void test6ExcluirFuncionario() throws ServiceEdgleChurrascariaException {
		Funcionario funcionario = new Funcionario();
		funcionario.setId((long) 1);
		funcionario.setNome("Yaggo");
		funcionario.setLogin("yaggo");
		funcionario.setSenha("123");
		funcionario.setTipoDeFuncionario(TipoDeFuncionario.GERENTE);
		funcionarioServiceImplementacao.delete(funcionario);
	}

}
