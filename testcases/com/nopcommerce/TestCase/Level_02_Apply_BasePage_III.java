package com.nopcommerce.TestCase;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;

public class Level_02_Apply_BasePage_III extends BasePage {
  
	WebDriver driver;
	String pojectPath = System.getProperty("user.dir");
	WebDriverWait explicitwait;
	Select select ;
	
	// Declare (khai báo)
//	BasePage basePage;
	
	String email = "khang"+random()+"@gmasidl.com";
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", pojectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		// Initial (Khởi tạo)
		// Che dấu việc khởi tạo của đối tượng
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitwait = new WebDriverWait(driver, 30);
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		
		 clickToElement(driver, "//button[@id='register-button']");
		Assert.assertEquals( getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
	}
	
	@Test
	public void TC_02_Register_Invalid_Email() {
		
		 sendkeyToElement(driver, "//input[@id='FirstName']", "duy");
		 sendkeyToElement(driver, "//input[@id='LastName']", "khang");
		 selectItemInDefaultDropdownBy_Text(driver, "//select[@name='DateOfBirthDay']", "10");
		 selectItemInDefaultDropdownBy_Text(driver, "//select[@name='DateOfBirthMonth']", "June");
		 selectItemInDefaultDropdownBy_Text(driver, "//select[@name='DateOfBirthYear']", "1938");
		
		 sendkeyToElement(driver, "//input[@id='Email']", "khang");
		 sendkeyToElement(driver, "//input[@id='Password']", "ASd1651c61616515");
		 sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "ASd1651c61616515");
		
		 clickToElement(driver, "//button[@id='register-button']");
		Assert.assertEquals( getElementText(driver, "//span[@id='Email-error']"), "Wrong email");

	}
	
	@Test
	public void TC_03_Register_Success() {
		 sendkeyToElement(driver, "//input[@id='FirstName']", "duy");
		 sendkeyToElement(driver, "//input[@id='LastName']", "khang");
		 selectItemInDefaultDropdownBy_Text(driver, "//select[@name='DateOfBirthDay']", "10");
		 selectItemInDefaultDropdownBy_Text(driver, "//select[@name='DateOfBirthMonth']", "June");
		 selectItemInDefaultDropdownBy_Text(driver, "//select[@name='DateOfBirthYear']", "1938");
		
		 sendkeyToElement(driver, "//input[@id='Email']", email);
		 sendkeyToElement(driver, "//input[@id='Password']", "ASd1651c61616515");
		 sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "ASd1651c61616515");
		
		 clickToElement(driver, "//button[@id='register-button']");
		 waitForElementVisible(driver, "//div[@class='result']");
		Assert.assertEquals( getElementText(driver, "//div[@class='result']"), "Your registration completed");
	}
	
	@Test
	public void TC_04_Register_Existing_Email() {
		
		 clickToElement(driver, "//a[@class='ico-register']");
		 sendkeyToElement(driver, "//input[@id='FirstName']", "duy");
		 sendkeyToElement(driver, "//input[@id='LastName']", "khang");
		 selectItemInDefaultDropdownBy_Text(driver, "//select[@name='DateOfBirthDay']", "10");
		 selectItemInDefaultDropdownBy_Text(driver, "//select[@name='DateOfBirthMonth']", "June");
		 selectItemInDefaultDropdownBy_Text(driver, "//select[@name='DateOfBirthYear']", "1938");
		
		 sendkeyToElement(driver, "//input[@id='Email']", "khsdl@gmasidl.com");
		 sendkeyToElement(driver, "//input[@id='Password']", "ASd1651c61616515");
		 sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "ASd1651c61616515");
		
		 clickToElement(driver, "//button[@id='register-button']");
		Assert.assertEquals( getElementText(driver, "//div[@class='page-body']//li"), "The specified email already exists");

	}
	
	@Test
	public void TC_05_Register_Paswprd_Less_Than_6_Chars() {
		 clickToElement(driver, "//a[@class='ico-register']");
		 sendkeyToElement(driver, "//input[@id='FirstName']", "duy");
		 sendkeyToElement(driver, "//input[@id='LastName']", "khang");
		 selectItemInDefaultDropdownBy_Text(driver, "//select[@name='DateOfBirthDay']", "10");
		 selectItemInDefaultDropdownBy_Text(driver, "//select[@name='DateOfBirthMonth']", "June");
		 selectItemInDefaultDropdownBy_Text(driver, "//select[@name='DateOfBirthYear']", "1938");
		
		 sendkeyToElement(driver, "//input[@id='Email']", email);
		 sendkeyToElement(driver, "//input[@id='Password']", "ASd");
		 sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "ASd");
		
		 clickToElement(driver, "//button[@id='register-button']");
		Assert.assertEquals( getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:"+"\n" +"must have at least 6 characters");
	}

	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		
		 clickToElement(driver, "//a[@class='ico-register']");
		 sendkeyToElement(driver, "//input[@id='FirstName']", "duy");
		 sendkeyToElement(driver, "//input[@id='LastName']", "khang");
		 selectItemInDefaultDropdownBy_Text(driver, "//select[@name='DateOfBirthDay']", "10");
		 selectItemInDefaultDropdownBy_Text(driver, "//select[@name='DateOfBirthMonth']", "June");
		 selectItemInDefaultDropdownBy_Text(driver, "//select[@name='DateOfBirthYear']", "1938");
		
		 sendkeyToElement(driver, "//input[@id='Email']", email);
		 sendkeyToElement(driver, "//input[@id='Password']", "ASd1651c61616515");
		 sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "ASd1651c");
		
		 clickToElement(driver, "//button[@id='register-button']");
		Assert.assertEquals( getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
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
