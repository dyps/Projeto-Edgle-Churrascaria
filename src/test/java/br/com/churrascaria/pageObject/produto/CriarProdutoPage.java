package br.com.churrascaria.pageObject.produto;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;

public class CriarProdutoPage extends AbstractPage {

	public CriarProdutoPage(WebDriver driver) {
		super(driver);
	}

	public CriarProdutoPadraoPage novoProdutoPadrao() {
		$(By.xpath("//*[@id='j_idt44:idNovoProdutoPadrao']")).click();
		return new CriarProdutoPadraoPage(driver);
	}

	public CriarProdutoPersonalizadoPage novoProdutoPersonalizado() {
		$(By.xpath("//*[@id='j_idt44:idNovoProdutoPersonalizado']")).click();
		return new CriarProdutoPersonalizadoPage(driver);
	}

	public CriarProdutoPadraoPage editarProdutoPadrao() {
		$(By.xpath("//*[@id=\'j_idt44:tabView:dataTablePPad:0:idAbrirProdutoPadrao']")).click();
		return new CriarProdutoPadraoPage(driver);
	}

	public CriarProdutoPersonalizadoPage editarProdutoPersonalizado() {
		$(By.xpath("//*[@id=\'j_idt44:tabView:dataTablePPreso:0:idAbrirProdutoPersonalizado']")).click();
		return new CriarProdutoPersonalizadoPage(driver);
	}

	public ExcluirProdutoPadraoDialog apagarProdutoPadrao() {
		$(By.xpath("//*[@id='j_idt44:tabView:dataTablePPad:0:deleteProdutoPadrao']")).click();
		return new ExcluirProdutoPadraoDialog(driver);
	}

	public ExcluirProdutoPersonalizadoDialog apagarProdutoPersonalizado() {
		$(By.xpath("//*[@id='j_idt44:tabView:dataTablePPreso:0:deleteProdutoPersonalizado']")).click();
		return new ExcluirProdutoPersonalizadoDialog(driver);
	}

	public void buscar(String nome) {
		setInputText("j_idt44:itNomeFilter", nome);
		$(By.xpath("//*[@id='j_idt44:btnFiltrarBotao']")).click();
	}

	public CriarProdutoPage abaProdutoPadrao() {
		$(By.xpath("//*[@id=\'j_idt44:tabView\']/ul/li[1]/a")).click();
		return this;
	}

	public CriarProdutoPage abaProdutoPersonalizado() {
		$(By.xpath("//*[@id=\'j_idt44:tabView\']/ul/li[2]/a")).click();
		return this;
	}

}
