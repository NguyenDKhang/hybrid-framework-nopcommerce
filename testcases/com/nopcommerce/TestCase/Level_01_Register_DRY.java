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

public class Level_01_Register_DRY {
  
	WebDriver driver;
	String pojectPath = System.getProperty("user.dir");
	WebDriverWait explicitwait;
	Select select ;
	
	String email = "khsdl"+random()+"@gmasidl.com";
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", pojectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitwait = new WebDriverWait(driver, 30);
		
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		driver.findElement(By.id("register-button")).click();
		Assert.assertEquals(driver.findElement(By.id("FirstName-error")).getText(), "First name is required.");
		
	}
	
	@Test
	public void TC_02_Register_Invalid_Email() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		driver.findElement(By.id("FirstName")).sendKeys("asdas");
		driver.findElement(By.id("LastName")).sendKeys("asasd");
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		select.selectByVisibleText("10");
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		select.selectByVisibleText("June");
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		select.selectByVisibleText("1938");
		
		driver.findElement(By.id("Email")).sendKeys("khsdl");
		driver.findElement(By.id("Password")).sendKeys("ASd1651c61616515");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("ASd1651c61616515");
		
		driver.findElement(By.id("register-button")).click();
		Assert.assertEquals(driver.findElement(By.id("Email-error")).getText(), "Wrong email");
		
	}
	
	@Test
	public void TC_03_Register_Success() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		driver.findElement(By.id("FirstName")).sendKeys("khsd");
		driver.findElement(By.id("LastName")).sendKeys("asdoaksd");
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		select.selectByVisibleText("10");
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		select.selectByVisibleText("June");
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		select.selectByVisibleText("1938");
		
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys("ASd1651c61616515");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("ASd1651c61616515");
		
		driver.findElement(By.id("register-button")).click();
		explicitwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));
		explicitwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='result']")));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
	}
	
	@Test
	public void TC_04_Register_Existing_Email() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		driver.findElement(By.id("FirstName")).sendKeys("khsd");
		driver.findElement(By.id("LastName")).sendKeys("asdoaksd");
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		select.selectByVisibleText("10");
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		select.selectByVisibleText("June");
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		select.selectByVisibleText("1938");
		
		driver.findElement(By.id("Email")).sendKeys("khsdl@gmasidl.com");
		driver.findElement(By.id("Password")).sendKeys("ASd1651c61616515");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("ASd1651c61616515");
		
		driver.findElement(By.id("register-button")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page-body']//li")).getText(), "The specified email already exists");

	}
	
	@Test
	public void TC_05_Register_Paswprd_Less_Than_6_Chars() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		driver.findElement(By.id("FirstName")).sendKeys("khsd");
		driver.findElement(By.id("LastName")).sendKeys("asdoaksd");
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		select.selectByVisibleText("10");
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		select.selectByVisibleText("June");
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		select.selectByVisibleText("1938");
		
		driver.findElement(By.id("Email")).sendKeys("khsdl@gmasidl.com");
		driver.findElement(By.id("Password")).sendKeys("AS");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("AS");
		
		driver.findElement(By.id("register-button")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='Password-error']")).getText(), "Password must meet the following rules:"+"\n" +"must have at least 6 characters");
	}

	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		driver.findElement(By.id("FirstName")).sendKeys("khsd");
		driver.findElement(By.id("LastName")).sendKeys("asdoaksd");
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		select.selectByVisibleText("10");
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		select.selectByVisibleText("June");
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		select.selectByVisibleText("1938");
		
		driver.findElement(By.id("Email")).sendKeys("khsdl@gmasidl.com");
		driver.findElement(By.id("Password")).sendKeys("ASd1651c61616515");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("ASd1651c61");
		
		driver.findElement(By.id("register-button")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ConfirmPassword-error']")).getText(), "The password and confirmation password do not match.");

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
