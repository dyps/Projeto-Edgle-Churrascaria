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

import br.com.churrascaria.dao.ClienteDAO;
import br.com.churrascaria.dao.EnderecoDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.Cliente;
import br.com.churrascaria.entities.Endereco;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.ClienteServiceImplementacao;

@RunWith(MockitoJUnitRunner.class)
public class TestBeanManagerCliente {

	private ClienteServiceImplementacao clienteServiceImplementacao;

	@Mock
	private ClienteDAO clienteDAO;

	@Mock
	private EnderecoDAO enderecoDAO;

	@Before
	public void setUp() {
		clienteServiceImplementacao = new ClienteServiceImplementacao();
		clienteServiceImplementacao.setClienteDAO(clienteDAO);
		clienteServiceImplementacao.setEnderecoDAO(enderecoDAO);
	}

	@Test(expected = ServiceEdgleChurrascariaException.class)
	public void test1CriarClienteSemValores() throws ServiceEdgleChurrascariaException {
		Cliente cliente = new Cliente();
		clienteServiceImplementacao.save(cliente);
	}

	@Test(expected = ServiceEdgleChurrascariaException.class)
	public void test2CriarClienteComTelefoneRepetido()
			throws ServiceEdgleChurrascariaException, PersistenciaEdgleChurrascariaException {
		Cliente cliente = new Cliente();
		cliente.setId(1l);
		cliente.setNome("Yaggo");
		cliente.setTelefone("123");
		List<Cliente> lista = new ArrayList<Cliente>();
		lista.add(cliente);
		when(clienteDAO.getAll()).thenReturn(lista);
		
		Cliente cliente2 = new Cliente();
		cliente2.setId(2l);
		cliente2.setNome("Nikson");
		cliente2.setTelefone("123");

		clienteServiceImplementacao.save(cliente2);
	}

	@Test
	public void test3CriarCliente() throws ServiceEdgleChurrascariaException {
		Cliente cliente = new Cliente();
		cliente.setId((long) 1);
		cliente.setNome("Yaggo");
		cliente.setTelefone("123");
		clienteServiceImplementacao.save(cliente);
	}

	@Test
	public void test4AdicionarEnderecoNoCliente() throws ServiceEdgleChurrascariaException {
		Cliente cliente = new Cliente();
		cliente.setId((long) 1);
		cliente.setNome("Yaggo");
		cliente.setTelefone("123");

		Endereco endereco = new Endereco();
		endereco.setNome("Escritorio");
		endereco.setLogradouro("Rua A");
		endereco.setNumero(22);
		endereco.setCliente(cliente);
		clienteServiceImplementacao.saveEndereco(endereco);
		clienteServiceImplementacao.save(cliente);
	}

//	@Test
//	public void test5EditarCliente() {
//
//	}

	@Test
	public void test6BuscarCliente() throws Exception {
		Cliente cliente = new Cliente();
		cliente.setId((long) 1);
		cliente.setNome("Yaggo");
		cliente.setTelefone("123");
		List<Cliente> lista = new ArrayList<Cliente>();
		lista.add(cliente);
		when(clienteDAO.getAll()).thenReturn(lista);

		List<Cliente> lista2 = clienteServiceImplementacao.getAll();
		for (Cliente clienteAux : lista2) {
			if (clienteAux.getNome() == "Yaggo") {
				assertTrue(true);
				return;
			}
		}
		throw new Exception();
	}

	@Test
	public void test7ExcluirEnderecoNoCliente() throws ServiceEdgleChurrascariaException {
		Cliente cliente = new Cliente();
		cliente.setId((long) 1);
		cliente.setNome("Yaggo");
		cliente.setTelefone("123");

		Endereco endereco = new Endereco();
		endereco.setNome("Escritorio");
		endereco.setLogradouro("Rua A");
		endereco.setNumero(22);
		endereco.setCliente(cliente);
		clienteServiceImplementacao.deleteEndereco(endereco);
	}

	@Test
	public void test8ExcluirCliente() throws ServiceEdgleChurrascariaException {
		Cliente cliente = new Cliente();
		cliente.setId((long) 1);
		cliente.setNome("Yaggo");
		cliente.setTelefone("123");
		clienteServiceImplementacao.delete(cliente);
	}

}
