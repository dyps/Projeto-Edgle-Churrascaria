package br.com.churrascaria.dao;

import java.util.List;

import br.com.churrascaria.entities.CategoriaProduto;
import br.com.churrascaria.filter.CategoriaProdutoFilter;

public interface CategoriaProdutoDAO extends EntidadeDAO<CategoriaProduto> {

	public List<CategoriaProduto> findBy(CategoriaProdutoFilter filter) throws PersistenciaEdgleChurrascariaException;

}
