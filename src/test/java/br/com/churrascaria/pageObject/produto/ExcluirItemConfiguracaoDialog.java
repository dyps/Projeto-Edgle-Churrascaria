package br.com.churrascaria.pageObject.produto;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;

public class ExcluirItemConfiguracaoDialog extends AbstractPage {

	public ExcluirItemConfiguracaoDialog(WebDriver driver) {
		super(driver);
	}
	
	public CriarProdutoPersonalizadoPage confirmar() {
		$(By.xpath("//*[@id=\"j_idt44:confirmYes\"]")).click();
		return new CriarProdutoPersonalizadoPage(driver);
	}

}
