package br.com.churrascaria.pageObject.observacaoPadrao;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;

public class ObservacaoPadraoPage extends AbstractPage {
	
	private String urlPage = "http://127.0.0.1:8080/EdgleChurrascaria/paginas/protegidas/catalogo/observacoes/index.xhtml";

	public ObservacaoPadraoPage(WebDriver driver) {
		super(driver);
	}
	
	public void visita() {
		// visitar a página principal observacoes padrao
		open(urlPage);
	}
	
	public CriarObservacaoPadraoDialog nova() {
		$(By.xpath("//*[@id='formFilter:idNovaObservacao']")).click();
		return new CriarObservacaoPadraoDialog(driver);
	}
	
	public ExcluirObservacaoPadraoDialog apagar() {
		$(By.xpath("//*[@id='formFilter:j_idt48:0:deleteObservacao']")).click();
		return new ExcluirObservacaoPadraoDialog(driver);
	}

}
