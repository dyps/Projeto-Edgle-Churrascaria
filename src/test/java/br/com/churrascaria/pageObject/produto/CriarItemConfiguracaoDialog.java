package br.com.churrascaria.pageObject.produto;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;

public class CriarItemConfiguracaoDialog extends AbstractPage {

	public CriarItemConfiguracaoDialog(WebDriver driver) {
		super(driver);
	}

	public CriarProdutoPersonalizadoPage confirmar() {
		$(By.xpath("//*[@id=\"formItem:botaoSubmetera\"]")).click();
		return new CriarProdutoPersonalizadoPage(driver);
	}
	
	public CriarItemConfiguracaoDialog setNome(String nome) {
		setInputText("formItem:idNome", nome);
		return this;
	}
	
	public CriarItemConfiguracaoDialog setQuantidadeMaxima(String quantidadeMaxima) {
		setInputText("formItem:idQtdEsc", quantidadeMaxima);
		return this;
	}

}
