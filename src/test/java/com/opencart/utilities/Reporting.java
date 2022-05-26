package com.opencart.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter
{
	public ExtentHtmlReporter htmlreporter;
	public ExtentReports extent;
	public ExtentTest logger;

	@Override
	public void onStart(ITestContext testContext)
	{
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String reponame = "Test-Report-" + timestamp + ".html";

		htmlreporter = new ExtentHtmlReporter(".//test-output//" + reponame);
		//htmlreporter.loadConfig(".//extent-config.xml");

		extent = new ExtentReports();
		extent.attachReporter(htmlreporter);

		extent.setSystemInfo("TesterName", "Sandip");
		extent.setSystemInfo("OS", "Window");
		extent.setSystemInfo("Browser", "chrome");

		htmlreporter.config().setDocumentTitle("Automation Report");
		htmlreporter.config().setReportName("Functional Testing");

		htmlreporter.config().setTheme(Theme.DARK);

	}

	@Override
	public void onTestSuccess(ITestResult tr)
	{
		logger = extent.createTest(tr.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.AMBER));
	}

	@Override
	public void onTestFailure(ITestResult tr)
	{
		logger = extent.createTest(tr.getName());
		logger.log(Status.FAIL, tr.getName());
		
		String screenshotPath=System.getProperty("user.dir")+"//screenshots//"+tr.getName()+".png";

		File f = new File(screenshotPath);
		System.out.println(f.exists());

		if (f.exists())
		{
			try
			{
			 logger.addScreenCaptureFromPath(screenshotPath);
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				System.out.println(e.getCause()); 
			}
		}

	}

	@Override
	public void onTestSkipped(ITestResult tr)
	{
		logger = extent.createTest(tr.getName());
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));

	}

	@Override
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}

	@Override
	public void onTestStart(ITestResult result)
	{
		
	}

}
