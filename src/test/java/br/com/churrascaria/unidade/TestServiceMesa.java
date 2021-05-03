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

import br.com.churrascaria.dao.MesaDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.Mesa;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.MesaServiceImplementacao;

@RunWith(MockitoJUnitRunner.class)
public class TestServiceMesa {

	private MesaServiceImplementacao mesaServiceImplementacao;

	@Mock
	private MesaDAO mesaDAO;

	@Before
	public void setUp() {
		mesaServiceImplementacao = new MesaServiceImplementacao();
		mesaServiceImplementacao.setMesaDAO(mesaDAO);
	}

	@Test(expected = ServiceEdgleChurrascariaException.class)
	public void test1CriarMesaSemNumero() throws ServiceEdgleChurrascariaException {
		Mesa mesa = new Mesa();
		mesaServiceImplementacao.save(mesa);
	}

	@Test(expected = ServiceEdgleChurrascariaException.class)
	public void test2CriarMesaNula() throws ServiceEdgleChurrascariaException {
		mesaServiceImplementacao.save(null);
	}

	@Test(expected = ServiceEdgleChurrascariaException.class)
	public void test3CriarMesaComNumeroRepetido()
			throws ServiceEdgleChurrascariaException, PersistenciaEdgleChurrascariaException {
		Mesa mesa = new Mesa();
		mesa.setNumero(1);
		List<Mesa> lista = new ArrayList<Mesa>();
		lista.add(mesa);
		when(mesaDAO.getAll()).thenReturn(lista);

		mesaServiceImplementacao.save(mesa);
	}

	@Test
	public void test4CriarMesa() throws ServiceEdgleChurrascariaException {
		Mesa mesa = new Mesa();
		mesa.setNumero(1);
		mesaServiceImplementacao.save(mesa);
	}

	@Test
    public void test5BuscarMesa() throws Exception {
        Mesa mesa = new Mesa();
        mesa.setNumero(1);
        List<Mesa> lista = new ArrayList<Mesa>();
        lista.add(mesa);
        when(mesaDAO.getAll()).thenReturn(lista);

        List<Mesa> lista2 = mesaServiceImplementacao.getAll();
        for (Mesa mesaAux : lista2) {
            if (mesaAux.getNumero()==1) {
                assertTrue(true);
                return;
            }
        }
        throw new Exception();
    }

	@Test
	public void test6ExcluirMesa() throws ServiceEdgleChurrascariaException {
		Mesa mesa = new Mesa();
		mesa.setNumero(1);
		mesaServiceImplementacao.delete(mesa);
	}

}
