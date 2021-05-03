package br.com.churrascaria.pageObject.pedido;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;

public class DetalhesDoProdutoDialog extends AbstractPage {

	public DetalhesDoProdutoDialog(WebDriver driver) {
		super(driver);
	}

	public CriarPedidoTipoMesaPage confirmar() {
		$(By.xpath("//*[@id=\'formProd:botaoadditem\']/span")).click();
		return new CriarPedidoTipoMesaPage(driver);
	}

	public DetalhesDoProdutoDialog adicionarObservacao() {
		$(By.xpath("//*[@id=\'formProd:idListObs\']/div[3]/table/tbody/tr/td[1]/div/div/span")).click();
		return this;
	}

	public DetalhesDoProdutoDialog setQuantidade(String quantidade) {
		setInputText("formProd:stepU_input", quantidade);
		return this;
	}

	public DetalhesDoProdutoDialog setQuantidade0() {
		$(By.xpath("//*[@id=\'formProd:stepU\']/a[2]/span/span")).click();
		return this;
	}

}
