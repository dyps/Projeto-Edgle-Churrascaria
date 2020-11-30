package br.projeto1EdgleChurrascaria.services.implementacao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import br.projeto1EdgleChurrascaria.dao.FuncionarioDAO;
import br.projeto1EdgleChurrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.projeto1EdgleChurrascaria.dao.implementacao.FuncionarioDAOImplementacao;
import br.projeto1EdgleChurrascaria.entities.Funcionario;
import br.projeto1EdgleChurrascaria.filter.FuncionarioFilter;
import br.projeto1EdgleChurrascaria.services.FuncionarioService;
import br.projeto1EdgleChurrascaria.services.ServiceEdgleChurrascariaException;

public class FuncionarioServiceImplementacao implements FuncionarioService {

	private FuncionarioDAO funcionarioDAO = new FuncionarioDAOImplementacao();

	public void save(Funcionario funcionario) throws ServiceEdgleChurrascariaException {
		try {
			// Verificar se login j� existe
//			validarLogin(funcionario);
//			calcularHashDaSenha(funcionario);
			funcionarioDAO.save(funcionario);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}

	}

	@Override
	public Funcionario update(Funcionario funcionario, boolean passwordChanged) throws ServiceEdgleChurrascariaException {
		return funcionario;
//		try {
//			// Verificar se login já existe
//			validarLogin(funcionario);
//			funcionario.limparCamposEspecificos();
//			if (passwordChanged) {
//				calcularHashDaSenha(funcionario);
//			}
//			return funcionarioDAO.update(funcionario);
//		} catch (PersistenciaEdgleChurrascariaException e) {
//			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
//		}
	}

	@Override
	public void delete(Funcionario funcionario) throws ServiceEdgleChurrascariaException {
//		try {
//			funcionarioDAO.delete(funcionario);
//		} catch (PersistenciaEdgleChurrascariaException e) {
//			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
//		}
	}

	@Override
	public Funcionario getByID(int userId) throws ServiceEdgleChurrascariaException {
		return null;
//		try {
//			return funcionarioDAO.getByID(userId);
//		} catch (PersistenciaEdgleChurrascariaException e) {
//			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
//		}
	}

	@Override
	public List<Funcionario> getAll() throws ServiceEdgleChurrascariaException {
		return null;
//		try {
//			return funcionarioDAO.getAll();
//		} catch (PersistenciaEdgleChurrascariaException e) {
//			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
//		}
	}

	@Override
	public List<Funcionario> findBy(FuncionarioFilter filter) throws ServiceEdgleChurrascariaException {
		return null;
//		try {
//			filter.validate();
//			return funcionarioDAO.findBy(filter);
//		} catch (PersistenciaEdgleChurrascariaException e) {
//			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
//		}
	}

	private String calcularHashDaSenha(Funcionario funcionario) throws ServiceEdgleChurrascariaException {
		return null;
//		funcionario.setPassword(hash(funcionario.getPassword()));
//		return funcionario.getPassword();
	}

	@Override
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
//	private void validarLogin(Funcionario funcionario) throws ServiceEdgleChurrascariaException {
//		boolean jahExiste = funcionarioDAO.existeUsuarioComLogin(funcionario);
//		if (jahExiste) {
//			throw new ServiceEdgleChurrascariaException("Login already exists: " + funcionario.getLogin()); 
//		}
//	}

}
