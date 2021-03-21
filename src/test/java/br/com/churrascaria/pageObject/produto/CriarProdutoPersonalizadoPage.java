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
		$(By.xpath("//*[@id='formEdit:j_idt58:0:j_idt69']")).click();
		return new CriarItemConfiguracaoDialog(driver);
	}
	
	public ExcluirItemConfiguracaoDialog excluirItemConfiguracao() {
		$(By.xpath("//*[@id='formEdit:j_idt58:0:j_idt71']")).click();
		return new ExcluirItemConfiguracaoDialog(driver);
	}
	
	public CriarOpcaoDialog novaOpcao() {
		$(By.xpath("//*[@id='formEdit:j_idt58_data']/tr[1]/td[1]/div")).click();
		$(By.xpath("//*[@id='formEdit:j_idt58:0:j_idt75']")).click();
		return new CriarOpcaoDialog(driver);
	}
	
	public CriarOpcaoDialog novaOpcao2() {
		$(By.xpath("//*[@id='formEdit:j_idt58_data']/tr[2]/td[1]/div")).click();
		$(By.xpath("//*[@id='formEdit:j_idt58:1:j_idt75']")).click();
		return new CriarOpcaoDialog(driver);
	}
	
	public ExcluirOpcaoDialog excluirOpcao() {
		$(By.xpath("//*[@id=\'formEdit:j_idt58_data\']/tr[2]/td[1]/div")).click();
		$(By.xpath("//*[@id='formEdit:j_idt58:1:j_idt78:0:j_idt84']")).click();
		return new ExcluirOpcaoDialog(driver);
	}

}
