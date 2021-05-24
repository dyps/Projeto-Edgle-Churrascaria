package br.com.churrascaria.pageObject.pedido;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;

public class MarcarProdutoEntregueDialog extends AbstractPage {

	public MarcarProdutoEntregueDialog(WebDriver driver) {
		super(driver);
	}

	public CriarPedidoTipoMesaPage confirmar() {
		$(By.xpath("//*[@id=\'j_idt45:confirmYes\']/span[1]")).click();
		return new CriarPedidoTipoMesaPage(driver);
	}

}
