package br.com.churrascaria.pageObject.mesa;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;

public class ExcluirMesaDialog extends AbstractPage {

	public ExcluirMesaDialog(WebDriver driver) {
		super(driver);
	}
	
	public void confirmar() {
		$(By.xpath("//*[@id=\"j_idt41:confirmYes\"]")).click();
	}

}
