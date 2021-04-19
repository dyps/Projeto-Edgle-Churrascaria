package br.com.churrascaria.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TB_TipoDePagamento")
public class TipoDePagamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TipoDePagamentoSeq")
	@SequenceGenerator(name = "TipoDePagamentoSeq", sequenceName = "TIPODEPAGAMENTO_SEQ", allocationSize = 1)
	private Long Id;

	@ManyToMany(mappedBy = "tipoDePagamento" ,cascade = CascadeType.MERGE)
	private List<BandeiraDeCartao> bandeirasDeCartao;
	
	private String nome;
	
	private boolean valorDeAberturaDefaultZero;
	
	@Transient
	private boolean primeiro = false;

	public boolean isPrimeiro() {
		return primeiro;
	}

	public void setPrimeiro(boolean primeiro) {
		this.primeiro = primeiro;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public List<BandeiraDeCartao> getBandeirasDeCartao() {
		return bandeirasDeCartao;
	}

	public void setBandeirasDeCartao(List<BandeiraDeCartao> bandeirasDeCartao) {
		this.bandeirasDeCartao = bandeirasDeCartao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isValorDeAberturaDefaultZero() {
		return valorDeAberturaDefaultZero;
	}

	public void setValorDeAberturaDefaultZero(boolean valorDeAberturaDefaultZero) {
		this.valorDeAberturaDefaultZero = valorDeAberturaDefaultZero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		result = prime * result + ((bandeirasDeCartao == null) ? 0 : bandeirasDeCartao.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + (valorDeAberturaDefaultZero ? 1231 : 1237);
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
		TipoDePagamento other = (TipoDePagamento) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	
	
	
	
	

}