package br.com.churrascaria.pageObject;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractPage {

	public static long TIMEOUT_IN_SECONDS = 15;

	protected WebDriver driver;

	public AbstractPage(WebDriver driver) {
		this.driver = driver;
	}

	protected String getIdCaixaMensagemErro() {
		return "idMessagesErrors";
	}

	protected String getIdGrowl(String id) {
		return id + "_container";
	}

	private String escapeColon(String id) {
		return id.replaceAll(":", "\\\\:");
	}

	protected void setInputText(String id, String value) {
		id = escapeColon(id);
		$("#" + id).setValue(value);
	}

	protected void setCalendar(String id, String value) {
		setInputText(id + "_input", value);
		$(".ui-datepicker-close").click();
	}

	protected void setSelectOneMenu(String id, String value) {
		$("#" + escapeColon(id) + "_label").click();

		WebElement opcaoWE = $(By.xpath(String.format("//div[@id='%s_panel']/div/ul/li[text()='%s']", id, value)));
		opcaoWE.click();
	}

	public boolean foiExibidaMensagemDeFalha(String... mensagens) {
		WebElement caixaMensagem = $("#" + getIdCaixaMensagemErro());
		String textoDaCaixaMensagem = caixaMensagem.getText();

		boolean mensagemExibida = true;
		for (String mensagem : mensagens) {
			mensagemExibida &= textoDaCaixaMensagem.contains(mensagem);
		}

		return mensagemExibida;
	}

	public boolean foiExibidaMensagemDeSucesso(String mensagem) {
		WebElement caixaMensagem = $("#" + getIdGrowl("idMessages"));
		String textoDaCaixaMensagem = caixaMensagem.getText();
		return textoDaCaixaMensagem.contains(mensagem);
	}

	public String getTitulo() {
		return driver.getTitle();
	}

//	public MenuWidget menu() {
//		return new MenuWidget(driver);
//	}

}
