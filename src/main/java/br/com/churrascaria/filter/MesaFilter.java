package br.com.churrascaria.filter;

import br.com.churrascaria.services.ServiceEdgleChurrascariaException;

public class MesaFilter implements Filter {
	public void validate() throws ServiceEdgleChurrascariaException {
		// TODO Auto-generated method stub
		Filter.super.validate();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer numero;

	public MesaFilter() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public boolean isEmpty() {
		if (this.id != null && !this.id.toString().trim().isEmpty()) {
			return false;
		}
		if (this.numero != null) {
			return false;
		}
		return true;
	}

	public String toString() {
		return "MesaFilter [id=" + id + ", numero=" + numero + "]";
	}

}
