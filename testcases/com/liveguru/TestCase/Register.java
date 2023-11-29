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
import pageObject.liveGuru.PageRegisterObject;

public class Register extends BaseTest{
	
	private WebDriver driver;
	private PageHomeObject homePageObject;
	private PageRegisterObject registerPageObject;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browser) {
		driver = getBrowserDriver(browser);
		driver.get("http://live.techpanda.org/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		homePageObject = PageGeneratorManager.getDriverPageHomeObject(driver);
	}
	
	@Test
	public void register() {
		registerPageObject = homePageObject.clickToRegister();
		
		registerPageObject.inputToFirstName("first name");
		registerPageObject.inputToLastName("last name");
		registerPageObject.inputEmail(random() + "@gmali.com");
		registerPageObject.inputPassWord("123456");
		registerPageObject.inputCofirmPassWord("123456");
		homePageObject = registerPageObject.clickToButtonRegister();
		
		Assert.assertEquals(homePageObject.getMessageSuccess(), "Thank you for registering with Main Website Store.");
		homePageObject.clickToLogout();
	}
	
	public int random() {
		Random random = new Random();
		return random.nextInt(9999965);
	}
	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
