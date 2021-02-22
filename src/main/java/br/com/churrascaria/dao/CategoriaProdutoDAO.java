package br.com.churrascaria.dao;

import java.util.List;

import br.com.churrascaria.entities.CategoriaProduto;
import br.com.churrascaria.filter.CategoriaProdutoFilter;

public interface CategoriaProdutoDAO {
	
	public void save(CategoriaProduto categoriaProduto) throws PersistenciaEdgleChurrascariaException;

	public List<CategoriaProduto> getAll() throws PersistenciaEdgleChurrascariaException;

	public List<CategoriaProduto> findBy(CategoriaProdutoFilter filter) throws PersistenciaEdgleChurrascariaException;

	public CategoriaProduto update(CategoriaProduto categoriaProduto) throws PersistenciaEdgleChurrascariaException;

	public void delete(CategoriaProduto categoriaProduto) throws PersistenciaEdgleChurrascariaException;

}
