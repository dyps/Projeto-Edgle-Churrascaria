package br.com.churrascaria.pageObject.cliente;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;

public class ClientePage extends AbstractPage {

	private String urlPage = "http://127.0.0.1:8080/EdgleChurrascaria/paginas/protegidas/vendas/clientes/index.xhtml";

	public ClientePage(WebDriver driver) {
		super(driver);
	}

	public void visita() {
		// visitar a página principal cliente
		open(urlPage);
	}

	public CriarClientePage nova() {
		$(By.xpath("//*[@id=\'formFilter:idNovoCliente']")).click();
		return new CriarClientePage(driver);
	}

	public CriarClientePage editar() {
		$(By.xpath("//*[@id=\'idEditarCliente']")).click();
		return new CriarClientePage(driver);
	}

	public ExcluirClienteDialog apagar() {
		$(By.xpath("//*[@id='deleteCliente']")).click();
		return new ExcluirClienteDialog(driver);
	}

	public void buscar(String nome) {
		setInputText("formFilter:itNomeFilter", nome);
		$(By.xpath("//*[@id='formFilter:btnFiltrarBotao']")).click();
	}

}
