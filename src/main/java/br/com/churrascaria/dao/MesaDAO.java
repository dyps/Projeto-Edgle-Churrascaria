package br.com.churrascaria.dao;

import java.util.List;

import br.com.churrascaria.entities.Mesa;
import br.com.churrascaria.filter.MesaFilter;

public interface MesaDAO {
	
	public void save(Mesa mesa) throws PersistenciaEdgleChurrascariaException;

	public List<Mesa> getAll() throws PersistenciaEdgleChurrascariaException;

	public List<Mesa> findBy(MesaFilter filter) throws PersistenciaEdgleChurrascariaException;

	public Mesa update(Mesa mesa) throws PersistenciaEdgleChurrascariaException;

	public void delete(Mesa mesa) throws PersistenciaEdgleChurrascariaException;

}
