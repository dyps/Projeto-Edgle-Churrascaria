package br.com.churrascaria.services;

import java.util.List;


public interface CRUDService<Entidade, Filtro> {
	public void save(Entidade entidade) throws ServiceEdgleChurrascariaException;

	public Entidade update(long id, Entidade entidade) throws ServiceEdgleChurrascariaException;

	public void delete(Entidade funcionario) throws ServiceEdgleChurrascariaException;

	public Entidade getByID(long userId) throws ServiceEdgleChurrascariaException;

	public List<Entidade> findBy(Filtro filtro) throws ServiceEdgleChurrascariaException;

	public List<Entidade> getAll() throws ServiceEdgleChurrascariaException;
}
