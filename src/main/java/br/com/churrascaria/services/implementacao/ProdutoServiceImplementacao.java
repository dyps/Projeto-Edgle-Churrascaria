package br.com.churrascaria.services.implementacao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.dao.EntidadeDAO;
import br.com.churrascaria.entities.Produto;
import br.com.churrascaria.services.CRUDService;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;

@Named
@RequestScoped
public class ProdutoServiceImplementacao extends CRUDService<Produto> {

	@Override
	protected void validar(Produto entidade) throws ServiceEdgleChurrascariaException {

	}

	@Override
	public Produto getByID(Long id) throws ServiceEdgleChurrascariaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Inject
	private EntidadeDAO<Produto> entidadeDAO;

	@Override
	protected EntidadeDAO<Produto> getEntidadeDAO() {
		return entidadeDAO;
	}

}
