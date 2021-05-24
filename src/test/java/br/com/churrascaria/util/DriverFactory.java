package br.com.churrascaria.util;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

public class DriverFactory {

	private static String PATH = "lib\\";

	public WebDriver getDriver(DriverType driverType) {
		switch (driverType) {
			
			case OPERAYAGGO: {
				System.setProperty("webdriver.opera.driver",
						"lib\\operadriver.exe");
						OperaOptions operaOptions = new OperaOptions();
						operaOptions.setBinary(new File("C:\\Users\\yaggo\\AppData\\Local\\Programs\\Opera GX\\73.0.3856.438\\opera.exe"));
						return new OperaDriver(operaOptions);}
			
			case OPERANIKSON: {
				System.setProperty("webdriver.opera.driver",
						PATH + "operadriver.exe");
						OperaOptions operaOptions = new OperaOptions();
						operaOptions.setBinary(new File("C:\\Users\\nikson\\AppData\\Local\\Programs\\Opera GX\\73.0.3856.415\\opera.exe"));
						return new OperaDriver(operaOptions);
			}
			default:
				throw new RuntimeException("Selenium Driver Type not found: " + driverType.name());
		}
	}

}
