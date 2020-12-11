package br.com.churrascaria.beans.dataGenerator;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.services.dataGenerator.FuncionarioDataGeneratorService;

//import org.omnifaces.cdi.Eager;


//@Eager // Thanks, Omnifaces!!!
@Named
@ApplicationScoped
public class InitDatabaseIfNeeded {

	@Inject
	private FuncionarioDataGeneratorService funcionarioDataGeneratorService;

	@PostConstruct
	public void postConstruct() {

		try {
			funcionarioDataGeneratorService.generateData();
		} catch (ServiceDacException e) {
			throw new DacRuntimeException("Ocorreu algum erro ao tentar inicializar a base de dados.", e);
		}
	}

}