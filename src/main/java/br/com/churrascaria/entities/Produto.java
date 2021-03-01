package br.com.churrascaria.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ProdutoSeq")
	@SequenceGenerator(name = "ProdutoSeq", sequenceName = "PRODUTO_SEQ", allocationSize = 1)
	private Long id;

	@Column(nullable = false, unique = true)
	private String nome;

	private String imagem;

	private boolean habilitado;

	@ManyToOne
	private CategoriaProduto categoriaProduto;

	public CategoriaProduto getCategoriaProduto() {
		return categoriaProduto;
	}

	public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
		this.categoriaProduto = categoriaProduto;
	}

	@ManyToMany
	@JoinTable(name = "tb_observacaopadrao_do_produto")
	private List<ObservacaoPadrao> observacoesPadrao;

	public List<ObservacaoPadrao> getObservacoesPadrao() {
		return observacoesPadrao;
	}

	public void setObservacoesPadrao(List<ObservacaoPadrao> observacoesPadrao) {
		this.observacoesPadrao = observacoesPadrao;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}