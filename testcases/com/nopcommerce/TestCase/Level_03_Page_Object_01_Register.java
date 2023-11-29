package com.nopcommerce.TestCase;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_03_Page_Object_01_Register {
  
	private WebDriver driver;
	private String pojectPath = System.getProperty("user.dir");
	private String firstName , lastName ,passWord ,confirmPassWord,email;
	
	private UserHomePageObject homePageObject; 
	private UserRegisterPageObject registerPageObject;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", pojectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		homePageObject = new UserHomePageObject(driver);
		
		firstName ="khang" + random();
		lastName="tesst "+random();
		email = "khang"+random()+"@gmasidl.com";
		passWord 			   	="ASd1651c61616515";
		confirmPassWord 	="ASd1651c61616515";
		
		// Initial (Khởi tạo)
		// Che dấu việc khởi tạo của đối tượng
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com");
	}

	@Test
	public void Register_01_Empty_Data() {
//		waitForElementClik(driver, "//a[@class='ico-register']");
//		clickToElement(driver, "//a[@class='ico-register']");
		System.out.println("Step 1: click to register link");
		homePageObject.clickToRegisterLink();
		
		registerPageObject = new UserRegisterPageObject(driver);
		
//		waitForElementClik(driver, "//button[@id='register-button']");
//		 clickToElement(driver, "//button[@id='register-button']");
		 System.out.println("Step 2: click to button register");
		 registerPageObject.clickToRegisterButton();
		 
		 System.out.println("Step 3: Verify message erorr");
		 Assert.assertEquals(registerPageObject.getErrorMessageFirstNameTextBox(),"First name is required.");
		 Assert.assertEquals(registerPageObject.getErrorMessageLastNameTextBox(),"Last name is required.");
		 Assert.assertEquals(registerPageObject.getErrorMessageEmailTextBox(),"Email is required.");
		 Assert.assertEquals(registerPageObject.getErrorMessagePasswordTextBox(),"Password is required.");
		 Assert.assertEquals(registerPageObject.getErrorMessageConfirmPasswordTextBox(),"Password is required.");
	}
	
	@Test
	public void Register_02_Invalid_Email() {
		
		homePageObject.clickToRegisterLink();
		
		registerPageObject = new UserRegisterPageObject(driver);
		
		registerPageObject.inputFirstNameTextbox(firstName);
		registerPageObject.inputLastNameTextbox(lastName);
		registerPageObject.inputEmailTextbox("khang");
		registerPageObject.inputPasswordTextbox(passWord);
		registerPageObject.inputConfirmPasswordTextbox(confirmPassWord);
		
		 registerPageObject.clickToRegisterButton();
		
		Assert.assertEquals(registerPageObject.getErrorMessageEmailTextBox(),"Wrong email");
	}
	
	@Test
	public void Register_03_Success() {
		
		homePageObject.clickToRegisterLink();
		
		registerPageObject = new UserRegisterPageObject(driver);
		
		registerPageObject.inputFirstNameTextbox(firstName);
		registerPageObject.inputLastNameTextbox(lastName);
		registerPageObject.inputEmailTextbox(email);
		registerPageObject.inputPasswordTextbox(passWord);
		registerPageObject.inputConfirmPasswordTextbox(confirmPassWord);
		
		 registerPageObject.clickToRegisterButton();
		
		Assert.assertEquals(registerPageObject.getSuccessMessage(),"Your registration completed");
	}
	
	@Test
	public void Register_04_Existing_Email() {
		
		homePageObject.clickToRegisterLink();
		
		registerPageObject = new UserRegisterPageObject(driver);
		
		registerPageObject.inputFirstNameTextbox(firstName);
		registerPageObject.inputLastNameTextbox(lastName);
		registerPageObject.inputPasswordTextbox(passWord);
		registerPageObject.inputConfirmPasswordTextbox(confirmPassWord);
		registerPageObject.inputEmailTextbox(email);
		
		 registerPageObject.clickToRegisterButton();
		
		Assert.assertEquals(registerPageObject.getErrorExistingEmailMessage(),"The specified email already exists");
	}
	
	@Test
	public void Register_05_Paswprd_Less_Than_6_Chars() {
		
		homePageObject.clickToRegisterLink();
		
		registerPageObject = new UserRegisterPageObject(driver);
		
		registerPageObject.inputFirstNameTextbox(firstName);
		registerPageObject.inputLastNameTextbox(lastName);
		registerPageObject.inputEmailTextbox(email);
		registerPageObject.inputPasswordTextbox("ASd");
		registerPageObject.inputConfirmPasswordTextbox("ASd");
		
		 registerPageObject.clickToRegisterButton();
		
		Assert.assertEquals(registerPageObject.getErrorMessagePasswordTextBox(),"Password must meet the following rules:"+"\n" +"must have at least 6 characters");
	}

	@Test
	public void Register_06_Invalid_Confirm_Password() {
		
		homePageObject.clickToRegisterLink();
		
		registerPageObject = new UserRegisterPageObject(driver);
		
		registerPageObject.inputFirstNameTextbox(firstName);
		registerPageObject.inputLastNameTextbox(lastName);
		registerPageObject.inputEmailTextbox(email);
		registerPageObject.inputPasswordTextbox("ASd1651c61616515");
		registerPageObject.inputConfirmPasswordTextbox("ASd1651c");
		
		 registerPageObject.clickToRegisterButton();
		
		Assert.assertEquals(registerPageObject.getErrorMessageConfirmPasswordTextBox(),"The password and confirmation password do not match.");
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
