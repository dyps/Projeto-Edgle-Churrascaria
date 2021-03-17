package br.com.churrascaria.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TB_Opcao")
public class Opcao {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OpcaoSeq")
	@SequenceGenerator(name = "OpcaoSeq", sequenceName = "OPCAO_SEQ", allocationSize = 1)
	private Long id;

	@ManyToOne
	private ItemDeConfiguracao itemDeConfiguracao;

	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private Float valorDeVenda;
	
	
	
	public boolean isDeletavel() {
		return true;
	}

	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ItemDeConfiguracao getItemDeConfiguracao() {
		return itemDeConfiguracao;
	}
	public void setItemDeConfiguracao(ItemDeConfiguracao itemDeConfiguracao) {
		this.itemDeConfiguracao = itemDeConfiguracao;
	}
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
