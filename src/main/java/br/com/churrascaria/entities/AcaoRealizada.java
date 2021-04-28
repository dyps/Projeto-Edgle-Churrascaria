package br.com.churrascaria.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TB_AcaoRealizada")
public class AcaoRealizada {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AcaoRealizadaSeq")
	@SequenceGenerator(name = "AcaoRealizadaSeq", sequenceName = "ACAOREALIZADA_SEQ", allocationSize = 1)
	private Long Id;

	@Column(nullable = false)
	private LocalDateTime data;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoAcaoItemPedido tipoAcaoItemPedido;

	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	private Funcionario funcionario;

	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	private Item item;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public TipoAcaoItemPedido getTipoAcaoItemPedido() {
		return tipoAcaoItemPedido;
	}

	public void setTipoAcaoItemPedido(TipoAcaoItemPedido tipoAcaoItemPedido) {
		this.tipoAcaoItemPedido = tipoAcaoItemPedido;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	public String getDataFormatada() {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
		return data.format(formatter );
	}

	@Override
	public String toString() {
		return "AcaoRealizada [Id=" + Id + ", data=" + data + ", tipoAcaoItemPedido=" + tipoAcaoItemPedido
				+ ", funcionario=" + funcionario + "]";
	}

}
