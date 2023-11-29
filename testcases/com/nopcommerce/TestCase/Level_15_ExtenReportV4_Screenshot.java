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

public class Level_15_ExtenReportV4_Screenshot extends BaseTest{
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

		// create account 
		registerPage = homePage.clickToRegisterLink();
		
		registerPage.inputFirstNameTextbox(firstName);
		
		registerPage.inputLastNameTextbox(lastName);
		
		registerPage.inputEmailTextbox(existingEmail);
		
		registerPage.inputPasswordTextbox(passWord);
		
		registerPage.inputConfirmPasswordTextbox(confirmPassWord);
		
		registerPage.clickToRegisterButton();
//		verifyEquals(registerPage.getSuccessMessage(),"Your registration completed._____");
		Assert.assertEquals(registerPage.getSuccessMessage(),"Your registration completed._____");
	}
	
	@Test
	public void TC_02_Login(Method method) {
		
		loginPage = homePage.openLoginPage();
		
		loginPage.inputToEmailTextbox(existingEmail);
		
		loginPage.inputToPassWordTextbox(passWord);
		
		loginPage.clickToLoginButton();
		
//		verifyEquals(homePage.getTextMyAccount(), "My account[][]");
		Assert.assertEquals(homePage.getTextMyAccount(), "My account[][]");
		
		customerInforPage = homePage.openMyAccountPage();
//		verifyTrue(customerInforPage.getTextMyAccount().contains("My account_____"));
		Assert.assertTrue(customerInforPage.getTextMyAccount().contains("My account_____"));
		
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