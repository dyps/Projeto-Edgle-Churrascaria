package br.com.churrascaria.entities;

public enum TipoDeMedida {
	QUILOGRAMA("Quilograma"), UNIDADE("Unidade");

	private String nome;

	TipoDeMedida(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
