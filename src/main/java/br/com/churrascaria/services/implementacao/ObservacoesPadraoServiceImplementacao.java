package br.com.churrascaria.services.implementacao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.dao.EntidadeDAO;
import br.com.churrascaria.dao.ObservacoesPadraoDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.ObservacaoPadrao;
import br.com.churrascaria.services.CRUDService;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;

@Named
@RequestScoped
public class ObservacoesPadraoServiceImplementacao extends CRUDService<ObservacaoPadrao> {

	@Inject
	private ObservacoesPadraoDAO observacoesPadraoDAO;




	@Override
	public List<ObservacaoPadrao> getAll() throws ServiceEdgleChurrascariaException {
		try {
			return observacoesPadraoDAO.getAll();
		} catch (PersistenciaEdgleChurrascariaException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ObservacaoPadrao getByID(Long Id) throws ServiceEdgleChurrascariaException {
		try {
			return observacoesPadraoDAO.getByID(Id);
		} catch (PersistenciaEdgleChurrascariaException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean aProdutosUsando(ObservacaoPadrao obs) {
		// buscar se a observacao esta sendo usada por algum produto
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected EntidadeDAO<ObservacaoPadrao> getEntidadeDAO() {
		return observacoesPadraoDAO;
	}

}