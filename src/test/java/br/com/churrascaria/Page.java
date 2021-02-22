package br.com.churrascaria;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
private WebDriver driver;
	
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
//		System.setProperty("webdriver.gecko.driver", 
//				"lib\\geckodriver-v0.24.0-win64.exe");
//		driver = new FirefoxDriver();

//		System.setProperty("webdriver.edge.driver",
//				"lib\\MicrosoftWebDriver--18.exe");
//		driver = new EdgeDriver();
		
//		System.setProperty("webdriver.chrome.driver",
//				"lib\\chromedriver_win32--74.exe");
//		driver = new ChromeDriver();

		System.setProperty("webdriver.opera.driver",
		"lib\\operadriver.exe");
		OperaOptions operaOptions = new OperaOptions();
		operaOptions.setBinary(new File("C:\\Users\\yaggo\\AppData\\Local\\Programs\\Opera GX\\73.0.3856.396\\opera.exe"));
//		WebDriverManager.operadriver().setup();
		driver = new OperaDriver(operaOptions);
		
		baseUrl = "http://www.google.com.br/";
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void buscaMostraPalavraProcuradaNosResultados() {

		driver.get(baseUrl);

		WebElement campoDeTexto = driver.findElement(By.name("q"));
		campoDeTexto.sendKeys("IFPB");
		campoDeTexto.submit();

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.titleContains("IFPB"));

		assertEquals("IFPB - Pesquisa Google", driver.getTitle());
	}

}