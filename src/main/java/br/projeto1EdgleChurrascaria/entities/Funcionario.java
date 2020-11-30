package br.projeto1EdgleChurrascaria.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "TB_Funcionarios")
public class Funcionario implements Identificavel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer Id;

	private String nome;
	private String login;
	private String senha;

	private boolean ativo;
	private TipoDeFuncionario tipoDeFuncionario;

	public Identificavel clone() {
		Funcionario funcionario = new Funcionario();
		funcionario.Id = Id;
		funcionario.nome = nome;
		funcionario.login = login;
		funcionario.senha = senha;
		funcionario.ativo = ativo;
		funcionario.tipoDeFuncionario = tipoDeFuncionario;
		return funcionario;
		
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer Id) {
		this.Id = Id;

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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public TipoDeFuncionario getTipoDeFuncionario() {
		return tipoDeFuncionario;
	}

	public void setTipoDeFuncionario(TipoDeFuncionario tipoDeFuncionario) {
		this.tipoDeFuncionario = tipoDeFuncionario;
	}

}
