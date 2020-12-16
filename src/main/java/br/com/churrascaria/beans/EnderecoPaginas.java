package br.com.churrascaria.beans;


public final class EnderecoPaginas {
	
	public EnderecoPaginas() {
		throw new UnsupportedOperationException("Esta classe não deve ser instanciada!");
	}
	
	private static final String REDIRECT_TRUE = "?faces-redirect=true";
	
	public static final String PAGINA_PRINCIPAL_FUNCIONARIO = "/paginas/protegidas/pessoas/funcionario/index.xhtml" + REDIRECT_TRUE;

}
