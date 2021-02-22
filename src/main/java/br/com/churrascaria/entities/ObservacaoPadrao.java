package br.com.churrascaria.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ObservacaoPadrao")
public class ObservacaoPadrao implements Identificavel {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ObservacaoPadraoSeq")
	@SequenceGenerator(name = "ObservacaoPadraoSeq", sequenceName = "OBSERVACAOPADRAO_SEQ", allocationSize = 1)
	private Long id;
	@Column(nullable = false, unique = true)
	private String descricao;

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

	@Override
	public Identificavel clone() {
		ObservacaoPadrao aux = new ObservacaoPadrao();
		aux.id = id;
		aux.descricao = descricao;
		return aux;
	}

}