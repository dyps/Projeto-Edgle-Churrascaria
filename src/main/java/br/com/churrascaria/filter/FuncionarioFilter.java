package br.com.churrascaria.filter;

import br.com.churrascaria.entities.TipoDeFuncionario;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;

public  class FuncionarioFilter implements Filter{
	public void validate() throws ServiceEdgleChurrascariaException {
		// TODO Auto-generated method stub
		Filter.super.validate();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String nome;
	
	private String login;
	
	private Boolean ativo;
	
	private TipoDeFuncionario tipoDeFuncionario;
	
	public FuncionarioFilter() {
		
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}




	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}




	public Boolean getAtivo() {
		return ativo;
	}


	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}


	public TipoDeFuncionario getTipoDeFuncionario() {
		return tipoDeFuncionario;
	}

	public void setTipoDeFuncionario(TipoDeFuncionario tipoDeFuncionario) {
		this.tipoDeFuncionario = tipoDeFuncionario;
	}
	
	public boolean isEmpty() {
		if (this.id != null && !this.id.toString().trim().isEmpty()) {
			return false;
		}
		if (this.nome != null && !this.nome.trim().isEmpty()) {
			return false;
		}
		if (this.login != null && !this.login.trim().isEmpty()) {
			return false;
		}
		if (this.tipoDeFuncionario != null) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "FuncionarioFilter [id=" + id + ", nome=" + nome + ", login="
				+ login  + ", ativo=" + ativo + ", tipoDeFuncionario="
				+ tipoDeFuncionario + "]";
	}

}
