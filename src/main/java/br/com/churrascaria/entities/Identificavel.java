package br.com.churrascaria.entities;

public interface Identificavel extends Cloneable {

	public Long getId();

	public void setId(Long Id);

	public Identificavel clone();

}
