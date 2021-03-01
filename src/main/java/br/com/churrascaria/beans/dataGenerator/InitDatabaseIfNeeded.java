package br.com.churrascaria.beans.dataGenerator;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.Eager;

import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.dataGenerator.FuncionarioDataGeneratorService;

//import org.omnifaces.cdi.Eager;

@Named
@Eager // Thanks, Omnifaces!!!
@ApplicationScoped
public class InitDatabaseIfNeeded {

	@Inject
	private FuncionarioDataGeneratorService funcionarioDataGeneratorService;

	@PostConstruct
	public void postConstruct() {
		try {
			funcionarioDataGeneratorService.generateData();

		} catch (ServiceEdgleChurrascariaException e) {
//			throw new EdgleChurrascariaRuntimeException("Ocorreu algum erro ao tentar inicializar a base de dados.", e);
		}
	}

}