package br.com.churrascaria.services.implementacao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.dao.EntidadeDAO;
import br.com.churrascaria.dao.EntregadorDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.Entregador;
import br.com.churrascaria.filter.EntregadorFilter;
import br.com.churrascaria.services.CRUDService;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;

@Named
@RequestScoped
public class EntregadorServiceImplementacao extends CRUDService<Entregador> {

	@Inject
	private EntregadorDAO entregadorDAO;



	public Entregador getByID(Long userId) throws ServiceEdgleChurrascariaException {
		EntregadorFilter filter = new EntregadorFilter();
		filter.setId(userId);
		return findBy(filter).get(0);
	}

	public List<Entregador> findBy(EntregadorFilter filter) throws ServiceEdgleChurrascariaException {
		try {
			filter.validate();
			return entregadorDAO.findBy(filter);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	@Override
	protected EntidadeDAO<Entregador> getEntidadeDAO() {
		return entregadorDAO;
	}


}
