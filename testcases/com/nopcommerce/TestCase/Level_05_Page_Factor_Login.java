package com.nopcommerce.TestCase;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageFactory.nopCommerce.HomePageFactory_Object;
import pageFactory.nopCommerce.LoginPageFactory_Object;
import pageFactory.nopCommerce.RegisterPageFactory_Object;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_05_Page_Factor_Login extends BaseTest {
  
	private WebDriver driver;
	private String firstName , lastName ,passWord ,confirmPassWord,existingEmail, 
	invalidEmail, notFoundEmail;
	
	private HomePageFactory_Object homePageFactory_Object; 
	private RegisterPageFactory_Object registerPageFactory_Object;
	private LoginPageFactory_Object loginPageFactory_Object;
	
	@Parameters ("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		
		driver = getBrowserDriver(browserName);
		// Initial (Khởi tạo)
		// Che dấu việc khởi tạo của đối tượng
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com");
		// Click logout thì bussines quay về trang home page
		homePageFactory_Object = new HomePageFactory_Object(driver);
				
		firstName ="khang" + random();
		lastName="tesst "+random();
		existingEmail = "khang"+random()+"@gmasidl.com";
		invalidEmail = "asdasd@asd@@gmasidl.com";
		notFoundEmail = "khang"+random()+"@gmasidl.com";
		passWord 			   	="ASd1651c61616515";
		confirmPassWord 	="ASd1651c61616515";
		
		// create account 
		registerPageFactory_Object = new RegisterPageFactory_Object(driver);
		
		homePageFactory_Object.clickToRegisterLink();
		registerPageFactory_Object.inputFirstNameTextbox(firstName);
		registerPageFactory_Object.inputLastNameTextbox(lastName);
		registerPageFactory_Object.inputEmailTextbox(existingEmail);
		registerPageFactory_Object.inputPasswordTextbox(passWord);
		registerPageFactory_Object.inputConfirmPasswordTextbox(confirmPassWord);
		
		 registerPageFactory_Object.clickToRegisterButton();
		
		Assert.assertEquals(registerPageFactory_Object.getSuccessMessage(),"Your registration completed");
	}

	@Test
	public void Login_01_Empty_Data() {
		homePageFactory_Object.clickToLoginLink();
		
		// Từ trang Home - Click Login link -> qua trang login
		loginPageFactory_Object = new LoginPageFactory_Object(driver);
		loginPageFactory_Object.clickToLoginButton();
		Assert.assertEquals(loginPageFactory_Object.getErrorMessageAtEmailTextBox(), "Please enter your email");
	}
	
	@Test
	public void Login_02_Invalid_Email() {
		homePageFactory_Object.clickToLoginLink();
		
		loginPageFactory_Object = new LoginPageFactory_Object(driver);
		loginPageFactory_Object.inputToEmailTextbox(invalidEmail);
		
		loginPageFactory_Object.clickToLoginButton();
		
		Assert.assertEquals(loginPageFactory_Object.getErrorMessageAtEmailTextBox(), "Wrong email");
	}
	
	@Test
	public void Login_03_Email_Not_Found() {
		homePageFactory_Object.clickToLoginLink();
		
		loginPageFactory_Object = new LoginPageFactory_Object(driver);
		loginPageFactory_Object.inputToEmailTextbox(notFoundEmail);
		
		loginPageFactory_Object.clickToLoginButton();
		
		Assert.assertEquals(loginPageFactory_Object.getErrorMessageUnsuccessfull(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}
	
	@Test
	public void Login_04_Existing_Email_Empty_PassWord() {
		homePageFactory_Object.clickToLoginLink();
		
		loginPageFactory_Object = new LoginPageFactory_Object(driver);
		loginPageFactory_Object.inputToEmailTextbox(existingEmail);
		loginPageFactory_Object.inputToPassWordTextbox("");
		
		loginPageFactory_Object.clickToLoginButton();
		
		Assert.assertEquals(loginPageFactory_Object.getErrorMessageUnsuccessfull(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void Login_05_Existing_Empty_Incorrect_PassWord() {
		homePageFactory_Object.clickToLoginLink();
		
		loginPageFactory_Object = new LoginPageFactory_Object(driver);
		loginPageFactory_Object.inputToEmailTextbox(existingEmail);
		loginPageFactory_Object.inputToPassWordTextbox("sdfghjk");
		
		loginPageFactory_Object.clickToLoginButton();
		
		Assert.assertEquals(loginPageFactory_Object.getErrorMessageUnsuccessfull(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void Login_06_Valid_Email_PasssWord() {
		homePageFactory_Object.clickToLoginLink();
		
		loginPageFactory_Object = new LoginPageFactory_Object(driver);
		loginPageFactory_Object.inputToEmailTextbox(existingEmail);
		loginPageFactory_Object.inputToPassWordTextbox(passWord);
		
		loginPageFactory_Object.clickToLoginButton();
		
		Assert.assertTrue(homePageFactory_Object.isMyAccountLinkDisplayer());
	}
	public int random() {
		Random random = new Random();
		return random.nextInt(99999);
	}
	@AfterClass
	public void afterClass() {
//		driver.quit();
	}


}
