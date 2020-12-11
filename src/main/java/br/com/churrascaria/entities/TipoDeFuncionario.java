package br.com.churrascaria.entities;

public enum TipoDeFuncionario {
	GARCOM("Garçom"), COZINHEIRO("Cozinheiro"), GERENTE("Gerente");

	private String nome;

	TipoDeFuncionario(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	


}
