package com.opencart.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencart.pageobjects.LoginPage;

public class TC_LoginTest_001 extends Baseclass
{

	@Test
	public void loginTest() throws IOException
	{
		driver.get(baseurl);
		logger.info("url is opened..........");
		LoginPage lp = new LoginPage(driver);

		lp.clickAccount();
		lp.selectLoginAction();

		lp.setEmail(userEmail);
		logger.info("email is provided............");
		lp.setPasword(userpassword);
		logger.info("password is provided......");
		lp.clickLogin();

		if (driver.getTitle().equals("My Account"))
		{
			logger.info("login is successful.........");
			lp.clickAccount();
			lp.clickLogout();
			Assert.assertTrue(true);
			logger.info("logout successfully...........");
		} else
		{
			captureScreenshot(driver, "TC_LoginTest_001");
			logger.error("Login is Failed");
			Assert.assertTrue(false);
			
		}
	}
}
