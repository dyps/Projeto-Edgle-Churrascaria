package br.projeto1EdgleChurrascaria.services;

import java.util.List;

import br.projeto1EdgleChurrascaria.entities.Funcionario;
import br.projeto1EdgleChurrascaria.filter.FuncionarioFilter;

public interface FuncionarioService {
	public void save(Funcionario funcionario) throws ServiceEdgleChurrascariaException;

	public Funcionario update(Funcionario funcionario, boolean passwordChanged) throws ServiceEdgleChurrascariaException;

	public void delete(Funcionario funcionario) throws ServiceEdgleChurrascariaException;

	public Funcionario getByID(int userId) throws ServiceEdgleChurrascariaException;

	public List<Funcionario> getAll() throws ServiceEdgleChurrascariaException;

	public List<Funcionario> findBy(FuncionarioFilter filter) throws ServiceEdgleChurrascariaException;

	public boolean senhaConfere(Funcionario funcionario, String supostaSenha) throws ServiceEdgleChurrascariaException;

}
