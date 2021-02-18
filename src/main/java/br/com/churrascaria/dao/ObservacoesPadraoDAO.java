package br.com.churrascaria.dao;

import java.util.List;

import br.com.churrascaria.entities.ObservacaoPadrao;

public interface ObservacoesPadraoDAO {
	public void save(ObservacaoPadrao funcionario) throws PersistenciaEdgleChurrascariaException;

	public List<ObservacaoPadrao> getAll() throws PersistenciaEdgleChurrascariaException;

	public ObservacaoPadrao update(ObservacaoPadrao funcionario) throws PersistenciaEdgleChurrascariaException;

	public void delete(ObservacaoPadrao funcionario) throws PersistenciaEdgleChurrascariaException;

	public ObservacaoPadrao getByID(Long id) throws PersistenciaEdgleChurrascariaException;

}
