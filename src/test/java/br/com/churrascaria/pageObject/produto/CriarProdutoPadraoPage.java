package br.com.churrascaria.pageObject.produto;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;

public class CriarProdutoPadraoPage extends AbstractPage {

	public CriarProdutoPadraoPage(WebDriver driver) {
		super(driver);
	}
	
	public void confirmar() {
		$(By.xpath("//*[@id=\"formEdit:botaoSubmeter\"]")).click();
	}
	
	public CriarProdutoPadraoPage setNome(String nome) {
		setInputText("formEdit:nome", nome);
		return this;
	}
		
	public CriarProdutoPadraoPage setPrecoDeVenda(String precoDeVenda) {
		setInputText("formEdit:preco_input", precoDeVenda);
		return this;
	}
	
	public CriarProdutoPadraoPage setUnidadeDeMedida() {
		$(By.xpath("//*[@id=\'formEdit:j_idt66\']/tbody/tr/td[2]/label")).click();
		return this;
	}
	
}
