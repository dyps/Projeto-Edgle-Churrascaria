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
@Table(name = "TB_TaxaEntregas")
public class TaxaEntrega {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TaxaEntregaSeq")
	@SequenceGenerator(name = "TaxaEntregaSeq", sequenceName = "TAXAENTREGA_SEQ", allocationSize = 1)
	private Long Id;
	
	@Column(nullable = false)
	private Double valor;
	
	@Column(nullable = false)
	private Double distanciaMaxima;
	
	@ManyToOne
	private Entregador entregador;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getDistanciaMaxima() {
		return distanciaMaxima;
	}

	public void setDistanciaMaxima(Double distanciaMaxima) {
		this.distanciaMaxima = distanciaMaxima;
	}

	public Entregador getEntregador() {
		return entregador;
	}

	public void setEntregador(Entregador entregador) {
		this.entregador = entregador;
	}

}
