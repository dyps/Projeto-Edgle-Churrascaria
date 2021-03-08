package br.com.churrascaria.pageObject.funcionario;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;

public class FuncionarioPage extends AbstractPage {

	private String urlPage = "http://127.0.0.1:8080/EdgleChurrascaria/paginas/protegidas/pessoas/funcionario/index.xhtml";

	public FuncionarioPage(WebDriver driver) {
		super(driver);
	}
	
	public void visita() {
		// visitar a página principal funcionario
		open(urlPage);
	}
	
	public CriarFuncionarioPage nova() {
		$(By.xpath("//*[@id=\'idNovoFuncionario']")).click();
		return new CriarFuncionarioPage(driver);
	}
	
	public CriarFuncionarioPage editar() {
		$(By.xpath("//*[@id=\'formFilter:dataTableFuncionarios:1:idEditarFuncionario']")).click();
		return new CriarFuncionarioPage(driver);
	}
	
	public ExcluirFuncionarioDialog apagar() {
		$(By.xpath("//*[@id='formFilter:dataTableFuncionarios:2:deleteFunc']")).click();
		return new ExcluirFuncionarioDialog(driver);
	}
	
	public void buscar(String nome) {
		setInputText("formFilter:itNomeFilter", nome);
		$(By.xpath("//*[@id='formFilter:btnFiltrarBotao']")).click();
	}

}
