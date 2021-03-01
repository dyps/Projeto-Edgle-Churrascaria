package br.com.churrascaria.beans.funcionario;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.beans.AbstractBean;
import br.com.churrascaria.beans.EnderecoPaginas;
import br.com.churrascaria.entities.Funcionario;
import br.com.churrascaria.services.CRUDService;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;

@ViewScoped
@Named
public class FuncionarioEdit extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8240621748414014769L;

	/**
	 * 
	 */

	@Inject
	private CRUDService<Funcionario> funcionarioService;

	private Funcionario funcionario;


	public String init() {
		try {
			if (funcionario == null) {
				funcionario = new Funcionario();
			} else {
				funcionario = funcionarioService.getByID(funcionario.getId());
			}
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
		}
		return null;
	}

	public String saveFuncionario() {
		try {
			if (isEdicaoDeFuncionario()) {
				funcionarioService.update(funcionario);
			} else {
				funcionarioService.save(funcionario);
			}
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Funcionario '" + funcionario.getNome() + "' salvo");

		return EnderecoPaginas.PAGINA_PRINCIPAL_FUNCIONARIO;
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
