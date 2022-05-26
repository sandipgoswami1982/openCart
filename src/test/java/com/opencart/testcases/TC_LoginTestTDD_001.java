package com.opencart.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.pageobjects.LoginPage;
import com.opencart.utilities.XlUtil;

public class TC_LoginTestTDD_001 extends Baseclass
{

	@Test(dataProvider = "Logindata")
	public void loginTest(String un, String ps) throws IOException
	{
		driver.get(baseurl);
		logger.info("url is opened..........");
		LoginPage lp = new LoginPage(driver);

		lp.clickAccount();
		lp.selectLoginAction();

		lp.setEmail(un);
		logger.info("email is provided............");
		lp.setPasword(ps);
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

	@DataProvider(name = "Logindata")
	public String[][] getdata() throws IOException
	{
		String filepath = System.getProperty("user.dir") + "/src/test/java/com/opencart/testData/LoginData.xlsx";
		int noRow = XlUtil.getRowCount(filepath, "Sheet1");
		System.out.println(noRow);

		int noCell = XlUtil.getCellcount(filepath, "Sheet1", 1);
		System.out.println(noCell);

		String[][] logindata = new String[noRow][noCell];
		for (int r = 1; r <= noRow; r++)
		{
			for (int c = 0; c < noCell; c++)
			{
				logindata[r - 1][c] = XlUtil.getCellData(filepath, "Sheet1", r, c);
			}
		}
		return logindata;

	}
}
