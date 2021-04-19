package br.com.churrascaria.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TB_Item")
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ItemSeq")
	@SequenceGenerator(name = "ItemSeq", sequenceName = "ITEM_SEQ", allocationSize = 1)
	private Long Id;
	
	@Column(nullable = false)
	private float quantidade = 1;
	
	@Column(nullable = false)
	private float valor;
	
	@OneToMany(mappedBy = "item")
	private List<AcaoRealizada> listAcaoRealizada;
	
	@ManyToOne
	private Pedido pedido;

	@ManyToOne
	private Produto produto;
	
	@ManyToMany(mappedBy = "listVezesSelecionada" )
	private List<Opcao> listOpcoes;
	
	@ManyToMany(mappedBy = "listItens", fetch = FetchType.EAGER)
	private List<ObservacaoPadrao> listObservacoes;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public List<AcaoRealizada> getListAcaoRealizada() {
		return listAcaoRealizada;
	}

	public void setListAcaoRealizada(List<AcaoRealizada> listAcaoRealizada) {
		this.listAcaoRealizada = listAcaoRealizada;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Opcao> getListOpcoes() {
		return listOpcoes;
	}

	public void setListOpcoes(List<Opcao> listOpcoes) {
		this.listOpcoes = listOpcoes;
	}

	public List<ObservacaoPadrao> getListObservacoes() {
		return listObservacoes;
	}

	public void setListObservacoes(List<ObservacaoPadrao> listObservacoes) {
		this.listObservacoes = listObservacoes;
	}

	@Override
	public String toString() {
		return "Item [Id=" + Id + ", quantidade=" + quantidade + ", valor=" + valor + ", listAcaoRealizada="
				+ listAcaoRealizada + ", pedido=" + pedido + ", produto=" + produto + ", listOpcoes=" + listOpcoes
				+ ", listObservacoes=" + listObservacoes + "]";
	}
	
	
	
	
}
