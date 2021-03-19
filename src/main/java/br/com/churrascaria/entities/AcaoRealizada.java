package br.com.churrascaria.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TB_AcaoRealizada")
public class AcaoRealizada {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AcaoRealizadaSeq")
	@SequenceGenerator(name = "AcaoRealizadaSeq", sequenceName = "ACAOREALIZADA_SEQ", allocationSize = 1)
	private Long Id;
	
	@Column(nullable = false)
	private LocalDateTime data;
	
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoAcaoItemPedido acaoItemPedido;
	
	@ManyToOne(optional = false)
	private Funcionario funcionario;
	
	@ManyToOne(optional = false)
	private Item item;

}
