package br.projeto1EdgleChurrascaria.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import br.projeto1EdgleChurrascaria.entities.TipoDeFuncionario;

public abstract class AbstractBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
