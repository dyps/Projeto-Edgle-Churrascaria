package br.com.churrascaria.pageObject.cliente;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;

public class CriarEnderecoDialog extends AbstractPage {

	public CriarEnderecoDialog(WebDriver driver) {
		super(driver);
	}

	public CriarClientePage confirmar() {
		$(By.xpath("//*[@id=\"formEdit:botaoSubmeter\"]")).click();
		return new CriarClientePage(driver);
	}

	public CriarEnderecoDialog setNome(String nome) {
		setInputText("formEdit:idNome", nome);
		return this;
	}

	public CriarEnderecoDialog setLogradouro(String logradouro) {
		setInputText("formEdit:idLogradouro", logradouro);
		return this;
	}

	public CriarEnderecoDialog setNumero(String numero) {
		setInputText("formEdit:idNumero", numero);
		return this;
	}

	public CriarEnderecoDialog setComplemento(String complemento) {
		setInputText("formEdit:idComplemento", complemento);
		return this;
	}

}
