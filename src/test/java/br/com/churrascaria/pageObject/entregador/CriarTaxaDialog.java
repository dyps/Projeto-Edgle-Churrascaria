package br.com.churrascaria.pageObject.entregador;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;

public class CriarTaxaDialog extends AbstractPage {

	public CriarTaxaDialog(WebDriver driver) {
		super(driver);
	}
	
	public CriarEntregadorPage confirmar() {
		$(By.xpath("//*[@id=\"formEdit:botaoSubmeter\"]")).click();
		return new CriarEntregadorPage(driver);
	}
	
	public CriarTaxaDialog setValor(String valor) {
		setInputText("formEdit:idValor", valor);
		return this;
	}
	
	public CriarTaxaDialog setDistancia(String distancia) {
		setInputText("formEdit:idDistanciaMaxima", distancia);
		return this;
	}

}
