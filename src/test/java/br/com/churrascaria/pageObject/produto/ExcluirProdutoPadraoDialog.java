package br.com.churrascaria.pageObject.produto;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;

public class ExcluirProdutoPadraoDialog extends AbstractPage {

	public ExcluirProdutoPadraoDialog(WebDriver driver) {
		super(driver);
	}
	
	public void confirmar() {
		$(By.xpath("//*[@id=\"j_idt43:confirmYes\"]")).click();
	}

}
