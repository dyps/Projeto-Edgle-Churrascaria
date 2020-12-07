package br.com.churrascaria.filter;

import java.io.Serializable;

import br.com.churrascaria.services.ServiceEdgleChurrascariaException;

public interface Filter extends Serializable {
	
	default public void validate() throws ServiceEdgleChurrascariaException {
		// Não fazer validação alguma por padrão.
	}

}
