package br.com.churrascaria.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TB_CategoriaProdutos")
public class CategoriaProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CategoriaProdutoSeq")
	@SequenceGenerator(name = "CategoriaProdutoSeq", sequenceName = "CATEGORIAPRODUTO_SEQ", allocationSize = 1)
	private Long Id;

	@Column(nullable = false, unique = true)
	private String nome;

	@Transient
	private boolean primeiro = false;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isPrimeiro() {
		return primeiro;
	}

	public void setPrimeiro(boolean primeiro) {
		this.primeiro = primeiro;
	}

}
