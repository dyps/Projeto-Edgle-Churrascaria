package br.com.churrascaria.pageObject.produto;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;

public class CriarCategoriaProdutoDialog extends AbstractPage {

	public CriarCategoriaProdutoDialog(WebDriver driver) {
		super(driver);
	}

	public void confirmar() {
		$(By.xpath("//*[@id=\"formFilter:botaoSubmeter\"]")).click();
	}

	public CriarCategoriaProdutoDialog setNome(String novoNome) {
		setInputText("formFilter:idNome", novoNome);
		return this;
	}

}
