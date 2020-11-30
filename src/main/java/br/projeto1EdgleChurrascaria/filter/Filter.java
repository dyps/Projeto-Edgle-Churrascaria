package br.projeto1EdgleChurrascaria.filter;

import java.io.Serializable;

import br.projeto1EdgleChurrascaria.services.ServiceEdgleChurrascariaException;

public interface Filter extends Serializable {
	
	default public void validate() throws ServiceEdgleChurrascariaException {
		// Não fazer validação alguma por padrão.
	}

}
