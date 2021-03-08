package br.com.churrascaria.pageObject.entregador;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;

public class ExcluirTaxaDialog extends AbstractPage {

	public ExcluirTaxaDialog(WebDriver driver) {
		super(driver);
	}

	public CriarEntregadorPage confirmar() {
		$(By.xpath("//*[@id=\"j_idt43:confirmYes\"]")).click();
		return new CriarEntregadorPage(driver);
	}

}
