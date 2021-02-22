package br.com.churrascaria.dao;

import java.util.List;

import br.com.churrascaria.entities.TaxaEntrega;
import br.com.churrascaria.filter.TaxaEntregaFilter;

public interface TaxaEntregaDAO extends EntidadeDAO<TaxaEntrega> {
	
	public List<TaxaEntrega> findBy(TaxaEntregaFilter filter) throws PersistenciaEdgleChurrascariaException;

}
