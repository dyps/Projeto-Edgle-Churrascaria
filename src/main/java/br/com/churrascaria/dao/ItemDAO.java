package br.com.churrascaria.dao;

import br.com.churrascaria.entities.AcaoRealizada;
import br.com.churrascaria.entities.Item;

public interface ItemDAO extends EntidadeDAO<Item> {

	public Item getByID(Long id) throws PersistenciaEdgleChurrascariaException;

	public void novaAcao(AcaoRealizada acaoRealizada) throws PersistenciaEdgleChurrascariaException;

}
