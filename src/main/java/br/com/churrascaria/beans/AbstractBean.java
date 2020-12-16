package br.com.churrascaria.beans;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;

import br.com.churrascaria.entities.Funcionario;
import br.com.churrascaria.entities.TipoDeFuncionario;
import br.com.churrascaria.filter.FuncionarioFilter;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.FuncionarioServiceImplementacao;

public abstract class AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Inject
	private FuncionarioServiceImplementacao funcionarioService;

	public Funcionario getFuncionarioLogado() {
		FuncionarioFilter filter = new FuncionarioFilter();
		filter.setLogin(getFuncionarioLogin());
		List<Funcionario> funcs = null;
		try {
			funcs = funcionarioService.findBy(filter);
		} catch (ServiceEdgleChurrascariaException e) {
			e.printStackTrace();
			reportarMensagemDeErro("Erro ao recuperar o funcionario logado!");
		}

		if (!funcs.isEmpty()) {
			return funcs.get(0);
		}
		return null;
	}

	private String getFuncionarioLogin() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Principal funcPrincipal = externalContext.getUserPrincipal();
		if (funcPrincipal == null) {
			return "";
		}

		return funcPrincipal.getName();
	}

	public TipoDeFuncionario[] getTipoDeFuncionario() {
		return TipoDeFuncionario.values();
	}

	protected void reportarMensagemDeErro(String detalhe) {
		reportarMensagem(true, detalhe, false);
	}

	protected void reportarMensagemDeSucesso(String detalhe) {
		reportarMensagem(false, detalhe, true);
	}

	private void reportarMensagem(boolean isErro, String detalhe, boolean keepMessages) {
		String sumario = "Success!";
		Severity severity = FacesMessage.SEVERITY_INFO;
		if (isErro) {
			sumario = "Error!";
			severity = FacesMessage.SEVERITY_ERROR;
			FacesContext.getCurrentInstance().validationFailed();
		}

		FacesMessage msg = new FacesMessage(severity, sumario + " " + detalhe, null);

		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.setKeepMessages(keepMessages);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
