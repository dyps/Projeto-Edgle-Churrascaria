package br.com.churrascaria.dao;

import br.com.churrascaria.entities.ObservacaoPadrao;

public interface ObservacoesPadraoDAO extends EntidadeDAO<ObservacaoPadrao> {

	public ObservacaoPadrao getByID(Long id) throws PersistenciaEdgleChurrascariaException;

}
