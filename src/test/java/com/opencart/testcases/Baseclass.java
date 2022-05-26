package com.opencart.testcases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


import com.opencart.utilities.Readconfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Baseclass
{
	Readconfig Readconfig = new Readconfig();
	public static WebDriver driver;
	String baseurl = Readconfig.getApplicationUrl();
	String userEmail = Readconfig.getEmail();
	String userpassword = Readconfig.getpassword();
	public static Logger logger;

	@BeforeClass
	@Parameters(
	{ "Browser" })
	public void setup(String brw)
	{
		if (brw.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//Drivers/chromedriver.exe" );
			driver = new ChromeDriver();
		} else if (brw.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"//Drivers/geckodriver.exe" );
			driver = new FirefoxDriver();
		} else if (brw.equals("edge"))
		{
			System.setProperty("webdriver.edge.driver",System.getProperty("user.dir")+"//Drivers/msedgedriver.exe" );
			driver = new EdgeDriver();
		} else
		{
			logger.info("invalid browser selection");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		logger = Logger.getLogger("opencart");
		PropertyConfigurator.configure("log4j.properties");

	}

	@AfterClass
	public void tearDown()
	{
		driver.quit();

	}
	
	public  void captureScreenshot(WebDriver driver,String tname) throws IOException
	{
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot ts=(TakesScreenshot)driver;
		
		File src=ts.getScreenshotAs(OutputType.FILE);
		
		File trg=new File(".\\screenshots\\"+tname+timestamp+".png");
		
		FileUtils.copyFile(src, trg);
		
	}

}
