package br.com.churrascaria.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TB_Pagamento")
public class Pagamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PagamentoSeq")
	@SequenceGenerator(name = "PagamentoSeq", sequenceName = "PAGAMENTO_SEQ", allocationSize = 1)
	private Long Id;
	
	@ManyToOne
	private Funcionario funcionario;
	
	@ManyToOne
	private Pedido pedido;
	
	private Float valorRecebido;
	
	private Float valorACobrar;
	
	private Float desconto;
	
	@ManyToOne
	private BandeiraDeCartao BandeiraDeCartao;
	
	@ManyToOne
	private TipoDePagamento tipoDePagamento;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Float getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(Float valorRecebido) {
		this.valorRecebido = valorRecebido;
	}

	public Float getValorACobrar() {
		return valorACobrar;
	}

	public void setValorACobrar(Float valorACobrar) {
		this.valorACobrar = valorACobrar;
	}

	public Float getDesconto() {
		return desconto;
	}

	public void setDesconto(Float desconto) {
		this.desconto = desconto;
	}

	public BandeiraDeCartao getBandeiraDeCartao() {
		return BandeiraDeCartao;
	}

	public void setBandeiraDeCartao(BandeiraDeCartao bandeiraDeCartao) {
		BandeiraDeCartao = bandeiraDeCartao;
	}

	public TipoDePagamento getTipoDePagamento() {
		return tipoDePagamento;
	}

	public void setTipoDePagamento(TipoDePagamento tipoDePagamento) {
		this.tipoDePagamento = tipoDePagamento;
	}
	
	
	

}
