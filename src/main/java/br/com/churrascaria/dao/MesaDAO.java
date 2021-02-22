package br.com.churrascaria.dao;

import java.util.List;

import br.com.churrascaria.entities.Mesa;
import br.com.churrascaria.filter.MesaFilter;

public interface MesaDAO extends EntidadeDAO<Mesa> {

	public List<Mesa> findBy(MesaFilter filter) throws PersistenciaEdgleChurrascariaException;

}
