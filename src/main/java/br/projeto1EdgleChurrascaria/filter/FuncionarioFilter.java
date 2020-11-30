package br.projeto1EdgleChurrascaria.filter;

import br.projeto1EdgleChurrascaria.entities.TipoDeFuncionario;

public class FuncionarioFilter implements Filter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String nome;
	
	private String login;
	
	private String senha;
	
	private String ativo;
	
	private TipoDeFuncionario tipoDeFuncionario;
	
	public FuncionarioFilter() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public TipoDeFuncionario getTipoDeFuncionario() {
		return tipoDeFuncionario;
	}

	public void setTipoDeFuncionario(TipoDeFuncionario tipoDeFuncionario) {
		this.tipoDeFuncionario = tipoDeFuncionario;
	}
	
	public boolean isEmpty() {
		if (this.id != null && !this.id.trim().isEmpty()) {
			return false;
		}
		if (this.nome != null && !this.nome.trim().isEmpty()) {
			return false;
		}
		if (this.login != null && !this.login.trim().isEmpty()) {
			return false;
		}
		if (this.senha != null && !this.senha.trim().isEmpty()) {
			return false;
		}
		if (this.ativo != null && !this.ativo.trim().isEmpty()) {
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
				+ login + ", senha=" + senha + ", ativo=" + ativo + ", tipoDeFuncionario="
				+ tipoDeFuncionario + "]";
	}

}
