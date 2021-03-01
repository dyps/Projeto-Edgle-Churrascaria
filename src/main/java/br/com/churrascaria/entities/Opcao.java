package br.com.churrascaria.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Opcao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OpcaoSeq")
	@SequenceGenerator(name = "OpcaoSeq", sequenceName = "OPCAO_SEQ", allocationSize = 1)
	private Long id;
	
	@ManyToOne
	private ItemDeConfiguracao itemDeConfiguracao;
	
	private boolean ativa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ItemDeConfiguracao getItemDeConfiguracao() {
		return itemDeConfiguracao;
	}

	public void setItemDeConfiguracao(ItemDeConfiguracao itemDeConfiguracao) {
		this.itemDeConfiguracao = itemDeConfiguracao;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}
	
	

}
