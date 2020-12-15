package br.com.churrascaria.services.dataGenerator;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.FuncionarioServiceImplementacao;

public class FuncionarioDataGeneratorServiceImpl implements Serializable,FuncionarioDataGeneratorService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private FuncionarioServiceImplementacao funcionarioService;

	@Override
	public void generateData() throws ServiceEdgleChurrascariaException {
		funcionarioService.getByID(0);
		
	}

}
