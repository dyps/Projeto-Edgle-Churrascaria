package br.com.churrascaria.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ErroPaginaLogin extends AbstractBean {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void dispararMensagemLoginErro(boolean error){
		if(error){
			reportarMensagemDeErro("Login e/ou senha errada.");
		}
	}

}