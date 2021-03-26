package br.com.churrascaria.unidade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.churrascaria.beans.mesa.ManageMesa;
import br.com.churrascaria.dao.EntidadeDAO;
import br.com.churrascaria.dao.MesaDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.Mesa;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.MesaServiceImplementacao;

@RunWith(MockitoJUnitRunner.class)
public class TestBeanManagerMesa {
	
	private MesaServiceImplementacao mesaServiceImplementacao;
	
	@Mock
	private MesaDAO mesaDAO;
	
	@Before
	public void setUp() {
		mesaServiceImplementacao = new MesaServiceImplementacao();
		mesaServiceImplementacao.setMesaDAO(mesaDAO);
		
	}
	
	
	
	
	@Test(expected = ServiceEdgleChurrascariaException.class)
	public void test1AdicionarCriarMesaSemNumero() throws ServiceEdgleChurrascariaException {
		Mesa mesa = new Mesa();
		mesaServiceImplementacao.save(mesa);
	}
	@Test(expected = ServiceEdgleChurrascariaException.class)
	public void test1AdicionarCriarMesaNula() throws ServiceEdgleChurrascariaException {
		mesaServiceImplementacao.save(null);
	}
	@Test(expected = ServiceEdgleChurrascariaException.class)
	public void test1AdicionarCriarMesaComNomeRepetido() throws ServiceEdgleChurrascariaException, PersistenciaEdgleChurrascariaException {
		Mesa mesa = new Mesa();
		mesa.setNumero(1);
		List<Mesa> lista = new ArrayList<Mesa>();
		lista.add(mesa);
		when(mesaDAO.getAll()).thenReturn(lista);
		
		mesaServiceImplementacao.save(mesa);
	}

}
