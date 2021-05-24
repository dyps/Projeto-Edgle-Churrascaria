package br.com.churrascaria.pageObject.pedido;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.churrascaria.pageObject.AbstractPage;

public class PedidoPage extends AbstractPage {

	private String urlPage = "http://127.0.0.1:8080/EdgleChurrascaria/paginas/protegidas/vendas/pedidos/index.xhtml";

	public PedidoPage(WebDriver driver) {
		super(driver);
	}

	public void visita() {
		// visitar a página principal de pedidos
		open(urlPage);
	}

	public CriarPedidoTipoMesaPage novoPedidoMesa() {
		$(By.xpath("//*[@id='IdMesa1']")).click();
		return new CriarPedidoTipoMesaPage(driver);
	}

	public CriarPedidoTipoBalcaoPage novoPedidoBalcao() {
		$(By.xpath("//*[@id='novoPedidoBalcao']")).click();
		return new CriarPedidoTipoBalcaoPage(driver);
	}

	public CriarPedidoTipoEntregaPage novoPedidoEntrega() {
		$(By.xpath("//*[@id='novoPedidoEntrega']")).click();
		return new CriarPedidoTipoEntregaPage(driver);
	}
	
	public CriarPedidoTipoMesaPage verPedidoDaMesa() {
		$(By.xpath("//*[@id=\'tabView:j_idt43:dataTableMesas:0:idVerPedido\']/span")).click();
		return new CriarPedidoTipoMesaPage(driver);
	}
	
	public CriarPedidoTipoBalcaoPage verPedidoDoBalcao() {
		$(By.xpath("//*[@id=\'tabView:j_idt71:dataTableBalcao:1:idVerPedido\']/span")).click();
		return new CriarPedidoTipoBalcaoPage(driver);
	}

	public PedidoPage abaMesas() {
		$(By.xpath("//*[@id=\"tabView\"]/ul/li[1]/a")).click();
		return this;
	}

	public PedidoPage abaBalcao() {
		$(By.xpath("//*[@id=\"tabView\"]/ul/li[2]/a")).click();
		return this;
	}

	public PedidoPage abaEntrega() {
		$(By.xpath("//*[@id=\"tabView\"]/ul/li[3]/a")).click();
		return this;
	}

	public PedidoPage abaEncerrados() {
		$(By.xpath("//*[@id=\"tabView\"]/ul/li[4]")).click();
		return this;
	}

}
