package br.com.churrascaria.dao;

import java.util.List;

import br.com.churrascaria.entities.Cliente;
import br.com.churrascaria.filter.ClienteFilter;

public interface ClienteDAO extends EntidadeDAO<Cliente> {
	
	public List<Cliente> findBy(ClienteFilter filter) throws PersistenciaEdgleChurrascariaException;

}
