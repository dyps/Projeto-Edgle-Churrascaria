package br.com.churrascaria.entities;

public enum TipoAcaoItemPedido {
	
	REALIZOUPEDIDO("Realizou Pedido"), ALTEROU("Alterou"), ENTREGOU("Entregou"), CANCELOU("Cancelou");

	private String nome;

	TipoAcaoItemPedido(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
