package br.com.churrascaria.services.implementacao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.dao.FuncionarioDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.entities.Funcionario;
import br.com.churrascaria.filter.FuncionarioFilter;
import br.com.churrascaria.services.CRUDService;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;

@Named
@RequestScoped
public class FuncionarioServiceImplementacao implements CRUDService<Funcionario> {

	@Inject
	private FuncionarioDAO funcionarioDAO;

	public void save(Funcionario funcionario) throws ServiceEdgleChurrascariaException {
		try {
			// Verificar se login j� existe
			validarLogin(funcionario);
//			calcularHashDaSenha(funcionario);
			funcionarioDAO.save(funcionario);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}

	}

	public Funcionario update(Funcionario funcionario) throws ServiceEdgleChurrascariaException {
		try {
			// Verificar se login já existe
			validarLogin(funcionario);
//			if (passwordChanged) {
//				calcularHashDaSenha(funcionario);
//			}
			return funcionarioDAO.update(funcionario);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	public void delete(Funcionario funcionario) throws ServiceEdgleChurrascariaException {
		try {
			funcionarioDAO.delete(funcionario);
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
			return funcionarioDAO.findBy(filter);
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

	public List<Funcionario> getAll() throws ServiceEdgleChurrascariaException {
		try {
			return funcionarioDAO.getAll();
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
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
			throw new ServiceEdgleChurrascariaException("Login already exists: " + funcionario.getLogin()); 
		}
	}

}
