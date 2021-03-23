package br.com.churrascaria.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ObservacaoPadrao")
public class ObservacaoPadrao implements Identificavel {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ObservacaoPadraoSeq")
	@SequenceGenerator(name = "ObservacaoPadraoSeq", sequenceName = "OBSERVACAOPADRAO_SEQ", allocationSize = 1)
	private Long id;
	
	@ManyToMany
	@JoinTable(name = "tb_observacaopadrao_do_produto", joinColumns = {
			@JoinColumn(name = "observacoesPadrao_id") }, inverseJoinColumns = { @JoinColumn(name = "produto_id") })
	private List<Produto> produtos;
	
	@Column(nullable = false, unique = true)
	private String descricao;
	
	@ManyToMany
	private List<Item> listItens;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Item> getListItens() {
		return listItens;
	}

	public void setListItens(List<Item> listItens) {
		this.listItens = listItens;
	}

	@Override
	public Identificavel clone() {
		ObservacaoPadrao aux = new ObservacaoPadrao();
		aux.id = id;
		aux.descricao = descricao;
		return aux;
	}

	@Override
	public String toString() {
		return "ObservacaoPadrao [id=" + id + ", descricao=" + descricao + "]";
	}


	
}