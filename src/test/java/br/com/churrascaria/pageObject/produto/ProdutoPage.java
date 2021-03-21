package br.com.churrascaria.pageObject.produto;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;

public class ProdutoPage extends AbstractPage {

	private String urlPage = "http://127.0.0.1:8080/EdgleChurrascaria/paginas/protegidas/catalogo/produtos/index.xhtml";

	public ProdutoPage(WebDriver driver) {
		super(driver);
	}

	public void visita() {
		// visitar a página principal categoria de produtos
		open(urlPage);
	}

	public CriarCategoriaProdutoDialog nova() {
		$(By.xpath("//*[@id='idNovaCategoriaProduto']")).click();
		return new CriarCategoriaProdutoDialog(driver);
	}

	public CriarProdutoPage abrir() {
		$(By.xpath("//*[@id=\'formFilter:dataTableCategoriaProdutos:1:idAbrirCategoria']")).click();
		return new CriarProdutoPage(driver);
	}

	public ExcluirCategoriaProdutoDialog apagar() {
		$(By.xpath("//*[@id='formFilter:dataTableCategoriaProdutos:1:deleteCategoriaProduto']")).click();
		return new ExcluirCategoriaProdutoDialog(driver);
	}

	public void buscar(String nome) {
		setInputText("formFilter:itNomeFilter", nome);
		$(By.xpath("//*[@id='formFilter:btnFiltrarBotao']")).click();
	}

}
