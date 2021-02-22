package br.com.churrascaria.services.implementacao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.dao.EntidadeDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.dao.TaxaEntregaDAO;
import br.com.churrascaria.entities.TaxaEntrega;
import br.com.churrascaria.filter.TaxaEntregaFilter;
import br.com.churrascaria.services.CRUDService;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;

@Named
@RequestScoped
public class TaxaEntregaServiceImplementacao extends CRUDService<TaxaEntrega> {

	@Inject
	private TaxaEntregaDAO taxaEntregaDAO;

	public TaxaEntrega getByID(Long userId) throws ServiceEdgleChurrascariaException {
		TaxaEntregaFilter filter = new TaxaEntregaFilter();
		filter.setId(userId);
		return findBy(filter).get(0);
	}

	public List<TaxaEntrega> findBy(TaxaEntregaFilter filter) throws ServiceEdgleChurrascariaException {
		try {
			filter.validate();
			return taxaEntregaDAO.findBy(filter);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	@Override
	protected EntidadeDAO<TaxaEntrega> getEntidadeDAO() {
		return taxaEntregaDAO;
	}

	@Override
	protected void validar(TaxaEntrega entidade) throws ServiceEdgleChurrascariaException {
		// TODO Auto-generated method stub

	}

}
