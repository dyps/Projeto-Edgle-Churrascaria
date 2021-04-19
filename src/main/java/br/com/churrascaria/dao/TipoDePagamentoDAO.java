package br.com.churrascaria.dao;

import br.com.churrascaria.entities.TipoDePagamento;

public interface TipoDePagamentoDAO extends EntidadeDAO<TipoDePagamento>{
	
	public TipoDePagamento getByID(Long id) throws PersistenciaEdgleChurrascariaException;

}
