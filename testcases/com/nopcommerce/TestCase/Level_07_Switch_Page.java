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
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;

public class Level_07_Switch_Page extends BaseTest{
	// Quản lý việc khởi tạo page
	private WebDriver driver;
	private String firstName , lastName ,passWord ,confirmPassWord,existingEmail, 
	invalidEmail, notFoundEmail;
	
	private UserHomePageObject homePageObject; 
	private UserRegisterPageObject registerPageObject;
	private UserLoginPageObject loginPageObject;
	private UserCustomerInforPageObject customerInforPage;
	private UserAddressPageObject addressPageObject;
	private UserRewardPointPageObject rewardPointPageObject;
	private UserMyProductReviewPageObject myProductReviewPageObject;
	
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
		homePageObject = PageGeneratorManager.getUserHomePageObject(driver);
				
		firstName ="khang" + random();
		lastName="tesst "+random();
		existingEmail = "khang"+random()+"@gmasidl.com";
		invalidEmail = "asdasd@asd@@gmasidl.com";
		notFoundEmail = "khang"+random()+"@gmasidl.com";
		passWord 			   	="ASd1651c61616515";
		confirmPassWord 	="ASd1651c61616515";
	}

	@Test
	public void User_01_Register() {
		
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
	public void User_02_Login() {
		loginPageObject = homePageObject.openLoginPage();
		loginPageObject.inputToEmailTextbox(existingEmail);
		loginPageObject.inputToPassWordTextbox(passWord);
		loginPageObject.clickToLoginButton();
		
		Assert.assertEquals(homePageObject.getTextMyAccount(), "My account");
		
	}
	
	@Test
	public void User_03_MyAccount() {
		customerInforPage = homePageObject.openMyAccountPage();
		
		Assert.assertTrue(customerInforPage.getTextMyAccount().contains("My account"));

	}
	
	@Test
	public void User_04_Switch_Page() {
		// Viết trong hàm base page để tránh việc lập lại code 
		// Customer Infor -> Address
		addressPageObject = customerInforPage.openAddressPage(driver);
		// Address -> My Product Review
		myProductReviewPageObject = addressPageObject.openMyProductReviewPage(driver);
		// My Product Review -> Reward Point
		rewardPointPageObject = myProductReviewPageObject.openRewardPointPage(driver);
		// Reward Point -> Address
		addressPageObject = rewardPointPageObject.openAddressPage(driver);
		// Address -> Reward Point
		rewardPointPageObject = addressPageObject.openRewardPointPage(driver);
		// Reward Point -> My Product Review
		myProductReviewPageObject = rewardPointPageObject.openMyProductReviewPage(driver);
	}
	
	@Test
	public void User_05_() {

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
