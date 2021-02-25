package br.com.churrascaria.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TB_Entregadores")
public class Entregador {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EntregadorSeq")
	@SequenceGenerator(name = "EntregadorSeq", sequenceName = "ENTREGADOR_SEQ", allocationSize = 1)
	private Long Id;

	@Column(nullable = false)
	private String nome;

	@Column(unique = true, nullable = false)
	private String telefone;
	
	@OneToMany(mappedBy = "entregador", cascade = CascadeType.ALL)
	private List<TaxaEntrega> taxas;

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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public boolean isPrimeiro() {
		return primeiro;
	}

	public void setPrimeiro(boolean primeiro) {
		this.primeiro = primeiro;
	}

	public List<TaxaEntrega> getTaxas() {
		return taxas;
	}

	public void setTaxas(List<TaxaEntrega> taxas) {
		this.taxas = taxas;
	}

}
