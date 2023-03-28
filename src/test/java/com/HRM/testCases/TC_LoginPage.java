package com.HRM.testCases;

import org.testng.annotations.Test;

import com.HRM.PageObjects.HomePage;
import com.HRM.PageObjects.LoginPage;
import com.HRM.Reports.ExtentReport;
import com.HRM.config.BaseClass;
import com.HRM.utils.Util;

public class TC_LoginPage extends BaseClass {
	
	@Test
	public void validateLoginFunctionality() {
		try {
			LoginPage log = new LoginPage(driver);
			log.isPageOpened();
			log.enterUserName(Util.getProperty("username"));
			log.enterPassword(Util.getProperty("password"));
			log.clickOnLogin();
			HomePage home = new HomePage(driver);
			home.isPageOpened();
		}catch (Exception e) {
			ExtentReport.failTest(driver, "Failed due to : "+e.getMessage());
		}
	}
	
	@Test
	public void validateLoginFunctionality2() {
		try {
			LoginPage log = new LoginPage(driver);
			log.isPageOpened();
			log.enterUserName("Admin34543534");
			log.enterPassword("admin123");
			log.clickOnLogin();
			HomePage home = new HomePage(driver);
			home.isPageOpened();
		}catch (Exception e) {
			ExtentReport.failTest(driver, "Failed due to : "+e.getMessage());
		}
	}

}
