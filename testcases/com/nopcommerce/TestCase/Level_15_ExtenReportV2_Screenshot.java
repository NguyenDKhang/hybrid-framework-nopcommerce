package com.nopcommerce.TestCase;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//import com.relevantcodes.extentreports.LogStatus;

import commons.BaseTest;
import commons.PageGeneratorManager;

import static org.testng.Assert.assertFalse	 ;
import static org.testng.Assert.assertTrue ;
import static org.testng.Assert.assertEquals ;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
//import reportConfig.ExtentManager;

public class Level_15_ExtenReportV2_Screenshot extends BaseTest{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	private UserRegisterPageObject registerPage;
	private UserHomePageObject homePage; 
	private String firstName , lastName ,passWord ,confirmPassWord,existingEmail;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		// Initial (Khởi tạo)
		// Che dấu việc khởi tạo của đối tượng
		
		driver.get("https://demo.nopcommerce.com");
		// Click logout thì bussines quay về trang home page
		homePage = PageGeneratorManager.getUserHomePageObject(driver);
				
		firstName ="khang" + random();
		lastName="tesst "+random();
		existingEmail = "khang"+random()+"@gmasidl.com";
		passWord 			   	="ASd1651c61616515";
		confirmPassWord 	="ASd1651c61616515";
	}
	
	@Test
	public void TC_01_Register(Method method) {
//		ExtentManager.startTest(method.getName(),"TC_01_Register");
//
//		// create account 
//		ExtentManager.getTest().log(LogStatus.INFO,"Register-Step 01: click to register link");
//		registerPage = homePage.clickToRegisterLink();
//		
//		ExtentManager.getTest().log(LogStatus.INFO,"Register-Step 02: Enter to firtname textbox with value is '"+lastName+"'");
//		registerPage.inputFirstNameTextbox(firstName);
//		
//		ExtentManager.getTest().log(LogStatus.INFO,"Register-Step 03: Enter to lastName textbox with value is '"+lastName+"'");
//		registerPage.inputLastNameTextbox(lastName);
//		
//		ExtentManager.getTest().log(LogStatus.INFO,"Register-Step 04: Enter to Email textbox with value is '"+existingEmail+"'");
//		registerPage.inputEmailTextbox(existingEmail);
//		
//		ExtentManager.getTest().log(LogStatus.INFO,"Register-Step 05: Enter to passWord textbox with value is '"+passWord+"'");
//		registerPage.inputPasswordTextbox(passWord);
//		
//		ExtentManager.getTest().log(LogStatus.INFO,"Register-Step 06: Enter to confirmPassWord textbox with value is '"+confirmPassWord+"'");
//		registerPage.inputConfirmPasswordTextbox(confirmPassWord);
//		
//		ExtentManager.getTest().log(LogStatus.INFO,"Register-Step 07: Click register button");
//		registerPage.clickToRegisterButton();
////		verifyEquals(registerPage.getSuccessMessage(),"Your registration completed._____");
//		Assert.assertEquals(registerPage.getSuccessMessage(),"Your registration completed._____");
//		ExtentManager.endTest();
	}
	
	@Test
	public void TC_02_Login(Method method) {
//		ExtentManager.startTest(method.getName(),"TC_02_Login");
//		
//		ExtentManager.getTest().log(LogStatus.INFO,"Login-Step 01: Navigate to Login page");
//		loginPage = homePage.openLoginPage();
//		
//		ExtentManager.getTest().log(LogStatus.INFO,"Login-Step 02: Enter to mail textbox with value is '"+existingEmail+"'");
//		loginPage.inputToEmailTextbox(existingEmail);
//		
//		ExtentManager.getTest().log(LogStatus.INFO,"Login-Step 03: Enter to pass textbox with value is '"+passWord+"'");
//		loginPage.inputToPassWordTextbox(passWord);
//		
//		ExtentManager.getTest().log(LogStatus.INFO,"Login-Step 04: Click to login button");
//		loginPage.clickToLoginButton();
//		
//		ExtentManager.getTest().log(LogStatus.INFO,"Login-Step 05: Verify acccount login success");
////		verifyEquals(homePage.getTextMyAccount(), "My account[][]");
//		Assert.assertEquals(homePage.getTextMyAccount(), "My account[][]");
//		
//		customerInforPage = homePage.openMyAccountPage();
////		verifyTrue(customerInforPage.getTextMyAccount().contains("My account_____"));
//		Assert.assertTrue(customerInforPage.getTextMyAccount().contains("My account_____"));
//		
//		ExtentManager.endTest();
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