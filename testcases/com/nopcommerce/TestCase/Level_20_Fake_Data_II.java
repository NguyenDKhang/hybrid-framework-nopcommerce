package com.nopcommerce.TestCase;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.data.UserData;

import commons.BaseTest;
import commons.PageGeneratorManager;

import static org.testng.Assert.assertFalse	 ;
import static org.testng.Assert.assertTrue ;
import static org.testng.Assert.assertEquals ;

import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import utilities.DataHelper;

public class Level_20_Fake_Data_II extends BaseTest{
	WebDriver driver;
	
	private DataHelper dataFaker;
	
	private UserRegisterPageObject registerPage;
	private UserHomePageObject homePage; 
	private String firstName , lastName ,passWord ,confirmPassWord,existingEmail, day, month, year;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		// Initial (Khởi tạo)
		
		dataFaker = new DataHelper().getDataHelper();
		
		driver.get("https://demo.nopcommerce.com");
		// Click logout thì bussines quay về trang home page
		homePage = PageGeneratorManager.getUserHomePageObject(driver);
		showBrowserConsoleLogs(driver);
		
		firstName 		= UserData.FRIST_NAME;
		lastName		= UserData.LAST_NAME;
		existingEmail   = UserData.Resgitor.EMAIL_ADDRESS + "@gmail.com";
		passWord 		= UserData.Resgitor.PASSWORD;
		confirmPassWord = UserData.Resgitor.PASSWORD;
		
		day = UserData.Resgitor.DATE;
		month = UserData.Resgitor.MONTH;
		year = UserData.Resgitor.YEAR;
		
		
	}
	
	@Test
	public void TC_01_Register() {
		// Tên chức năng - Step + thứ tự: Mô tả
		log.info("Register-Step 01: Navigate to register page");
		registerPage = homePage.clickToRegisterLink();
		showBrowserConsoleLogs(driver);
		registerPage.clickToRadioButtonLable(driver,"Female");
		
		log.info("Register-Step 02: Enter to firtname textbox with value is '"+lastName+"'");
		registerPage.inputToTextboxID(driver, "FirstName",firstName);
		
		log.info("Register-Step 03: Enter to lastName textbox with value is '"+lastName+"'");
		registerPage.inputToTextboxID(driver, "LastName",lastName);

		registerPage.selectToDropdownByName(driver,"DateOfBirthDay",day);
		registerPage.selectToDropdownByName(driver,"DateOfBirthMonth",month);
		registerPage.selectToDropdownByName(driver,"DateOfBirthYear",year);
		
		registerPage.clickToCheckboxByLable(driver,"Newsletter");
		
		log.info("Register-Step 04: Enter to Email textbox with value is '"+existingEmail+"'");
		registerPage.inputToTextboxID(driver, "Email",existingEmail);
		
		log.info("Register-Step 05: Enter to passWord textbox with value is '"+passWord+"'");
		registerPage.inputToTextboxID(driver, "Password",passWord);
		
		log.info("Register-Step 06: Enter to confirmPassWord textbox with value is '"+confirmPassWord+"'");
		registerPage.inputToTextboxID(driver, "ConfirmPassword",confirmPassWord);
		
		log.info("Register-Step 07: Click register button");
		registerPage.clickButtonByText(driver,"Register");
		
	}
	
	@Test
	public void TC_02_Login() {
		log.info("Login-Step 01: Navigate to Login page");
		loginPage = homePage.openLoginPage();
		showBrowserConsoleLogs(driver);
		log.info("Login-Step 02: Enter to mail textbox with value is '"+existingEmail+"'");
		registerPage.inputToTextboxID(driver, "Email",existingEmail);
		
		log.info("Login-Step 03: Enter to pass textbox with value is '"+passWord+"'");
		registerPage.inputToTextboxID(driver, "Password",passWord);
		
		log.info("Login-Step 04: Click to login button");
		registerPage.clickButtonByText(driver, "Log in");
		homePage = PageGeneratorManager.getUserHomePageObject(driver);
		showBrowserConsoleLogs(driver);
	}
	
	@Test
	public void TC_03_My_Account() {
		log.info("Login-Step 05: Verify acccount login success");
		Assert.assertEquals(homePage.getTextMyAccount(), "My account[][]");
		
		customerInforPage = homePage.openMyAccountPage();
		Assert.assertTrue(customerInforPage.getTextMyAccount().contains("My account"));
		
		log.info("");
		Assert.assertEquals(customerInforPage.getTextboxValueById(driver,"FirstName"),firstName);
		
		log.info("");
		Assert.assertEquals(customerInforPage.getTextboxValueById(driver,"LastName"),lastName);

		log.info("");
		Assert.assertEquals(customerInforPage.getTextboxValueById(driver,"Email"),lastName);
		
		
	}
	
	public int random() {
		Random random = new Random();
		return random.nextInt(99999);
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
//		closeBrowserDriver();
	}
}  