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

//	public static void main(String[] args) {
//
//		int cont = 1;
//		CRUDService funcionarioService = new FuncionarioServiceImplementacao();
//		for (int i = 0; i < 10; i++) {
//			try {
//				Funcionario funcionario = novoFunc();
//				funcionarioService.save(funcionario);
//
//				System.out.println("ok " + cont++);
//			} catch (ServiceEdgleChurrascariaException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}

//	private static Funcionario novoFunc() {
//
//		Name nome = Faker.instance().name();
//		String senha = Faker.instance().number().digits(10);
//		Funcionario funcionario = new Funcionario();
//		String nomefunc = nome.firstName();
//		funcionario.setNome(nomefunc);
//		funcionario.setLogin(nomefunc);
//		funcionario.setSenha(senha);
//		funcionario.setAtivo(Faker.instance().bool().bool());
//		funcionario.setTipoDeFuncionario(TipoDeFuncionario.GERENTE);
//		return funcionario;
//
//	}
}
