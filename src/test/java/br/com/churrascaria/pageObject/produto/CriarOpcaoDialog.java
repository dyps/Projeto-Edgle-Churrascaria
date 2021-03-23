package br.com.churrascaria.pageObject.produto;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;

public class CriarOpcaoDialog extends AbstractPage {

	public CriarOpcaoDialog(WebDriver driver) {
		super(driver);
	}

	public CriarProdutoPersonalizadoPage confirmar() {
		$(By.xpath("//*[@id=\"formOpc:botaoSubmeteraOpc\"]")).click();
		return new CriarProdutoPersonalizadoPage(driver);
	}

	public CriarOpcaoDialog setNome(String nome) {
		setInputText("formOpc:idNomeOp", nome);
		return this;
	}

	public CriarOpcaoDialog setValorVenda(String valorVenda) {
		setInputText("formOpc:idValorVenda_input", valorVenda);
		return this;
	}

}
