package br.com.churrascaria.pageObject.cliente;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;

public class CriarClientePage extends AbstractPage {

	public CriarClientePage(WebDriver driver) {
		super(driver);
	}

	public void confirmar() {
		$(By.xpath("//*[@id=\"formEdit:botaoSubmeterr\"]")).click();
	}

	public CriarClientePage setNome(String nome) {
		setInputText("formEdit:nome", nome);
		return this;
	}

	public CriarClientePage setTelefone(String telefone) {
		setInputText("formEdit:telefone", telefone);
		return this;
	}

	public CriarEnderecoDialog novoEndereco() {
		$(By.xpath("//*[@id='formEdit:novoEndereco']")).click();
		return new CriarEnderecoDialog(driver);
	}

	public ExcluirEnderecoDialog apagarEndereco() {
		$(By.xpath("//*[@id='formEdit:endereco:0:idDeleteEndereco']")).click();
		return new ExcluirEnderecoDialog(driver);
	}

}
