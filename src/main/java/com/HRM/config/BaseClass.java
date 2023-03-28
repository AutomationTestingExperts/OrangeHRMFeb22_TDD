package com.HRM.config;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.HRM.Reports.ExtentReport;
import com.HRM.utils.Util;
import com.aventstack.extentreports.ExtentTest;


import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public WebDriver driver = null;
	public static ExtentTest report;
	String htmlPath = "", reportsPath = "";

	@BeforeSuite
	public void brforeSuite() {
		reportsPath = System.getProperty("user.dir") + "//Reports//" + Util.getFolderName();
	}

	@BeforeMethod
	public void brforeMethod(Method method) {
		
		htmlPath = reportsPath + "\\" + method.getName() + "_" + Util.getRandomNumWithCurrentDate() + ".html";
		report = ExtentReport.startReport(htmlPath, method.getName(), "");
		launchBrowser("chrome");
	}

	@AfterMethod
	public void afterMethod() {
		if(driver !=null) {
			driver.quit();
		}
		ExtentReport.getReporter(htmlPath).flush();
	}

	@AfterSuite
	public void afterSuite() {

	}
	
	
	public void launchBrowser(String browser) {
		System.out.println(System.getProperty("user.dir"));
		DesiredCapabilities cap = new DesiredCapabilities();
		if(browser.equalsIgnoreCase("chrome")) {	
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-extensions");
			options.addArguments("--start-maximized");
			options.addArguments("--disable-gpu");
			options.addArguments("--remote-allow-origins=*");
//			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers\\chromedriver.exe");
//			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
		}else if(browser.equalsIgnoreCase("firefox")) {
			
		}else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.get(Util.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ExtentReport.infoTest("Browser & application launched successfully!!!!!!!");
	}

}
