package br.com.churrascaria.pageObject.mesa;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import br.com.churrascaria.pageObject.AbstractPage;

public class MesaPage extends AbstractPage {

	private String urlPage = "http://127.0.0.1:8080/EdgleChurrascaria/paginas/protegidas/gestao/mesas/index.xhtml";

	public MesaPage(WebDriver driver) {
		super(driver);
	}

	public void visita() {
		// visitar a página principal mesas
		open(urlPage);
	}

	public CriarMesaDialog nova() {
		$(By.xpath("//*[@id='idNovaMesa']")).click();
		return new CriarMesaDialog(driver);
	}
	
	public ExcluirMesaDialog apagar() {
		$(By.xpath("//*[@id='formFilter:dataTableMesas:1:deleteMesa']")).click();
		return new ExcluirMesaDialog(driver);
	}
	
	public void buscar(String numero) {
		setInputText("formFilter:itNumeroFilter", numero);
		$(By.xpath("//*[@id='formFilter:btnFiltrarBotao']")).click();
	}

	public List<Integer> mesasCadastradas() {
		List<Integer> retorno = new ArrayList<>();
		SelenideElement tabela = $(By.xpath("//*[@id=\"formFilter:dataTableMesas_content\"]/table"));
		ElementsCollection colunas = tabela.$$(By.xpath("//*[@class=\"ui-datagrid-column\"]"));

		for (SelenideElement selenideElement : colunas) {
			String numeroNoElemento = selenideElement.getText().replaceAll("\\\n", " ").replaceAll("Nova Mesa", " ")
					.replaceAll("Mesa ", " ").replaceAll("Excluir", " ").trim();
			if (!numeroNoElemento.equals(""))
				retorno.add(Integer.parseInt(numeroNoElemento));
		}
		return retorno;
	}

}
