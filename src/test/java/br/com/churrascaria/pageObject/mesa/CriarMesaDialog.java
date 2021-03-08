package br.com.churrascaria.pageObject.mesa;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;


public class CriarMesaDialog extends AbstractPage {

	public CriarMesaDialog(WebDriver driver) {
		super(driver);
	}
	
	public void confirmar() {
		$(By.xpath("//*[@id=\"formFilter:botaoSubmeter\"]")).click();
	}

	public CriarMesaDialog setNumero(String novoFirstName) {
		setInputText("formFilter:idNumero", novoFirstName);
		return this;
	}

}
