package br.com.churrascaria.beans;



import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.entities.Funcionario;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.FuncionarioServiceImplementacao;

@ViewScoped
@Named
public class FuncionarioDelete extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Funcionario funcionario;

	@Inject
	private FuncionarioServiceImplementacao funcionarioService ;

	public String delete() {
		try {
			funcionarioService.delete(funcionario);
		} catch (ServiceEdgleChurrascariaException e) {
			e.printStackTrace();
			return null;
		}

		reportarMensagemDeSucesso("Funcionario '" + funcionario.getNome() + "' deleted");

		return EnderecoPaginas.PAGINA_PRINCIPAL;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
