package br.com.churrascaria.entities;

import java.util.List;

import javax.persistence.Entity;
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
	
	@OneToMany (mappedBy = "itemDeConfiguracao")
	private List<Opcao> opcoes;

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
	
	

}