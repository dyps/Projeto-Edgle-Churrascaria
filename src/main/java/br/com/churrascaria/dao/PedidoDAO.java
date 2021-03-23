package br.com.churrascaria.dao;

import br.com.churrascaria.entities.Pedido;

public interface PedidoDAO extends EntidadeDAO<Pedido> {

	public Pedido getByID(Long id) throws PersistenciaEdgleChurrascariaException;

}
