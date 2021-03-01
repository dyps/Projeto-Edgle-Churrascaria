package br.com.churrascaria.services.implementacao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.dao.EntidadeDAO;
import br.com.churrascaria.dao.EntregadorDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.dao.TaxaEntregaDAO;
import br.com.churrascaria.entities.Entregador;
import br.com.churrascaria.entities.TaxaEntrega;
import br.com.churrascaria.filter.EntregadorFilter;
import br.com.churrascaria.services.CRUDService;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.util.TransacionalCdi;

@Named
@RequestScoped
public class EntregadorServiceImplementacao extends CRUDService<Entregador> {

	@Inject
	private EntregadorDAO entregadorDAO;
	
	@Inject
	private TaxaEntregaDAO taxaEntregaDAO;

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

	@Override
	protected void validar(Entregador entidade) throws ServiceEdgleChurrascariaException {
		if (entidade == null || entidade.getNome() == null || entidade.getNome().trim().equals("")) {
			throw new ServiceEdgleChurrascariaException("O nome do entregador é necessário");
		}
		if (entidade == null || entidade.getTelefone() == null || entidade.getTelefone().trim().equals("")) {
			throw new ServiceEdgleChurrascariaException("O telefone do entregador é necessário");
		}
	}
	
	public void validar(TaxaEntrega entidade) throws ServiceEdgleChurrascariaException {
		// valor e distanciaMaxima
		if (entidade == null || entidade.getValor() == null) {
			throw new ServiceEdgleChurrascariaException("O valor da taxa de entrega é necessária");
		}
		if (entidade == null || entidade.getDistanciaMaxima() == null) {
			throw new ServiceEdgleChurrascariaException("A distância máxima da taxa de entrega é necessária");
		}
	}
	
	@TransacionalCdi
	public void saveTaxa(TaxaEntrega entidade) throws ServiceEdgleChurrascariaException {
		try {
			validar(entidade);
			this.taxaEntregaDAO.save(entidade);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}
	
	@TransacionalCdi
	public void deleteTaxa(TaxaEntrega entidade) throws ServiceEdgleChurrascariaException {
		try {
			this.taxaEntregaDAO.delete(entidade);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

}
