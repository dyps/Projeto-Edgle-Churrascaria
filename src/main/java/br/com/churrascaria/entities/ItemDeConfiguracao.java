package br.com.churrascaria.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ItemDeConfiguracao")
public class ItemDeConfiguracao {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ItemDeConfiguracaoSeq")
	@SequenceGenerator(name = "ItemDeConfiguracaoSeq", sequenceName = "ITEMCONF_SEQ", allocationSize = 1)
	private Long id;

	@ManyToOne
	private ProdutoPersonalizado produtoPersonalizado;

	@OneToMany(mappedBy = "itemDeConfiguracao" ,cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	private List<Opcao> opcoes;

	private String nome;

	private Integer quantidadeMaxEscolhas;

	private boolean ativo = true;

	public boolean isDeletavel() {
		// aki fica a validacao para saber se o item pode ser deletado
		return true;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQuantidadeMaxEscolhas() {
		return quantidadeMaxEscolhas;
	}

	public void setQuantidadeMaxEscolhas(Integer quantidadeMaxEscolhas) {
		this.quantidadeMaxEscolhas = quantidadeMaxEscolhas;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public List<Opcao> getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(List<Opcao> opcoes) {
		this.opcoes = opcoes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProdutoPersonalizado getProdutoPersonalizado() {
		return produtoPersonalizado;
	}

	public void setProdutoPersonalizado(ProdutoPersonalizado produtoPersonalizado) {
		this.produtoPersonalizado = produtoPersonalizado;
	}

	public ItemDeConfiguracao(Long id, ProdutoPersonalizado produtoPersonalizado, List<Opcao> opcoes, String nome,
			Integer quantidadeMaxEscolhas, boolean ativo) {
		this.id = id;
		this.produtoPersonalizado = produtoPersonalizado;
		this.opcoes = opcoes;
		this.nome = nome;
		this.quantidadeMaxEscolhas = quantidadeMaxEscolhas;
		this.ativo = ativo;
	}

	public ItemDeConfiguracao() {
	}

	public ItemDeConfiguracao clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new ItemDeConfiguracao(id, produtoPersonalizado, opcoes, nome, quantidadeMaxEscolhas, ativo);
	}

	@Override
	public String toString() {
		return "ItemDeConfiguracao [id=" + id + ", produtoPersonalizado=" + produtoPersonalizado + ", opcoes=" + opcoes
				+ ", nome=" + nome + ", quantidadeMaxEscolhas=" + quantidadeMaxEscolhas + ", ativo=" + ativo + "]";
	}

}