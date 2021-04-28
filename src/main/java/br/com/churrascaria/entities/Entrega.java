package br.com.churrascaria.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TB_Entrega")
public class Entrega  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EntregaSeq")
	@SequenceGenerator(name = "EntregaSeq", sequenceName = "ENTREGA_SEQ", allocationSize = 1)
	private Long Id;
	
	@Column(nullable = false)
	private LocalDateTime dataSaida;
	
	@Column(nullable = false)
	private LocalDateTime dataChegada;
	
	@ManyToOne(optional = false)
	private Entregador entregador;
	
	
	@ManyToOne(optional = false)
	private TaxaEntrega taxaEntrega;
	
	@ManyToOne(optional = false)
	private Endereco endereco;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public LocalDateTime getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDateTime dataSaida) {
		this.dataSaida = dataSaida;
	}

	public LocalDateTime getDataChegada() {
		return dataChegada;
	}

	public void setDataChegada(LocalDateTime dataChegada) {
		this.dataChegada = dataChegada;
	}

	public Entregador getEntregador() {
		return entregador;
	}

	public void setEntregador(Entregador entregador) {
		this.entregador = entregador;
	}

	public TaxaEntrega getTaxaEntrega() {
		return taxaEntrega;
	}

	public void setTaxaEntrega(TaxaEntrega taxaEntrega) {
		this.taxaEntrega = taxaEntrega;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
