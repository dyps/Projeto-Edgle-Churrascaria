package br.com.churrascaria.beans;

public final class EnderecoPaginas {

	public EnderecoPaginas() {
		throw new UnsupportedOperationException("Esta classe não deve ser instanciada!");
	}

	private static final String REDIRECT_TRUE = "?faces-redirect=true";

	public static final String PAGINA_PRINCIPAL_FUNCIONARIO = "/paginas/protegidas/pessoas/funcionario/index.xhtml"
			+ REDIRECT_TRUE;

	public static final String PAGINA_PRINCIPAL_MESA = "/paginas/protegidas/gestao/mesas/index.xhtml" + REDIRECT_TRUE;

	public static final String PAGINA_PRINCIPAL_CATEGORIAPRODUTO = "/paginas/protegidas/catalogo/produtos/index.xhtml"
			+ REDIRECT_TRUE;

	public static final String PAGINA_PRINCIPAL_ENTREGADOR = "/paginas/protegidas/pessoas/entregadores/index.xhtml"
			+ REDIRECT_TRUE;

	public static final String PAGINA_PRINCIPAL_OBSERVACAO = "/paginas/protegidas/catalogo/observacoes/index.xhtml"
			+ REDIRECT_TRUE;
	
	public static final String PAGINA_PRINCIPAL_CLIENTE = "/paginas/protegidas/vendas/clientes/index.xhtml"
			+ REDIRECT_TRUE;
	
	public static final String PAGINA_NOVO_PEDIDO = "/paginas/protegidas/vendas/pedidos/pedido_edit.xhtml"
			+ REDIRECT_TRUE;

	public static final String PAGINA_PRINCIPAL_FORMAS_DE_PAGAMENTO  = "/paginas/protegidas/gestao/formasdepagamento/index.xhtml"
			+ REDIRECT_TRUE;

	public static final String PAGINA_PRINCIPAL_VENDAS = "/paginas/protegidas/vendas/pedidos/index.xhtml"
			+ REDIRECT_TRUE;

}
