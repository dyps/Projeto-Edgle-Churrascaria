package br.com.churrascaria.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TB_Pedido")
public class Pedido  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PedidoSeq")
	@SequenceGenerator(name = "PedidoSeq", sequenceName = "PEDIDO_SEQ", allocationSize = 1)
	private Long Id;
	
	@Column(nullable = false)
	private Integer numero;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoDePedido tipoDePedido;
	
	@ManyToOne
	private Cliente cliente;
	
	@ManyToOne
	private Mesa mesa;
	
	private String observacao;
	
	@OneToMany(mappedBy = "pedido")
	private List<Item> itens;
	
	@OneToOne
	private Entrega entrega;
	
	
	

}
