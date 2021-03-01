package br.com.churrascaria.filter;

public class ProdutoFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nome;
	private Long idCategoria;

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "ProdutoFilter [nome=" + nome + ", idCategoria=" + idCategoria + "]";
	}

	public boolean isEmpty() {
		if (this.idCategoria != null && !this.idCategoria.toString().trim().isEmpty()) {
			return false;
		}
		if (this.nome != null && !this.nome.trim().isEmpty()) {
			return false;
		}
		return true;
	}

}