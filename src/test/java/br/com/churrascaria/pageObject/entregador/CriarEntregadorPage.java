package br.com.churrascaria.pageObject.entregador;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;

public class CriarEntregadorPage extends AbstractPage {

	public CriarEntregadorPage(WebDriver driver) {
		super(driver);
	}
	
	public void confirmar() {
		$(By.xpath("//*[@id=\"formEdit:botaoSubmeterr\"]")).click();
	}
	
	public CriarEntregadorPage setNome(String nome) {
		setInputText("formEdit:nome", nome);
		return this;
	}
	
	public CriarEntregadorPage setTelefone(String telefone) {
		setInputText("formEdit:telefone", telefone);
		return this;
	}
	
	public CriarTaxaDialog novaTaxa() {
		$(By.xpath("//*[@id='formEdit:idNovaTaxa']")).click();
		return new CriarTaxaDialog(driver);
	}
	
	public ExcluirTaxaDialog apagarTaxa() {
		$(By.xpath("//*[@id='formEdit:taxaEntrega:0:idDeleteTaxa']")).click();
		return new ExcluirTaxaDialog(driver);
	}

}
