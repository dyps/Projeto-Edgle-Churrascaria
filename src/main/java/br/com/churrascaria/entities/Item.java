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
	private float quantidade;
	
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
	
}
