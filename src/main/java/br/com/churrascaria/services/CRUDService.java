package br.com.churrascaria.services;

import java.util.List;

import br.com.churrascaria.dao.EntidadeDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.util.TransacionalCdi;

public abstract class CRUDService<Entidade> {
	
	protected abstract void validar(Entidade entidade) throws ServiceEdgleChurrascariaException;

	@TransacionalCdi
	public void save(Entidade entidade) throws ServiceEdgleChurrascariaException {
		try {
			validar(entidade);
			getEntidadeDAO().save(entidade);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}
	@TransacionalCdi
	public Entidade update(Entidade entidade) throws ServiceEdgleChurrascariaException {
		try {
			validar(entidade);
			return getEntidadeDAO().update(entidade);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	@TransacionalCdi
	public void delete(Entidade entidade) throws ServiceEdgleChurrascariaException {
		try {
			if (!podeSerApagada(entidade)) {
				throw new ServiceEdgleChurrascariaException("Não pode ser apagado");
			}
			getEntidadeDAO().delete(entidade);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	public boolean podeSerApagada(Entidade entidade) {
		return true;
	}

	public List<Entidade> getAll() throws ServiceEdgleChurrascariaException {
		try {
			return getEntidadeDAO().getAll();
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	public abstract Entidade getByID(Long id) throws ServiceEdgleChurrascariaException;
	protected abstract EntidadeDAO<Entidade> getEntidadeDAO();
}
