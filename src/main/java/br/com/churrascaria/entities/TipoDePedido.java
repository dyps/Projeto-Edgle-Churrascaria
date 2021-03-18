package br.com.churrascaria.entities;

public enum TipoDePedido {
	BALCAO("Balcão"), MESA("Mesa"), DELIVERY("Delivery");
	
	private String nome;

	TipoDePedido(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
