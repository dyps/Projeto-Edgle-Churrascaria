package br.com.churrascaria.app;


import com.github.javafaker.Faker;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.FuncionarioServiceImplementacao;

public class TestCrudFuncionario {
	final Faker faker = new Faker();

	public static void main(String[] args) {
		FuncionarioServiceImplementacao funcionarioService = new FuncionarioServiceImplementacao();
		try {
			Object a = funcionarioService.getByID(1);
			System.out.println(a);
		} catch (ServiceEdgleChurrascariaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
