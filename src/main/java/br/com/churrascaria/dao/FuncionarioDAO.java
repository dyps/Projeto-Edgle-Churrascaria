package br.com.churrascaria.dao;

import br.com.churrascaria.entities.Funcionario;

public interface FuncionarioDAO  {

	public void save(Funcionario funcionario) throws PersistenciaEdgleChurrascariaException;

}
