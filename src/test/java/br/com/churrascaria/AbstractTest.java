package br.com.churrascaria;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.codeborne.selenide.WebDriverRunner;

import br.com.churrascaria.pageObject.cliente.ClientePage;
import br.com.churrascaria.pageObject.entregador.EntregadorPage;
import br.com.churrascaria.pageObject.funcionario.FuncionarioPage;
import br.com.churrascaria.pageObject.mesa.MesaPage;
import br.com.churrascaria.pageObject.observacaoPadrao.ObservacaoPadraoPage;
import br.com.churrascaria.pageObject.produto.ProdutoPage;
import br.com.churrascaria.util.DriverFactory;
import br.com.churrascaria.util.DriverType;

public abstract class AbstractTest {

	// XXX: Good read about Selenium implicit vs explicit waits:
	// 1. https://stackoverflow.com/a/28067495
	// 2.
	// http://www.seleniumhq.org/docs/04_webdriver_advanced.jsp#explicit-and-implicit-waits

	@Rule
	public TestWatcher screenShotRule = new TestWatcher() {

		@Override
		protected void failed(Throwable e, Description description) {
			String uniqueId = "" + System.nanoTime();
			String methodName = description.getMethodName();
			String fileName = uniqueId + "-" + description.getTestClass().getSimpleName() + "-" + methodName + ".png";

			try {
				File destiny = new File(fileName);
				FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), destiny);
			} catch (IOException ioe) {
				throw new RuntimeException(ioe);
			}
		}

		@Override
		protected void finished(Description description) {
			// XXX Comentada a linha abaixo, pois agora o navegador é aberto uma única vez e
			// não
			// a cada caso de teste
			// driver.quit();
		}
	};

	protected MesaPage mesasPage;
	
	protected ObservacaoPadraoPage observacaoPadraoPage;
	
	protected EntregadorPage entregadoresPage;
	
	protected FuncionarioPage funcionarioPage;
	
	protected ClientePage clientePage;
	
	protected ProdutoPage produtoPage;

	protected static DateFormat DATE_FORMAT;


	protected static WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		mesasPage = new MesaPage(driver);
		observacaoPadraoPage = new ObservacaoPadraoPage(driver);
		entregadoresPage = new EntregadorPage(driver);
		funcionarioPage = new FuncionarioPage(driver);
		clientePage = new ClientePage(driver);
		produtoPage = new ProdutoPage(driver);
	}


	@BeforeClass
	public static void setUpClass() throws Exception {
		DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
		driver = new DriverFactory().getDriver(DriverType.OPERAYAGGO);

		driver.manage().window().maximize();
		// XXX: Inicialização do WebDriver a ser utilizado pelo Selenide
		WebDriverRunner.setWebDriver(driver);
	}

	@AfterClass
	public static void afterClass() throws Exception {
		if (driver != null) {
			driver.quit();
		}
	}


}
