package br.com.churrascaria.dao;

import br.com.churrascaria.entities.ObservacaoPadrao;

public interface ObservacaoPadraoDAO extends EntidadeDAO<ObservacaoPadrao> {

	public ObservacaoPadrao getByID(Long id) throws PersistenciaEdgleChurrascariaException;

}
