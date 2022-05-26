package com.opencart.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage
{

	WebDriver ldriver;

	public LoginPage(WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement clkAccount;

	@FindBy(linkText = "Login")
	WebElement lnkLogin;

	@FindBy(id = "input-email")
	WebElement txtEmail;

	@FindBy(id = "input-password")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement btnLogin;

	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']")
	WebElement btnLogout;

	public void clickAccount()
	{
		clkAccount.click();
	}

	public void selectLoginAction()
	{
		lnkLogin.click();
	}

	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}

	public void setPasword(String password)
	{
		txtPassword.sendKeys(password);
	}

	public void clickLogin()
	{
		btnLogin.click();
	}

	public void clickLogout()
	{
		btnLogout.click();
	}

}
