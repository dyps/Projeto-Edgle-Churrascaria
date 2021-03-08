package br.com.churrascaria.filter;

public class ClienteFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String nome;
	
	private String telefone;
	
	public ClienteFilter() {
		
	}
	
	public boolean isEmpty() {
		if (this.id != null && !this.id.toString().trim().isEmpty()) {
			return false;
		}
		if (this.nome != null && !this.nome.trim().isEmpty()) {
			return false;
		}
		if (this.telefone != null && !this.telefone.trim().isEmpty()) {
			return false;
		}
		return true;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
