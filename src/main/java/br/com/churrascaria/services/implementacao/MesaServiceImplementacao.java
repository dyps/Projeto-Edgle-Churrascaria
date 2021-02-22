package br.com.churrascaria.services.implementacao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.dao.MesaDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.Mesa;
import br.com.churrascaria.filter.MesaFilter;
import br.com.churrascaria.services.CRUDService;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;

@Named
@RequestScoped
public class MesaServiceImplementacao implements CRUDService<Mesa, MesaFilter> {
	
	@Inject
	private MesaDAO mesaDAO;

	@Override
	public void save(Mesa mesa) throws ServiceEdgleChurrascariaException {
		try {
			mesaDAO.save(mesa);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	@Override
	public Mesa update(Mesa mesa) throws ServiceEdgleChurrascariaException {
		try {
			return mesaDAO.update(mesa);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	@Override
	public void delete(Mesa mesa) throws ServiceEdgleChurrascariaException {
		try {
			mesaDAO.delete(mesa);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	@Override
	public Mesa getByID(long userId) throws ServiceEdgleChurrascariaException {
		MesaFilter filter = new MesaFilter();
		filter.setId(userId);
		return findBy(filter).get(0);
	}

	@Override
	public List<Mesa> findBy(MesaFilter filter) throws ServiceEdgleChurrascariaException {
		try {
			filter.validate();
			return mesaDAO.findBy(filter);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	@Override
	public List<Mesa> getAll() throws ServiceEdgleChurrascariaException {
		try {
			return mesaDAO.getAll();
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

}
