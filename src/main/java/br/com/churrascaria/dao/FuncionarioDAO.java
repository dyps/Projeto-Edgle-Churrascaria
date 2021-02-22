package br.com.churrascaria.dao;

import java.util.List;

import br.com.churrascaria.entities.Funcionario;
import br.com.churrascaria.filter.FuncionarioFilter;

public interface FuncionarioDAO extends EntidadeDAO<Funcionario> {

	public List<Funcionario> findBy(FuncionarioFilter filter) throws PersistenciaEdgleChurrascariaException;

	public boolean existeUsuarioComLogin(Funcionario funcionario);

}
