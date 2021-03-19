package br.com.churrascaria.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ProdutoPersonalizado", catalog = "public")
public class ProdutoPersonalizado extends Produto {

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "produtoPersonalizado" )
	private List<ItemDeConfiguracao> itensDeConfiguracao;

	public List<ItemDeConfiguracao> getItensDeConfiguracao() {
		return itensDeConfiguracao;
	}

	public void setItensDeConfiguracao(List<ItemDeConfiguracao> itensDeConfiguracao) {
		this.itensDeConfiguracao = itensDeConfiguracao;
	}

}
