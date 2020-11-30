package br.projeto1EdgleChurrascaria.entities;

public interface Identificavel extends Cloneable {

	public Integer getId();

	public void setId(Integer Id);

	public Identificavel clone();

}
