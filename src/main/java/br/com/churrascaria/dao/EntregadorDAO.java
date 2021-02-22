package br.com.churrascaria.dao;

import java.util.List;

import br.com.churrascaria.entities.Entregador;
import br.com.churrascaria.filter.EntregadorFilter;

public interface EntregadorDAO extends EntidadeDAO<Entregador> {

	public List<Entregador> findBy(EntregadorFilter filter) throws PersistenciaEdgleChurrascariaException;

}
