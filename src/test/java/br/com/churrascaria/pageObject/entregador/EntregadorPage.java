package br.com.churrascaria.pageObject.entregador;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;

public class EntregadorPage extends AbstractPage {

	private String urlPage = "http://127.0.0.1:8080/EdgleChurrascaria/paginas/protegidas/pessoas/entregadores/index.xhtml";

	public EntregadorPage(WebDriver driver) {
		super(driver);
	}

	public void visita() {
		// visitar a página principal entregador
		open(urlPage);
	}

	public CriarEntregadorPage nova() {
		$(By.xpath("//*[@id=\'idNovoEntregador']")).click();
		return new CriarEntregadorPage(driver);
	}
	
	public CriarEntregadorPage editar() {
		$(By.xpath("//*[@id=\'formFilter:dataTableEntregadores:1:idEditarEntregador']")).click();
		return new CriarEntregadorPage(driver);
	}
	
	public ExcluirEntregadorDialog apagar() {
		$(By.xpath("//*[@id='formFilter:dataTableEntregadores:1:deleteFunc']")).click();
		return new ExcluirEntregadorDialog(driver);
	}
	
	public void buscar(String nome) {
		setInputText("formFilter:itNomeFilter", nome);
		$(By.xpath("//*[@id='formFilter:btnFiltrarBotao']")).click();
	}

}
