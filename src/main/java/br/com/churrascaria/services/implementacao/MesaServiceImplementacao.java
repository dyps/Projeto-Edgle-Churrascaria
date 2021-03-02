package br.com.churrascaria.services.implementacao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.dao.EntidadeDAO;
import br.com.churrascaria.dao.MesaDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.Mesa;
import br.com.churrascaria.filter.MesaFilter;
import br.com.churrascaria.services.CRUDService;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;

@Named
@RequestScoped
public class MesaServiceImplementacao extends CRUDService<Mesa> {

	@Inject
	private MesaDAO mesaDAO;




	public Mesa getByID(Long userId) throws ServiceEdgleChurrascariaException {
		MesaFilter filter = new MesaFilter();
		filter.setId(userId);
		return findBy(filter).get(0);
	}

	public List<Mesa> findBy(MesaFilter filter) throws ServiceEdgleChurrascariaException {
		try {
			filter.validate();
			List<Mesa> list = mesaDAO.findBy(filter);
			return list;
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}
	protected EntidadeDAO<Mesa> getEntidadeDAO() {
		return mesaDAO;
	}

	@Override
	protected void validar(Mesa entidade) throws ServiceEdgleChurrascariaException {
		if (entidade == null || entidade.getNumero() == null ) {
			throw new ServiceEdgleChurrascariaException("O número da mesa é necessário");
		}
		List<Mesa> list = getAll();
        for (Mesa mesa : list) {
            if(mesa.getNumero().equals(entidade.getNumero()))
                throw new ServiceEdgleChurrascariaException("O número da mesa não pode ser repetida");
        }
	}


}
