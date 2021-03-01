package br.com.churrascaria.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TB_OpcaoInsumo")
public class OpcaoInsumo extends Opcao{
	
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private Float valorDeVenda;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Float getValorDeVenda() {
		return valorDeVenda;
	}
	public void setValorDeVenda(Float valorDeVenda) {
		this.valorDeVenda = valorDeVenda;
	}


	
	

}
