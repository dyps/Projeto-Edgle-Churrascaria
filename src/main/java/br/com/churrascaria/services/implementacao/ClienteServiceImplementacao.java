package br.com.churrascaria.services.implementacao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.dao.ClienteDAO;
import br.com.churrascaria.dao.EnderecoDAO;
import br.com.churrascaria.dao.EntidadeDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.Cliente;
import br.com.churrascaria.entities.Endereco;
import br.com.churrascaria.filter.ClienteFilter;
import br.com.churrascaria.services.CRUDService;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.util.TransacionalCdi;

@Named
@RequestScoped
public class ClienteServiceImplementacao extends CRUDService<Cliente> {

	@Inject
	private ClienteDAO clienteDAO;

	@Inject
	private EnderecoDAO enderecoDAO;

	@Override
	protected void validar(Cliente entidade) throws ServiceEdgleChurrascariaException {
		if (entidade == null || entidade.getNome() == null || entidade.getNome().trim().equals("")) {
			throw new ServiceEdgleChurrascariaException("O nome do cliente é necessário");
		}
		List<Cliente> list = getAll();
		for (Cliente cliente : list) {
			if (cliente.getTelefone().equals(entidade.getTelefone()))
				throw new ServiceEdgleChurrascariaException("O telefone do cliente não pode ser repetido");
		}
	}

	public void validarEndereco(Endereco entidade) throws ServiceEdgleChurrascariaException {
		if (entidade == null || entidade.getNome() == null || entidade.getNome().trim().equals("")) {
			throw new ServiceEdgleChurrascariaException("Um nome para o endereço é necessário");
		}
		if (entidade == null || entidade.getLogradouro() == null || entidade.getLogradouro().trim().equals("")) {
			throw new ServiceEdgleChurrascariaException("Um logradouro para o endereço é necessário");
		}
		if (entidade == null || entidade.getNumero() == null) {
			throw new ServiceEdgleChurrascariaException("O número do endereço é necessária");
		}

	}

	@Override
	public Cliente getByID(Long id) throws ServiceEdgleChurrascariaException {
		ClienteFilter filter = new ClienteFilter();
		filter.setId(id);
		return findBy(filter).get(0);
	}

	public List<Cliente> findBy(ClienteFilter filter) throws ServiceEdgleChurrascariaException {
		try {
			filter.validate();
			List<Cliente> list = clienteDAO.findBy(filter);
			return list;
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	@Override
	protected EntidadeDAO<Cliente> getEntidadeDAO() {
		return clienteDAO;
	}

	@TransacionalCdi
	public void saveEndereco(Endereco entidade) throws ServiceEdgleChurrascariaException {
		try {
			validarEndereco(entidade);
			this.enderecoDAO.save(entidade);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	@TransacionalCdi
	public void deleteEndereco(Endereco entidade) throws ServiceEdgleChurrascariaException {
		try {
			this.enderecoDAO.delete(entidade);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

}
