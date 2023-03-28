package com.HRM.PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.HRM.Reports.ExtentReport;
import com.HRM.config.BaseClass;
import com.HRM.utils.Util;

public class LoginPage {
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	String expTitle = "OrangeHRM";
	
	By input_username = By.name("username");
	By input_password = By.name("password");
	By btn_Login = By.xpath("//button[@type='submit']");
	
	public void isPageOpened() {
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, expTitle);
		ExtentReport.passTest(driver, "Login Page launched succesfully!!!!!!!!!!");
		
	}

	
	public void enterUserName(String username) {
		Util.sendKeys(driver, input_username, username, "User Name");
	}
	
	public void enterPassword(String password) {
		Util.sendKeys(driver, input_password, password, "Password");
	}
	
	public void clickOnLogin() {
		Util.click(driver, btn_Login, "Login");
	}
	
}
