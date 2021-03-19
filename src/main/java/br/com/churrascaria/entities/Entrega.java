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
	
	

}
