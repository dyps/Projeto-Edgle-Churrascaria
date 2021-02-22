package br.com.churrascaria.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TB_Mesas")
public class Mesa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MesaSeq")
	@SequenceGenerator(name = "MesaSeq", sequenceName = "MESA_SEQ", allocationSize = 1)
	private Long Id;
	
	@Column(unique = true, nullable = false)
	private Integer numero;
	
	@Transient
	private boolean primeiro = false;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	public void setPrimeiro(boolean b) {
		primeiro = b;

	}

	public boolean isPrimeiro() {
		return primeiro;
	}

}
