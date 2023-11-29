package com.nopcommerce.common;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.PageGeneratorManager;


import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Common_01_Register_End_User extends BaseTest{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	private UserRegisterPageObject registerPage;
	private UserHomePageObject homePage; 
	private String firstName , lastName;
	public static String passWord ,email;
	
	@Parameters("browser")
	@BeforeTest(description = "Create new common User for all Classes Test")
	public void Register(String browserName) {
		driver = getBrowserDriver(browserName);
		
		driver.get("https://demo.nopcommerce.com/");
		homePage = PageGeneratorManager.getUserHomePageObject(driver);
				
		firstName 	= "khang" + random();
		lastName	= "tesst "+random();
		email 		= "khang"+random()+"@gmasidl.com";
		passWord 	= "ASd1651c61616515";
		
		log.info("Pre-Condition -Step 01: Navigate to register page");
		registerPage = homePage.clickToRegisterLink();
		
		log.info("Pre-Condition -Step 02: Enter to firtname textbox with value is '"+lastName+"'");
		registerPage.inputFirstNameTextbox(firstName);
		
		log.info("Pre-Condition -Step 03: Enter to lastName textbox with value is '"+lastName+"'");
		registerPage.inputLastNameTextbox(lastName);
		
		log.info("Pre-Condition -Step 04: Enter to Email textbox with value is '"+email+"'");
		registerPage.inputEmailTextbox(email);
		
		log.info("Pre-Condition -Step 05: Enter to passWord textbox with value is '"+passWord+"'");
		registerPage.inputPasswordTextbox(passWord);
		
		log.info("Pre-Condition -Step 06: Enter to confirmPassWord textbox with value is '"+passWord+"'");
		registerPage.inputConfirmPasswordTextbox(passWord);
		
		log.info("Pre-Condition -Step 08: Click register button");
		registerPage.clickToRegisterButton();
		verifyEquals(registerPage.getSuccessMessage(),"Your registration completed");
		
	}
	
	public int random() {
		Random random = new Random();
		return random.nextInt(99999);
	}

	@AfterTest
	public void afterClassTest() {
		driver.quit();
	}
}