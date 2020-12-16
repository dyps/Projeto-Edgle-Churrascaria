package br.com.churrascaria.beans.funcionario;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.beans.AbstractBean;
import br.com.churrascaria.beans.EnderecoPaginas;
import br.com.churrascaria.entities.Funcionario;
import br.com.churrascaria.entities.TipoDeFuncionario;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.FuncionarioServiceImplementacao;

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
	private FuncionarioServiceImplementacao funcionarioService;

	private Funcionario funcionario;

	public TipoDeFuncionario[] getTipoDeFuncionario() {
		return TipoDeFuncionario.values();
	}

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

		reportarMensagemDeSucesso("Funcionario '" + funcionario.getNome() + "' saved");

		return EnderecoPaginas.PAGINA_PRINCIPAL_FUNCIONARIO;
//		return "/paginas/protegidas/pessoas/funcionario/index.xhtml";
	}

	public boolean isEdicaoDeFuncionario() {
		return funcionario != null && funcionario.getId() != null;
	}

	public boolean isCozinheiro() {
		return funcionario != null && funcionario.getTipoDeFuncionario() == TipoDeFuncionario.COZINHEIRO;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
