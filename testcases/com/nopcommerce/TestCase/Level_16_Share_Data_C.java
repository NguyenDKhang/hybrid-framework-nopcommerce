package com.nopcommerce.TestCase;
import java.util.Random;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Register_Cookie;

import commons.BaseTest;
import commons.PageGeneratorManager;


import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;

//@Listeners(commons.MethodListener.class)
public class Level_16_Share_Data_C extends BaseTest{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	private UserHomePageObject homePage; 
	private UserLoginPageObject loginPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.get("https://demo.nopcommerce.com/");
		homePage = PageGeneratorManager.getUserHomePageObject(driver);
		
		
		
		log.info("Login-Step 01: Navigate to Login page");
		loginPage = homePage.openLoginPage();
		
		log.info("Login-Step 02: Set cookie and relload page");
		loginPage.setCookies(driver, Common_01_Register_Cookie.loggedCookies);
		for (Cookie cookies : Common_01_Register_Cookie.loggedCookies) {
			System.out.println("Class C: "+cookies);
		}
		loginPage.refreshCurrentPage(driver);
	
	}
	
	@Test
	public void TC_01_Login() {
		log.info("Login-Step - Step 03: Verify 'My Account' link is displayed");
		verifyEquals(homePage.getTextMyAccount(), "My account");
	}
//	
//	@Test
//	public void Search_01_Empty_Data() {
//		
//	}
//	@Test
//	public void Search_02_Relative_Product_Name() {
//		
//	}
//	@Test
//	public void Search_03_Alsolute_Product_Name() {
//		
//	}
//	@Test
//	public void Search_04_Parent_Category() {
//		
//	}
//	@Test
//	public void Search_05_Incorrect_Manufactorer() {
//		
//	}
//	@Test
//	public void Search_06_Correct_Manufactorer() {
//		
//	}

	public int random() {
		Random random = new Random();
		return random.nextInt(99999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}