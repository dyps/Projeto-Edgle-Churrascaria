package br.com.churrascaria.services.implementacao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.dao.EnderecoDAO;
import br.com.churrascaria.dao.EntidadeDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.Endereco;
import br.com.churrascaria.filter.EnderecoFilter;
import br.com.churrascaria.services.CRUDService;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;

@Named
@RequestScoped
public class EnderecoServiceImplementacao extends CRUDService<Endereco> {
	
	@Inject
	private EnderecoDAO enderecoDAO;

	@Override
	protected void validar(Endereco entidade) throws ServiceEdgleChurrascariaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Endereco getByID(Long id) throws ServiceEdgleChurrascariaException {
		EnderecoFilter filter = new EnderecoFilter();
		filter.setId(id);
		return findBy(filter).get(0);
	}
	
	public List<Endereco> findBy(EnderecoFilter filter) throws ServiceEdgleChurrascariaException {
		try {
			filter.validate();
			return enderecoDAO.findBy(filter);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	@Override
	protected EntidadeDAO<Endereco> getEntidadeDAO() {
		return enderecoDAO;
	}

}
