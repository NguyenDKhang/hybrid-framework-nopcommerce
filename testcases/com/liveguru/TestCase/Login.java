package com.liveguru.TestCase;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.liveGuru.PageGeneratorManager;
import pageObject.liveGuru.PageHomeObject;
import pageObject.liveGuru.PageLoginObject;
import pageObject.liveGuru.PageRegisterObject;

public class Login extends BaseTest{
	private WebDriver driver;
	private PageHomeObject homePageObject;
	private PageRegisterObject registerPageObject;
	private PageLoginObject loginPageObject;
	private String email = random() +"@email.com";
	private String pass = "123456789";
	@Parameters ("browser")
	@BeforeClass
	public void beforeClass(String browser) {
		driver = getBrowserDriver(browser);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.techpanda.org/");
		
		homePageObject = PageGeneratorManager.getDriverPageHomeObject(driver);
		registerPageObject = homePageObject.clickToRegister();
		
		registerPageObject.inputToFirstName("asdfghj");
		registerPageObject.inputToLastName("asdfghj");
		registerPageObject.inputEmail(email);
		registerPageObject.inputPassWord(pass);
		registerPageObject.inputCofirmPassWord(pass);
		registerPageObject.clickToButtonRegister();
		
		Assert.assertEquals(homePageObject.getMessageSuccess(),"Thank you for registering with Main Website Store.");
		homePageObject.clickToLogout();
	}
	
	@Test
	public void Login() {
		loginPageObject = homePageObject.clickLogInt();
		
		loginPageObject.inputToEmail(email);
		loginPageObject.inputToPass(pass);
		loginPageObject.clickToLogin();
		Assert.assertTrue(homePageObject.getMessageMyDashboarh().contains("Hello,"));
	}
	
	private int random() {
		Random random = new Random();
		return random.nextInt(999999);
	}
	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
