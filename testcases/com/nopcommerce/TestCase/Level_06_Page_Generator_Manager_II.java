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
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_06_Page_Generator_Manager_II extends BaseTest{
	// Quản lý việc khởi tạo page
	private WebDriver driver;
	private String firstName , lastName ,passWord ,confirmPassWord,existingEmail, 
	invalidEmail, notFoundEmail;
	
	private UserHomePageObject homePageObject; 
	private UserRegisterPageObject registerPageObject;
	private UserLoginPageObject loginPageObject;
	
	// Cách 1: Khởi tạo trực tiếp trên testCase
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		// Initial (Khởi tạo)
		// Che dấu việc khởi tạo của đối tượng
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com");
		// Click logout thì bussines quay về trang home page
		homePageObject = new UserHomePageObject(driver);
				
		firstName ="khang" + random();
		lastName="tesst "+random();
		existingEmail = "khang"+random()+"@gmasidl.com";
		invalidEmail = "asdasd@asd@@gmasidl.com";
		notFoundEmail = "khang"+random()+"@gmasidl.com";
		passWord 			   	="ASd1651c61616515";
		confirmPassWord 	="ASd1651c61616515";
		
		// create account 
		registerPageObject = homePageObject.clickToRegisterLink();
		
		registerPageObject.inputFirstNameTextbox(firstName);
		registerPageObject.inputLastNameTextbox(lastName);
		registerPageObject.inputEmailTextbox(existingEmail);
		registerPageObject.inputPasswordTextbox(passWord);
		registerPageObject.inputConfirmPasswordTextbox(confirmPassWord);
		
		 registerPageObject.clickToRegisterButton();
		
		Assert.assertEquals(registerPageObject.getSuccessMessage(),"Your registration completed");
		
		
		
	}

	@Test
	public void Login_01_Empty_Data() {
		loginPageObject = homePageObject.openLoginPage();
		// Từ trang Home - Click Login link -> qua trang login
		loginPageObject.clickToLoginButton();
		Assert.assertEquals(loginPageObject.getErrorMessageAtEmailTextBox(), "Please enter your email");
	}
	
	@Test
	public void Login_02_Invalid_Email() {
		loginPageObject = homePageObject.openLoginPage();
		loginPageObject.inputToEmailTextbox(invalidEmail);
		
		loginPageObject.clickToLoginButton();
		
		Assert.assertEquals(loginPageObject.getErrorMessageAtEmailTextBox(), "Wrong email");
		
	}
	
	@Test
	public void Login_03_Email_Not_Found() {
		loginPageObject = homePageObject.openLoginPage();
		loginPageObject.inputToEmailTextbox(notFoundEmail);
		
		loginPageObject.clickToLoginButton();
		
		Assert.assertEquals(loginPageObject.getErrorMessageUnsuccessfull(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");

	}
	
	@Test
	public void Login_04_Existing_Email_Empty_PassWord() {
		loginPageObject = homePageObject.openLoginPage();

		loginPageObject.inputToEmailTextbox(existingEmail);
		loginPageObject.inputToPassWordTextbox("");
		
		loginPageObject.clickToLoginButton();
		
		Assert.assertEquals(loginPageObject.getErrorMessageUnsuccessfull(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}
	
	@Test
	public void Login_05_Existing_Empty_Incorrect_PassWord() {
		loginPageObject = homePageObject.openLoginPage();

		loginPageObject.inputToEmailTextbox(existingEmail);
		loginPageObject.inputToPassWordTextbox("sdfghjk");
		
		loginPageObject.clickToLoginButton();
		
		Assert.assertEquals(loginPageObject.getErrorMessageUnsuccessfull(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}
	
	@Test
	public void Login_06_Valid_Email_PasssWord() {
		loginPageObject = homePageObject.openLoginPage();

		loginPageObject.inputToEmailTextbox(existingEmail);
		loginPageObject.inputToPassWordTextbox(passWord);
		
		loginPageObject.clickToLoginButton();
		
		Assert.assertTrue(homePageObject.isMyAccountLinkDisplayer());
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
