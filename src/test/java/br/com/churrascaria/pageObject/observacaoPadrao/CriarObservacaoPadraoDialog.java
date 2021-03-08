package br.com.churrascaria.pageObject.observacaoPadrao;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;

public class CriarObservacaoPadraoDialog extends AbstractPage {

	public CriarObservacaoPadraoDialog(WebDriver driver) {
		super(driver);
	}
	
	public void confirmar() {
		$(By.xpath("//*[@id=\"formFilter:botaoSubmeter\"]")).click();
	}
	
	public CriarObservacaoPadraoDialog setDescricao(String novaDescricao) {
		setInputText("formFilter:idDescrição", novaDescricao);
		return this;
	}

}
