package br.com.churrascaria.pageObject.funcionario;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;

public class CriarFuncionarioPage extends AbstractPage {

	public CriarFuncionarioPage(WebDriver driver) {
		super(driver);
	}
	
	public void confirmar() {
		$(By.xpath("//*[@id=\"formEdit:botaoSubmeter\"]")).click();
	}
	
	public CriarFuncionarioPage setNome(String nome) {
		setInputText("formEdit:nome", nome);
		return this;
	}
	
	public CriarFuncionarioPage setLogin(String login) {
		setInputText("formEdit:login", login);
		return this;
	}
	
	public CriarFuncionarioPage setSenha(String senha) {
		setInputText("formEdit:password", senha);
		return this;
	}
	
	public CriarFuncionarioPage setConfirmarSenha(String confirmarSenha) {
		setInputText("formEdit:confirmacaoPassword", confirmarSenha);
		return this;
	}
	
	public CriarFuncionarioPage setTipoFuncionario() {
		$(By.xpath("//*[@id='formEdit:tipoDeFuncionario']")).click();
		$(By.xpath("//*[@id='formEdit:tipoDeFuncionario_1']")).click();
		return this;
	}

}
