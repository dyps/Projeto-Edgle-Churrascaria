package br.com.churrascaria.services.implementacao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.dao.EntidadeDAO;
import br.com.churrascaria.dao.FuncionarioDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.Funcionario;
import br.com.churrascaria.filter.FuncionarioFilter;
import br.com.churrascaria.services.CRUDService;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.util.TransacionalCdi;

@Named
@RequestScoped
public class FuncionarioServiceImplementacao extends CRUDService<Funcionario> {

	@Inject
	private FuncionarioDAO funcionarioDAO;

	@TransacionalCdi
	public void save(Funcionario funcionario) throws ServiceEdgleChurrascariaException {
		try {
			// Verificar se login j� existe
			validarLogin(funcionario);
			validar(funcionario);
//			calcularHashDaSenha(funcionario);
			funcionarioDAO.save(funcionario);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}

	}

	@TransacionalCdi
	public Funcionario update(Funcionario funcionario) throws ServiceEdgleChurrascariaException {
		try {
			// Verificar se login já existe
			validarLogin(funcionario);
			validar(funcionario);
//			if (passwordChanged) {
//				calcularHashDaSenha(funcionario);
//			}
			return funcionarioDAO.update(funcionario);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	public Funcionario getByID(Long userId) throws ServiceEdgleChurrascariaException {
		FuncionarioFilter filter = new FuncionarioFilter();
		filter.setId(userId);
		return findBy(filter).get(0);
	}

	public List<Funcionario> findBy(FuncionarioFilter filter) throws ServiceEdgleChurrascariaException {
		try {
			filter.validate();
			List<Funcionario> list = funcionarioDAO.findBy(filter);
			return list;
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

//	private String calcularHashDaSenha(Funcionario funcionario) throws ServiceEdgleChurrascariaException {
//		return null;
//		funcionario.setPassword(hash(funcionario.getPassword()));
//		return funcionario.getPassword();
//	}

	public boolean senhaConfere(Funcionario funcionario, String supostaSenha) throws ServiceEdgleChurrascariaException {
		return false;
//		// Recuperar verdadeira senha atual (hash)
//		String senhaHash = null;
//		try {
//			senhaHash = funcionarioDAO.getByID(funcionario.getId()).getPassword();
//		} catch (PersistenciaEdgleChurrascariaException e) {
//			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
//		}
//
//		// Programação defensiva contra NPE
//		if (senhaHash == null && supostaSenha == null) {
//			return true;
//		}
//
//		if (senhaHash == null || supostaSenha == null) {
//			return false;
//		}
//
//		// Comparar hash da suposta senha com o verdadeiro hash da senha
//		String supostaSenhaHash = hash(supostaSenha);
//
//		return senhaHash.equals(supostaSenhaHash);
	}

//	private String hash(String password) throws ServiceEdgleChurrascariaException {
//		MessageDigest md;
//		try {
//			md = MessageDigest.getInstance("SHA-256");
//			md.update(password.getBytes("UTF-8"));
//			byte[] digest = md.digest();
//			String output = Base64.getEncoder().encodeToString(digest);
//			return output;
//		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
//			throw new ServiceEdgleChurrascariaException("Could not calculate hash!", e);
//		}
//	}
//	
	private void validarLogin(Funcionario funcionario) throws ServiceEdgleChurrascariaException {
		boolean jahExiste = funcionarioDAO.existeUsuarioComLogin(funcionario);
		if (jahExiste) {
			throw new ServiceEdgleChurrascariaException("Login já existente: " + funcionario.getLogin());
		}
	}

	@Override
	protected EntidadeDAO<Funcionario> getEntidadeDAO() {
		return funcionarioDAO;
	}

	@Override
	protected void validar(Funcionario entidade) throws ServiceEdgleChurrascariaException {
		if (entidade == null || entidade.getNome() == null || entidade.getNome().trim().equals("")) {
			throw new ServiceEdgleChurrascariaException("O nome do funcionario é necessário");
		}
		if (entidade == null || entidade.getLogin() == null || entidade.getLogin().trim().equals("")) {
			throw new ServiceEdgleChurrascariaException("O login do funcionario é necessário");
		}
		if (entidade == null || entidade.getSenha() == null || entidade.getSenha().trim().equals("")) {
			throw new ServiceEdgleChurrascariaException("A senha do funcionario é necessário");
		}
		if (entidade == null || entidade.getTipoDeFuncionario() == null) {
			throw new ServiceEdgleChurrascariaException("O tipo do funcionario é necessário");
		}
		List<Funcionario> list = getAll();
		for (Funcionario funcionario : list) {
			if (funcionario.getLogin().equals(entidade.getLogin())) {
				if (funcionario.getId().equals(entidade.getId()))
					return;
				else
					throw new ServiceEdgleChurrascariaException("O login do funcionario não pode ser repetido");
			}
		}
	}

	public void setFuncionarioDAO(FuncionarioDAO funcionarioDAO) {
		this.funcionarioDAO = funcionarioDAO;
	}

}
