package br.com.churrascaria.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
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

//	@Lob
//	private UploadedFile imagem;

	private boolean habilitado = true;

	@ManyToOne
	private CategoriaProduto categoriaProduto;

	@ManyToMany
	@JoinTable(name = "tb_observacaopadrao_do_produto", joinColumns = {
			@JoinColumn(name = "produto_id") }, inverseJoinColumns = { @JoinColumn(name = "observacoesPadrao_id") })
	private List<ObservacaoPadrao> observacoesPadrao;

	public boolean isDeletavel() {
		// aki deve ser implementada a regra para saver se o produto ja foi vendido
		// alguma vez
		return true;

	}

	public CategoriaProduto getCategoriaProduto() {
		return categoriaProduto;
	}

	public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
		this.categoriaProduto = categoriaProduto;
	}

	public List<ObservacaoPadrao> getObservacoesPadrao() {
		return observacoesPadrao;
	}

	public void setObservacoesPadrao(List<ObservacaoPadrao> observacoesPadrao) {
		this.observacoesPadrao = observacoesPadrao;
	}

//	public UploadedFile getImagem() {
//		return imagem;
//	}
//
//	public void setImagem(UploadedFile imagem) {
//		this.imagem = imagem;
//	}

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

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", habilitado=" + habilitado + ", observacoesPadrao="
				+ observacoesPadrao + "]";
	}
	
	

}
