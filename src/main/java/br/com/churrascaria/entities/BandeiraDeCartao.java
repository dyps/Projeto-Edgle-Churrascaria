package br.com.churrascaria.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TB_BandeiraDeCartao")
public class BandeiraDeCartao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BandeiraDeCartaoSeq")
	@SequenceGenerator(name = "BandeiraDeCartaoSeq", sequenceName = "BANDEIRADECARTAO_SEQ", allocationSize = 1)
	private Long Id;
	
	@ManyToMany
	private List<TipoDePagamento> tipoDePagamento;
	
	private String nome;
	
	@Transient
	private boolean primeiro = false;

	public boolean isPrimeiro() {
		return primeiro;
	}

	public void setPrimeiro(boolean primeiro) {
		this.primeiro = primeiro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public List<TipoDePagamento> getTipoDePagamento() {
		return tipoDePagamento;
	}

	public void setTipoDePagamento(List<TipoDePagamento> tipoDePagamento) {
		this.tipoDePagamento = tipoDePagamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((tipoDePagamento == null) ? 0 : tipoDePagamento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BandeiraDeCartao other = (BandeiraDeCartao) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (tipoDePagamento == null) {
			if (other.tipoDePagamento != null)
				return false;
		} else if (!tipoDePagamento.equals(other.tipoDePagamento))
			return false;
		return true;
	}
	
	
	
	

}
