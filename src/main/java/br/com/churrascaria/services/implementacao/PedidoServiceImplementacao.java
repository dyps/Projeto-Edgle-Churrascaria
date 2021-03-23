package br.com.churrascaria.services.implementacao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.dao.EntidadeDAO;
import br.com.churrascaria.dao.PedidoDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.Pedido;
import br.com.churrascaria.services.CRUDService;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;

@Named
@RequestScoped
public class PedidoServiceImplementacao extends CRUDService<Pedido> {
	
	@Inject
	private PedidoDAO entidadeDAO;

	@Override
	protected void validar(Pedido entidade) throws ServiceEdgleChurrascariaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pedido getByID(Long id) throws ServiceEdgleChurrascariaException {
		try {
			return entidadeDAO.getByID(id);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	@Override
	protected EntidadeDAO<Pedido> getEntidadeDAO() {
		return entidadeDAO;
	}
}
