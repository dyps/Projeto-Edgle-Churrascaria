package br.com.churrascaria.dao;

import br.com.churrascaria.entities.ItemDeConfiguracao;
import br.com.churrascaria.entities.Opcao;
import br.com.churrascaria.entities.Produto;

public interface ProdutoDAO extends EntidadeDAO<Produto> {

	public Produto getByID(Long id) throws PersistenciaEdgleChurrascariaException;

	public void delete(Opcao entidade) throws PersistenciaEdgleChurrascariaException;

	public void delete(ItemDeConfiguracao entidade) throws PersistenciaEdgleChurrascariaException;

	public ItemDeConfiguracao getItemByID(Long id) throws PersistenciaEdgleChurrascariaException;

}
