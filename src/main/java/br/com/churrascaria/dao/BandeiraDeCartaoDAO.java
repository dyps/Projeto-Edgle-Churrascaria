package br.com.churrascaria.dao;

import br.com.churrascaria.entities.BandeiraDeCartao;

public interface BandeiraDeCartaoDAO extends EntidadeDAO<BandeiraDeCartao>{
	
	public BandeiraDeCartao getByID(Long id) throws PersistenciaEdgleChurrascariaException;

}
