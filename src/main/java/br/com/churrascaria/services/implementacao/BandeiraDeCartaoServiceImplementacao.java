package br.com.churrascaria.services.implementacao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.dao.BandeiraDeCartaoDAO;
import br.com.churrascaria.dao.EntidadeDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.BandeiraDeCartao;
import br.com.churrascaria.services.CRUDService;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;

@Named
@RequestScoped
public class BandeiraDeCartaoServiceImplementacao extends CRUDService<BandeiraDeCartao> {

	@Inject
	private BandeiraDeCartaoDAO entidadeDAO;

	@Override
	protected void validar(BandeiraDeCartao entidade) throws ServiceEdgleChurrascariaException {
		if (entidade.getNome() == null || entidade.getNome().trim().isEmpty()) {
			throw new ServiceEdgleChurrascariaException("Nome não pode ser vazio");
		}
		
		List<BandeiraDeCartao> all = getAll();
		for (BandeiraDeCartao bandeiraDeCartao : all) {
			if (bandeiraDeCartao.equals(entidade)) {
				throw new ServiceEdgleChurrascariaException("A bandeira de cartão já existe");
			}
		}
	}

	@Override
	public BandeiraDeCartao getByID(Long id) throws ServiceEdgleChurrascariaException {
		try {
			return entidadeDAO.getByID(id);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	@Override
	protected EntidadeDAO<BandeiraDeCartao> getEntidadeDAO() {
		return entidadeDAO;
	}

}
