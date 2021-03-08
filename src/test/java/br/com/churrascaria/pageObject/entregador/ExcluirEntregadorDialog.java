package br.com.churrascaria.pageObject.entregador;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;

public class ExcluirEntregadorDialog extends AbstractPage {

	public ExcluirEntregadorDialog(WebDriver driver) {
		super(driver);
	}
	
	public void confirmar() {
		$(By.xpath("//*[@id=\"j_idt41:confirmYes\"]")).click();
	}

}
