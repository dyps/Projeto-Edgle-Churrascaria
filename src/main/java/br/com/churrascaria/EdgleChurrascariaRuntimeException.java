package br.com.churrascaria;

public class EdgleChurrascariaRuntimeException extends Exception {

	private static final long serialVersionUID = 1L;

	public EdgleChurrascariaRuntimeException(String mensagem) {
		super(mensagem);
	}

	public EdgleChurrascariaRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}