package br.projeto1EdgleChurrascaria.entities;

public enum TipoDeFuncionario {
	GARCOM("Garcom"), COZINHEIRO("Cozinheiro"), GERENTE("Gerente");

	private String nome;

	TipoDeFuncionario(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
