package com.nopcommerce.TestCase;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;


import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

//@Listeners(commons.MethodListener.class)
public class Level_16_Share_Data_B extends BaseTest{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	private UserRegisterPageObject registerPage;
	private UserHomePageObject homePage; 
	private String firstName , lastName ,passWord ,email;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		
driver = getBrowserDriver(browserName);
		
		driver.get("https://demo.nopcommerce.com/");
		homePage = PageGeneratorManager.getUserHomePageObject(driver);
				
		firstName 	= "khang" + random();
		lastName	= "tesst "+random();
		email 		= "khang"+random()+"@gmasidl.com";
		passWord 	= "ASd1651c61616515";
		
		log.info("Pre-Condition - Step 01: Navigate to register page");
		registerPage = homePage.clickToRegisterLink();
		
		log.info("Pre-Condition - Step 02: Enter to firtname textbox with value is '"+lastName+"'");
		registerPage.inputFirstNameTextbox(firstName);
		
		log.info("Pre-Condition - Step 03: Enter to lastName textbox with value is '"+lastName+"'");
		registerPage.inputLastNameTextbox(lastName);
		
		log.info("Pre-Condition - Step 04: Enter to Email textbox with value is '"+email+"'");
		registerPage.inputEmailTextbox(email);
		
		log.info("Pre-Condition - Step 05: Enter to passWord textbox with value is '"+passWord+"'");
		registerPage.inputPasswordTextbox(passWord);
		
		log.info("Pre-Condition - Step 06: Enter to confirmPassWord textbox with value is '"+passWord+"'");
		registerPage.inputConfirmPasswordTextbox(passWord);
		
		log.info("Pre-Condition - Step 08: Click register button");
		registerPage.clickToRegisterButton();
		verifyEquals(registerPage.getSuccessMessage(),"Your registration completed");
		
		log.info("Login-Step 01: Navigate to Login page");
		loginPage = homePage.openLoginPage();
		
		log.info("Login-Step 02: Enter to mail textbox with value is '"+email+"'");
		loginPage.inputToEmailTextbox(email);
		
		log.info("Login-Step 03: Enter to pass textbox with value is '"+passWord+"'");
		loginPage.inputToPassWordTextbox(passWord);
		
		log.info("Login-Step 04: Click to login button");
		homePage = loginPage.clickToLoginButton();
	}
	
	@Test
	public void TC_02_Login() {
		log.info("Login-Step 05: Verify acccount login success");
		Assert.assertEquals(homePage.getTextMyAccount(), "My account");
		customerInforPage = homePage.openMyAccountPage();
		verifyTrue(customerInforPage.getTextMyAccount().contains("My account"));
	}
	
	@Test
	public void Search_01_Empty_Data() {
		
	}
	@Test
	public void Search_02_Relative_Product_Name() {
		
	}
	@Test
	public void Search_03_Alsolute_Product_Name() {
		
	}
	@Test
	public void Search_04_Parent_Category() {
		
	}
	@Test
	public void Search_05_Incorrect_Manufactorer() {
		
	}
	@Test
	public void Search_06_Correct_Manufactorer() {
		
	}

	public int random() {
		Random random = new Random();
		return random.nextInt(99999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}