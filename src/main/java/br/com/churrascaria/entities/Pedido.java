package br.com.churrascaria.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TB_Pedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PedidoSeq")
	@SequenceGenerator(name = "PedidoSeq", sequenceName = "PEDIDO_SEQ", allocationSize = 1)
	private Long Id;
	
	@Column(nullable = false, unique=true)
	private Integer numero;
	
	private Cliente cliente;
	
	private Mesa mesa;
	
	@Column(nullable = true)
	private String observacao;
	
	//array de itens do pedido
	
	private Date dataPedido;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoDePedido tipoDePedido;

}
