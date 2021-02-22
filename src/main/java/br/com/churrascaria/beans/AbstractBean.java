package br.com.churrascaria.beans;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

	public Funcionario getFuncionarioLogado() throws IOException {
		FuncionarioFilter filter = new FuncionarioFilter();
		filter.setLogin(getFuncionarioLogin());
		List<Funcionario> funcs = null;
		try {
			funcs = funcionarioService.findBy(filter);
			if (!funcs.isEmpty()) {
				return funcs.get(0);
			}
		} catch (ServiceEdgleChurrascariaException e) {

			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();
			HttpSession session = (HttpSession) ec.getSession(false);
			session.invalidate();
			// XXX Chamada #logout() abaixo necessária, pois:
			// https://stackoverflow.com/a/26421775/4023351
			HttpServletRequest request = (HttpServletRequest) ec.getRequest();
			ec.redirect(request.getContextPath());
			reportarMensagemDeErro("Erro ao recuperar o funcionario logado!");
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
		String sumario = "Sucesso: ";
		Severity severity = FacesMessage.SEVERITY_INFO;
		if (isErro) {
			sumario = "Erro: ";
			severity = FacesMessage.SEVERITY_ERROR;
			FacesContext.getCurrentInstance().validationFailed();
		}

		FacesMessage msg = new FacesMessage(severity, sumario + " " + detalhe, null);

		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.setKeepMessages(keepMessages);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
