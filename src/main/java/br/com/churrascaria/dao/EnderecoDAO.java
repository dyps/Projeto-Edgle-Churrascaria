package br.com.churrascaria.dao;

import java.util.List;

import br.com.churrascaria.entities.Endereco;
import br.com.churrascaria.filter.EnderecoFilter;

public interface EnderecoDAO extends EntidadeDAO<Endereco> {
	
	public List<Endereco> findBy(EnderecoFilter filter) throws PersistenciaEdgleChurrascariaException;

}
