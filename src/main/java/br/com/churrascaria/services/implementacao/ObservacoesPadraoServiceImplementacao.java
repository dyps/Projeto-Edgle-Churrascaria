package br.com.churrascaria.services.implementacao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.dao.EntidadeDAO;
import br.com.churrascaria.dao.ObservacaoPadraoDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.ObservacaoPadrao;
import br.com.churrascaria.services.CRUDService;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;

@Named
@RequestScoped
public class ObservacoesPadraoServiceImplementacao extends CRUDService<ObservacaoPadrao> {

	@Inject
	private ObservacaoPadraoDAO observacoesPadraoDAO;

	@Override
	public List<ObservacaoPadrao> getAll() throws ServiceEdgleChurrascariaException {
		try {
			return observacoesPadraoDAO.getAll();
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	public ObservacaoPadrao getByID(Long Id) throws ServiceEdgleChurrascariaException {
		try {
			return observacoesPadraoDAO.getByID(Id);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	public boolean aProdutosUsando(ObservacaoPadrao obs) throws ServiceEdgleChurrascariaException {
		try {
			return getByID(obs.getId()).getProdutos().size() > 0;
		} catch (ServiceEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	@Override
	protected EntidadeDAO<ObservacaoPadrao> getEntidadeDAO() {
		return observacoesPadraoDAO;
	}

	@Override
	protected void validar(ObservacaoPadrao entidade) throws ServiceEdgleChurrascariaException {

		if (entidade == null || entidade.getDescricao() == null || entidade.getDescricao().trim().equals("")) {
			throw new ServiceEdgleChurrascariaException("A descrição da observação é necessário");
		}
//		if (entidade.getDescricao().length() <= 3) {
//			throw new ServiceEdgleChurrascariaException("A descrição da observação esta pequena");
//		}
		List<ObservacaoPadrao> list = getAll();
		for (ObservacaoPadrao observacaoPadrao : list) {
			if (observacaoPadrao.getDescricao().toLowerCase().equals(entidade.getDescricao().toLowerCase()))
				throw new ServiceEdgleChurrascariaException("A descrição da observação não pode ser repetida");
		}

	}

	public void setObservacoesPadraoDAO(ObservacaoPadraoDAO observacoesPadraoDAO) {
		this.observacoesPadraoDAO = observacoesPadraoDAO;
	}

}