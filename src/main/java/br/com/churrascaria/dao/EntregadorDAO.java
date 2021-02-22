package br.com.churrascaria.dao;

import java.util.List;

import br.com.churrascaria.entities.Entregador;
import br.com.churrascaria.filter.EntregadorFilter;

public interface EntregadorDAO {
	
	public void save(Entregador entregador) throws PersistenciaEdgleChurrascariaException;

	public List<Entregador> getAll() throws PersistenciaEdgleChurrascariaException;

	public List<Entregador> findBy(EntregadorFilter filter) throws PersistenciaEdgleChurrascariaException;

	public Entregador update(Entregador entregador) throws PersistenciaEdgleChurrascariaException;

	public void delete(Entregador entregador) throws PersistenciaEdgleChurrascariaException;

}
