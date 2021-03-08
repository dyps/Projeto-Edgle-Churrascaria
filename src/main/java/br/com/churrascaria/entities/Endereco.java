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
@Table(name = "TB_Enderecos")
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EnderecoSeq")
	@SequenceGenerator(name = "EnderecoSeq", sequenceName = "ENDERECO_SEQ", allocationSize = 1)
	private Long Id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String logradouro;
	
	@Column(nullable = false)
	private Integer numero;
	
	private String complemento;
	
	@ManyToOne(optional = false)
	private Cliente cliente;

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

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
