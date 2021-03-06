package br.com.churrascaria.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TB_Pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PedidoSeq")
	@SequenceGenerator(name = "PedidoSeq", sequenceName = "PEDIDO_SEQ", allocationSize = 1)
	private Long Id;

	@Column(nullable = false)
	private Integer numero;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoDePedido tipoDePedido;

	@ManyToOne(cascade = CascadeType.MERGE , fetch = FetchType.EAGER)
	private Cliente cliente;

	@ManyToOne
	private Mesa mesa;

	private String observacao;
	
	private LocalDateTime finalizado = null;
	
	@OneToMany(mappedBy = "pedido", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Item> itens = new ArrayList<Item>();

	@OneToOne
	private Entrega entrega;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.MERGE)
	private List<Pagamento> pagamentos;

	@Transient
	private boolean primeiro = false;

	public boolean isPrimeiro() {
		return primeiro;
	}

	public void setPrimeiro(boolean primeiro) {
		this.primeiro = primeiro;
	}

	public LocalDateTime getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(LocalDateTime finalizado) {
		this.finalizado = finalizado;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}
	

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public TipoDePedido getTipoDePedido() {
		return tipoDePedido;
	}

	public void setTipoDePedido(TipoDePedido tipoDePedido) {
		this.tipoDePedido = tipoDePedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	@Override
	public String toString() {
		return "Pedido [Id=" + Id + ", numero=" + numero + ", tipoDePedido=" + tipoDePedido + ", cliente=" + cliente
				+ ", mesa=" + mesa + ", observacao=" + observacao + ", finalizado=" + finalizado + ", itens=" + itens
				+ ", entrega=" + entrega + ", pagamentos=" + pagamentos + "]";
	}

	
}
