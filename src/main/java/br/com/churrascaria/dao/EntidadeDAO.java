package br.com.churrascaria.dao;

import java.util.List;


public interface EntidadeDAO<Entidade> {
	public void save(Entidade entidade) throws PersistenciaEdgleChurrascariaException;

	public List<Entidade> getAll() throws PersistenciaEdgleChurrascariaException;

	public Entidade update(Entidade entidade) throws PersistenciaEdgleChurrascariaException;

	public void delete(Entidade entidade) throws PersistenciaEdgleChurrascariaException;


}
