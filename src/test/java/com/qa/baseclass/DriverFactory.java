package com.qa.baseclass;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {
	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	private DriverFactory() {

	}

	private static DriverFactory instance = new DriverFactory();

	public static DriverFactory getInstance() {
		return instance;
	}

	// public void setDriver(WebDriver driverObj) {
	// driver.set(driverObj);
	// }
	//
	// public WebDriver getDriver() {
	// return driver.get();
	// }
	//
	public void removeDriver() {
		driver.get().quit();
		driver.remove();
	}

	public void setDriver(String browser)
			throws MalformedURLException {
		DesiredCapabilities capability = new DesiredCapabilities();
			if (browser.toLowerCase().equals("chrome")) {
				capability.setCapability(BrowserType.CHROME, true);
				driver.set(new ChromeDriver());
		}
	}

	public WebDriver getDriver() {
		return driver.get();
	}
}