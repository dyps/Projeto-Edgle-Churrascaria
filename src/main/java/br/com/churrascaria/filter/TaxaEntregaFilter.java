package br.com.churrascaria.filter;

import br.com.churrascaria.services.ServiceEdgleChurrascariaException;

public class TaxaEntregaFilter implements Filter {
	public void validate() throws ServiceEdgleChurrascariaException {
		// TODO Auto-generated method stub
		Filter.super.validate();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getDistanciaMaxima() {
		return distanciaMaxima;
	}

	public void setDistanciaMaxima(Double distanciaMaxima) {
		this.distanciaMaxima = distanciaMaxima;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Double valor;
	
	private Double distanciaMaxima;
	
	public boolean isEmpty() {
		if (this.id != null && !this.id.toString().trim().isEmpty()) {
			return false;
		}
		if (this.valor != null) {
			return false;
		}
		if (this.distanciaMaxima != null) {
			return false;
		}
		return true;
	}
	
	public String toString() {
		return "CategoriaProdutoFilter [id=" + id + ", valor=" + valor + ", distanciaMaxima=" + distanciaMaxima + "]";
	}

}
