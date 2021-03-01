package br.com.churrascaria.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ProdutoPadrao" , catalog = "public")
public class ProdutoPadrao extends Produto{
	
	@Column(nullable = false)
	private Float valorDeVenda;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoDeMedida medida;

	public Float getValorDeVenda() {
		return valorDeVenda;
	}

	public void setValorDeVenda(Float valorDeVenda) {
		this.valorDeVenda = valorDeVenda;
	}

	public TipoDeMedida getMedida() {
		return medida;
	}

	public void setMedida(TipoDeMedida medida) {
		this.medida = medida;
	}
	
	

	
}
