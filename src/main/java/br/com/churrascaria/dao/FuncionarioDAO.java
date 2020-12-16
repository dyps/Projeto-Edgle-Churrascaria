package br.com.churrascaria.dao;

import java.util.List;

import br.com.churrascaria.entities.Funcionario;
import br.com.churrascaria.filter.FuncionarioFilter;

public interface FuncionarioDAO {

	public void save(Funcionario funcionario) throws PersistenciaEdgleChurrascariaException;

	public List<Funcionario> getAll() throws PersistenciaEdgleChurrascariaException;

	public List<Funcionario> findBy(FuncionarioFilter filter) throws PersistenciaEdgleChurrascariaException;

	public boolean existeUsuarioComLogin(Funcionario funcionario) ;

	public Funcionario update(Funcionario funcionario) throws PersistenciaEdgleChurrascariaException;

	public void delete(Funcionario funcionario) throws PersistenciaEdgleChurrascariaException;

}
