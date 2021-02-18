package br.com.churrascaria.services;

import java.util.List;


public interface CRUDService<Entidade> {
	public void save(Entidade entidade) throws ServiceEdgleChurrascariaException;

	public Entidade update(Entidade entidade) throws ServiceEdgleChurrascariaException;

	public void delete(Entidade entidade) throws ServiceEdgleChurrascariaException;

	public Entidade getByID(Long Id) throws ServiceEdgleChurrascariaException;

	public List<Entidade> getAll() throws ServiceEdgleChurrascariaException;
}
