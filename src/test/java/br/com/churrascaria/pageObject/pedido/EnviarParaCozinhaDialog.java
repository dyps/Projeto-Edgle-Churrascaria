package br.com.churrascaria.pageObject.pedido;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;

public class EnviarParaCozinhaDialog extends AbstractPage {

	public EnviarParaCozinhaDialog(WebDriver driver) {
		super(driver);
	}

	public CriarPedidoTipoMesaPage confirmar() {
		$(By.xpath("//*[@id=\'formEnviarCozinha:botaoEnivar\']/span")).click();
		return new CriarPedidoTipoMesaPage(driver);
	}

	public EnviarParaCozinhaDialog selecionarProduto() {
		$(By.xpath("//*[@id=\'formEnviarCozinha:idListItensNEnv\']/div[3]/table/tbody/tr/td[1]/div/div/span")).click();
		return this;
	}

}
