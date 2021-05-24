package br.com.churrascaria.pageObject.pedido;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;

public class CriarPedidoTipoBalcaoPage extends AbstractPage {

	public CriarPedidoTipoBalcaoPage(WebDriver driver) {
		super(driver);
	}
	
	public CriarPedidoTipoBalcaoPage setObservacao(String observacao) {
		setInputText("idFormObservacao:idObservacao", observacao);
		return this;
	}
	
	public CriarPedidoTipoBalcaoPage selecionarCategoriaDoProduto() {
		$(By.xpath("//*[@id=\'idformescolhacat:idListCat\']/div[3]/table/tbody/tr/td/span")).click();
		return this;
	}
	
	public DetalhesDoProdutoDialog selecionarProduto() {
		$(By.xpath("//*[@id=\'idformescolhacat:IdPainelProdutos:0:j_idt96\']/i")).click();
		return new DetalhesDoProdutoDialog(driver);
	}
	
	public MarcarProdutoEntregueDialog marcarProdutoComoEntregue() {
		$(By.xpath("//*[@id=\'formListItens:IdPainelItenss:0:j_idt72\']/i")).click();
		return new MarcarProdutoEntregueDialog(driver);
	}
	
	public EnviarParaCozinhaDialog enviarProdutoParaCozinha(){
		$(By.xpath("//*[@id=\'j_idt101:botaoEnviarPCoz\']/span")).click();
		return new EnviarParaCozinhaDialog(driver);
	}
	
	public PedidoPage finalizarPedido() {
		$(By.xpath("//*[@id=\'j_idt101:j_idt106\']/span")).click();
		return new PedidoPage(driver);
	}
	
	public DetalhesDoItemDialog verDetalhesDoItem() {
		$(By.xpath("//*[@id=\'formListItens:IdPainelItenss:0:j_idt70\']/i")).click();
		return new DetalhesDoItemDialog(driver);
	}
	
	public CriarPedidoTipoBalcaoPage cancelarItem() {
		$(By.xpath("//*[@id=\'formListItens:IdPainelItenss:1:j_idt75\']/i")).click();
		return this;
	}

}
