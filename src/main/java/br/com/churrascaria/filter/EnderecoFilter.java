package br.com.churrascaria.filter;

public class EnderecoFilter  implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String nome;
	
	private String logradouro;
	
	private Integer numero;
	
	private String complemento;
	
	public boolean isEmpty() {
		if (this.id != null && !this.id.toString().trim().isEmpty()) {
			return false;
		}
		if (this.nome != null && !this.nome.toString().trim().isEmpty()) {
			return false;
		}
		if (this.logradouro != null && !this.logradouro.toString().trim().isEmpty()) {
			return false;
		}
		if (this.numero != null) {
			return false;
		}
		if (this.complemento != null && !this.complemento.toString().trim().isEmpty()) {
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

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

}
