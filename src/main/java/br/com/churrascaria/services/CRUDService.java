package br.com.churrascaria.services;

import java.util.List;

import br.com.churrascaria.dao.EntidadeDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;

public abstract class CRUDService<Entidade> {

	public void save(Entidade entidade) throws ServiceEdgleChurrascariaException {
		try {
			getEntidadeDAO().save(entidade);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	public Entidade update(Entidade entidade) throws ServiceEdgleChurrascariaException {
		try {
			return getEntidadeDAO().update(entidade);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	public void delete(Entidade entidade) throws ServiceEdgleChurrascariaException {
		try {
			getEntidadeDAO().delete(entidade);
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	public List<Entidade> getAll() throws ServiceEdgleChurrascariaException {
		try {
			return getEntidadeDAO().getAll();
		} catch (PersistenciaEdgleChurrascariaException e) {
			throw new ServiceEdgleChurrascariaException(e.getMessage(), e);
		}
	}

	protected abstract EntidadeDAO<Entidade> getEntidadeDAO();
}
