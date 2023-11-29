package com.facebook.register;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageFaebookObject.LoginPageObject;
import pageFaebookObject.PageGeneratorManager;

public class Level_13_Element_Undisplayed extends BaseTest{
	private WebDriver driver;
	private LoginPageObject loginPage;
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGeneratorManager.getLoginPageObject(driver);
	}
	@Test
	public void TC_01_Verify_Element_Displayed() {
		loginPage.clickToCreateNewAccountButton();
		
		// nếu 1 cái hàm chỉ mong đợi để verify element displayed thôi - kết hợp cả wait + isDisplayed
		// waitFoeElementVisible
		// isElementDisplayed
		verifyTrue(loginPage.isEmailAccountButton());
	}
	
	@Test
	public void TC_02_Verify_Element_Undisplayed_In_DOM() {
		// Nếu như mình mong đợi 1 ái hàm vừa verify displayed/ undisplay thì ko được kết hợp wait
		// waitForElementVisible
		// isElementDisplayed
		
		// Verify True- mong đợi Confirm Email displayed (True)
		loginPage.enterToEmailAddressTextbox("sdvsdv231@gmail.com");
		loginPage.sleep(2);
		verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplayed());
		
		// Verify false - cho hàm trả về là Displayed
		loginPage.enterToEmailAddressTextbox("");
		loginPage.sleep(2);
		verifyTrue(loginPage.isConfirmEmailAddressTextboxDisplayed());
		loginPage.sleep(2);
	}
	
	@Test
	public void TC_03_Verify_Element_Undisplayed_Not_In_DOM() {
		loginPage.clickCloseIconAtRegisterForm();
		loginPage.sleep(2);
		// khi close cái form register đi thì confirm Email k còn trong DOM nữa
		// Nên hàm findElement sẽ bị fail vì k tìm thấy element
		// 1- Nó sẽ chờ hết timeout của implicit: 30s
		// 2- Nó sẽ đánh fail testcase tại đúng step này luôn
		// 3- K có chạy các step còn lại nữa
		
		// verify False - mong đợi confirm Email Undisplayed (false)
		// isDisplayed: Không thể kiểm tra 1 element không có trong DOM được 
//		verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplayed());
		verifyTrue(loginPage.isConfirmEmailAddressTextboxDisplayed());
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