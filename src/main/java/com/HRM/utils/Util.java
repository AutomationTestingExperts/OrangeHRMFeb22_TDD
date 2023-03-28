package com.HRM.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.HRM.Reports.ExtentReport;


public class Util {
	
	public static void sendKeys(WebDriver driver, By locator, String input, String desc) {
		WebDriverWait owait = new WebDriverWait(driver, Duration.ofSeconds(10));
		owait.until(ExpectedConditions.presenceOfElementLocated(locator)).sendKeys(input);
		ExtentReport.infoTest("User enters : "+input+" into "+desc);
	}

	public static void click(WebDriver driver, By locator, String desc) {
		WebDriverWait owait = new WebDriverWait(driver, Duration.ofSeconds(10));
		owait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		ExtentReport.infoTest("User clicks on "+desc);
	}
	
	public static String getText(WebDriver driver, By locator) {
		WebDriverWait owait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String text = owait.until(ExpectedConditions.presenceOfElementLocated(locator)).getText();
		return text;
	}
	
	public static String captureScreenShot(WebDriver driver)  {
		String dest="";
		try {
		TakesScreenshot screen =  (TakesScreenshot) driver;
		File src = screen.getScreenshotAs(OutputType.FILE);
		 dest = System.getProperty("user.dir")+"\\Reports\\ScreenShots\\"+getRandomNumWithCurrentDate()+".png";
		File target = new File(dest);
		FileUtils.copyFile(src, target);
		}catch (Exception e) {
			ExtentReport.failTest("Failed to take screenshot");
		}
		return dest;
	}
	
	
	public static String getRandomNumWithCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		String format = dateFormat.format(new Date());
		String formatNew = format.replaceAll("[^0-9]", "");
		formatNew = formatNew.replace(":", "");
		formatNew = formatNew.replace("-", "");
		return formatNew;
	}
	
	public static String getFolderName() {
		int MyDay = LocalDateTime.now().getDayOfMonth(); // dd
		int MyYear = LocalDateTime.now().getYear(); // yyyy
		int MyMonth = LocalDateTime.now().getMonthValue(); // yyyy
		final String CureentDtTm = (String.valueOf(MyMonth) + "_" + String.valueOf(MyDay) + "_"
				+ String.valueOf(MyYear));
		return CureentDtTm;
	}
	
	public static String getProperty(String key)  {
		String value = "";
		try {
			File file = new File(System.getProperty("user.dir")+"\\config.properties");
			FileInputStream fi = new FileInputStream(file);
			Properties prop = new Properties();
			prop.load(fi);
			value = prop.getProperty(key);
			System.out.println(value);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

}
