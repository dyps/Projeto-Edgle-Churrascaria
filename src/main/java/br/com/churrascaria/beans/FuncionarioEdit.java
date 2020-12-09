package br.com.churrascaria.beans;


import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import br.com.churrascaria.entities.Funcionario;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.FuncionarioServiceImplementacao;

@ViewScoped
@ManagedBean
public class FuncionarioEdit extends AbstractBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FuncionarioServiceImplementacao funcionarioService = new FuncionarioServiceImplementacao();
	
	private Funcionario funcionario;
	
	public void init() {
		if (funcionario == null) {
			// Criando novo funcionario
			funcionario = new Funcionario();
		}
	}
	
	public String saveFuncionario() {
		try {
			if (isEdicaoDeFuncionario()) {
				funcionarioService.update(funcionario.getId() , funcionario);
			} else {
				funcionarioService.save(funcionario);
			}
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Funcionario '" + funcionario.getNome() + "' saved");

		return EnderecoPaginas.PAGINA_PRINCIPAL;
	}
	
	public boolean isEdicaoDeFuncionario() {
		return funcionario != null && funcionario.getId() != null;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
