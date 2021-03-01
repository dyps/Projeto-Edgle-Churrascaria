package br.com.churrascaria.dao;

import br.com.churrascaria.entities.Produto;

public interface ProdutoDAO extends EntidadeDAO<Produto> {

	public Produto getByID(Long id) throws PersistenciaEdgleChurrascariaException;

}
