package br.com.churrascaria.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_OpcaoProdutoPadrao")
public class OpcaoProdutoPadrao extends Opcao {
	@Column(nullable = false)
	private Float valorASerUltilizado;
	
	@ManyToOne
	private ProdutoPadrao produtoPadrao;
	

	public ProdutoPadrao getProdutoPadrao() {
		return produtoPadrao;
	}

	public void setProdutoPadrao(ProdutoPadrao produtoPadrao) {
		this.produtoPadrao = produtoPadrao;
	}

	public Float getValorASerUltilizado() {
		return valorASerUltilizado;
	}

	public void setValorASerUltilizado(Float valorASerUltilizado) {
		this.valorASerUltilizado = valorASerUltilizado;
	}

}
