package com.qa.baseclass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.implementations.SampleImplementation;
import com.qa.utils.ExcelUtility;

public class BaseTest {
	private static WebDriverWait webdriverWait;
	private static ExtentReports reportInstance;
	private static ExtentHtmlReporter htmlReporter;

	@BeforeTest(alwaysRun = true)
	public void setupTest() throws Exception {
		setReport();
		ExcelUtility.setExcelFile(System.getProperty("user.dir") + "/Testdata.xlsx", "Sheet1");
	}

	public void setReport() {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/reports/test-report.html");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Functional Execution Report");
		htmlReporter.config().setTheme(Theme.DARK);
		reportInstance = new ExtentReports();
		reportInstance.attachReporter(htmlReporter);
	}

	@Parameters({ "browser", "url", "wait"})
	@BeforeMethod(alwaysRun = true)
	public void setupDriver(String browser, String url, long waitTimeout) throws MalformedURLException {
		System.setProperty("webdriver.chrome.silentOutput", "true");
		DriverFactory.getInstance().setDriver(browser);
		setWait(getDriver(), waitTimeout);
		getDriver().manage().window().maximize();
		getDriver().get(url);
	}

	public void setWait(WebDriver driver, long waitTimeout) {
		webdriverWait = new WebDriverWait(driver, waitTimeout);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public WebDriver getDriver() {
		return DriverFactory.getInstance().getDriver();
	}

	public static ExtentReports getReportInstance() {
		return reportInstance;
	}

	public WebDriverWait getWait() {
		return webdriverWait;
	}

	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		getDriver().quit();
	}

	@AfterTest(alwaysRun = true)
	public void tearDownReport() {
		reportInstance.flush();
	}
}