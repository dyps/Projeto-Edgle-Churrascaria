package br.com.churrascaria.pageObject.produto;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;

public class CriarProdutoPersonalizadoPage extends AbstractPage {

	public CriarProdutoPersonalizadoPage(WebDriver driver) {
		super(driver);
	}
	
	public void confirmar() {
		$(By.xpath("//*[@id=\"formEdit:botaoSubmeter\"]")).click();
	}
	
	public CriarProdutoPersonalizadoPage setNome(String nome) {
		setInputText("formEdit:nome", nome);
		return this;
	}
	
	public CriarItemConfiguracaoDialog novoItemConfiguracao() {
		$(By.xpath("//*[@id='idNovoIte']")).click();
		return new CriarItemConfiguracaoDialog(driver);
	}
	
	public CriarItemConfiguracaoDialog editarItemConfiguracao() {
		$(By.xpath("//*[@id=\'formEdit:j_idt67:0:j_idt78\']/i")).click();
		return new CriarItemConfiguracaoDialog(driver);
	}
	
	public ExcluirItemConfiguracaoDialog excluirItemConfiguracao() {
		$(By.xpath("//*[@id='formEdit:j_idt67:0:j_idt80']")).click();
		return new ExcluirItemConfiguracaoDialog(driver);
	}
	
	public CriarOpcaoDialog novaOpcao() {
		$(By.xpath("//*[@id=\'formEdit:j_idt67_data\']/tr/td[1]/div")).click();
		$(By.xpath("//*[@id=\'formEdit:j_idt67:0:j_idt84\']/i")).click();
		return new CriarOpcaoDialog(driver);
	}
	
	public CriarOpcaoDialog novaOpcao2() {
		$(By.xpath("//*[@id='formEdit:j_idt67_data']/tr[2]/td[1]/div")).click();
		$(By.xpath("//*[@id='formEdit:j_idt67:1:j_idt84']")).click();
		return new CriarOpcaoDialog(driver);
	}
	
	public ExcluirOpcaoDialog excluirOpcao() {
		$(By.xpath("//*[@id=\'formEdit:j_idt67_data\']/tr[2]/td[1]/div")).click();
		$(By.xpath("//*[@id=\'formEdit:j_idt67:1:j_idt87:0:j_idt93\']/i")).click();
		return new ExcluirOpcaoDialog(driver);
	}

}
