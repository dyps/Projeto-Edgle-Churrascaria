package br.com.churrascaria.services.implementacao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.dao.EntidadeDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.dao.TipoDePagamentoDAO;
import br.com.churrascaria.entities.TipoDePagamento;
import br.com.churrascaria.services.CRUDService;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;

@Named
@RequestScoped
public class TipoDePagamentoServiceImplementacao extends CRUDService<TipoDePagamento> {

	@Inject
	private TipoDePagamentoDAO entidadeDAO;

	@Override
	protected void validar(TipoDePagamento entidade) throws ServiceEdgleChurrascariaException {
		if (entidade.getNome() == null || entidade.getNome().trim().isEmpty()) {
			throw new ServiceEdgleChurrascariaException("Nome não pode ser vazio");
		}
		
		List<TipoDePagamento> all = getAll();
		for (TipoDePagamento tipoDePagamento : all) {
			if (tipoDePagamento.equals(entidade)) {
				throw new ServiceEdgleChurrascariaException("O tipo de pagamento já existe");
			}
		}
	}

	@Override
	public TipoDePagamento getByID(Long id) throws ServiceEdgleChurrascariaException {
		try {
			return entidadeDAO.getByID(id);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	@Override
	protected EntidadeDAO<TipoDePagamento> getEntidadeDAO() {
		return entidadeDAO;
	}

}
