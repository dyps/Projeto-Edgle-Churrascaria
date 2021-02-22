package br.com.churrascaria.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.checkerframework.common.aliasing.qual.Unique;

import com.sun.istack.NotNull;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "TB_Funcionarios")
public class Funcionario implements Identificavel {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FuncionarioSeq")
	@SequenceGenerator(name = "FuncionarioSeq", sequenceName = "FUNCIONARIO_SEQ", allocationSize = 1)
	private Long Id;

	@NotNull
	private String nome;
	@NotNull
	@Unique
	private String login;
	@NotNull
	private String senha;

	@NotNull
	private boolean ativo;

	@Enumerated(EnumType.STRING)
	@NotNull
	private TipoDeFuncionario tipoDeFuncionario;

	@Transient
	private boolean primeiro = false;

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

	public Long getId() {
		return Id;
	}

	public void setId(Long Id) {
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

	public String getSenhaFechada() {
		return "********";
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

	@Override
	public String toString() {
		return "Funcionario [id=" + Id + ", nome=" + nome + ", login=" + login + ", senha" + senha
				+ ", tipoDeFuncionario=" + tipoDeFuncionario.getNome() + ", ativo=" + ativo + "]";
	}

	public void setPrimeiro(boolean b) {
		primeiro = b;

	}

	public boolean isPrimeiro() {
		return primeiro;
	}

}
