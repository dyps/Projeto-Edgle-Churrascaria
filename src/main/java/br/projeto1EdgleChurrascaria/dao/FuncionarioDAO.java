package br.projeto1EdgleChurrascaria.dao;

import br.projeto1EdgleChurrascaria.entities.Funcionario;

public interface FuncionarioDAO  {

	public void save(Funcionario funcionario) throws PersistenciaEdgleChurrascariaException;

}
