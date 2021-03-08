package br.com.churrascaria.pageObject.cliente;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;

public class ExcluirEnderecoDialog extends AbstractPage {

	public ExcluirEnderecoDialog(WebDriver driver) {
		super(driver);
	}
	
	public CriarClientePage confirmar() {
		$(By.xpath("//*[@id=\"j_idt43:confirmYes\"]")).click();
		return new CriarClientePage(driver);
	}

}
