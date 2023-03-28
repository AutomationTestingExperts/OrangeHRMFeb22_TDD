package com.HRM.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.HRM.Reports.ExtentReport;
import com.HRM.utils.Util;

public class HomePage {
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	By header = By.xpath("//h6");
	
	String expTitle = "Dashboard";
	public void isPageOpened() {
		
		String actTitle = Util.getText(driver, header);
		Assert.assertEquals(actTitle, expTitle);
		ExtentReport.passTest(driver, "Home Page launched succesfully!!!!!!!!!!");
		
	}
	
	

}
