package br.com.churrascaria.services;

public class ServiceEdgleChurrascariaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceEdgleChurrascariaException(String mensagem) {
		super(mensagem);
	}

	public ServiceEdgleChurrascariaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
