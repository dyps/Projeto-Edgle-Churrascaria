package br.com.churrascaria.dao;

import java.util.List;

import br.com.churrascaria.entities.Funcionario;
import br.com.churrascaria.filter.FuncionarioFilter;

public interface FuncionarioDAO {

	public void save(Funcionario funcionario) throws PersistenciaEdgleChurrascariaException;

	public List<Funcionario> getAll() throws PersistenciaEdgleChurrascariaException;

	public Funcionario getByID(long userId) throws PersistenciaEdgleChurrascariaException;

	public List<Funcionario> findBy(FuncionarioFilter filter) throws PersistenciaEdgleChurrascariaException;

}
