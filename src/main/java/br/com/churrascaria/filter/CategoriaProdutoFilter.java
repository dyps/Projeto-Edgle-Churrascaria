package br.com.churrascaria.filter;

import br.com.churrascaria.services.ServiceEdgleChurrascariaException;

public class CategoriaProdutoFilter implements Filter {
	public void validate() throws ServiceEdgleChurrascariaException {
		// TODO Auto-generated method stub
		Filter.super.validate();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;

	public CategoriaProdutoFilter() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isEmpty() {
		if (this.id != null && !this.id.toString().trim().isEmpty()) {
			return false;
		}
		if (this.nome != null && !this.nome.trim().isEmpty()) {
			return false;
		}
		return true;
	}

	public String toString() {
		return "CategoriaProdutoFilter [id=" + id + ", nome=" + nome + "]";
	}

}
