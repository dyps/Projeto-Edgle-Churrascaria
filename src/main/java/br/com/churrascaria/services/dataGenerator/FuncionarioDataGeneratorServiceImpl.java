package br.com.churrascaria.services.dataGenerator;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;

import br.com.churrascaria.entities.Funcionario;
import br.com.churrascaria.entities.TipoDeFuncionario;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.FuncionarioServiceImplementacao;

public class FuncionarioDataGeneratorServiceImpl implements Serializable, FuncionarioDataGeneratorService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private FuncionarioServiceImplementacao funcionarioService;

	@Override
	public void generateData() throws ServiceEdgleChurrascariaException {
		List<Funcionario> funcionarios = funcionarioService.getAll();

		if (!funcionarios.isEmpty()) {
			return;
		}
		try {
			Funcionario func = new Funcionario();
			func.setLogin("yaggo");
			func.setNome("yaggo");
			func.setSenha("123");
			func.setTipoDeFuncionario(TipoDeFuncionario.GERENTE);
			func.setAtivo(true);
			funcionarioService.save(func);
		} catch (Exception e) {
			// TODO: handle exception
		}
		for (int i = 0; i < 3; i++) {
			try {
				Funcionario funcionario = novoFunc();
				funcionarioService.save(funcionario);
			} catch (ServiceEdgleChurrascariaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private static Funcionario novoFunc() {

		Name nome = Faker.instance().name();
		String senha = Faker.instance().number().digits(10);
		Funcionario funcionario = new Funcionario();
		String nomefunc = nome.firstName();
		funcionario.setNome(nomefunc);
		funcionario.setLogin(nomefunc);
		funcionario.setSenha(senha);
		funcionario.setAtivo(Faker.instance().bool().bool());
		funcionario.setTipoDeFuncionario(TipoDeFuncionario.GERENTE);
		return funcionario;

	}

}
