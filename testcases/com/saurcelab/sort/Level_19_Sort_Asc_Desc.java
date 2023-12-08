package com.saurcelab.sort;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.sauceLab.LoginPageObject;
import pageObject.sauceLab.PageGeneratorManager;
import pageObject.sauceLab.ProductPageObject;

public class Level_19_Sort_Asc_Desc extends BaseTest{
	WebDriver driver;
	LoginPageObject loginPage;
	ProductPageObject productPage;
	
	@Parameters({"browser","appUrl"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		loginPage = PageGeneratorManager.getLoginPageObject(driver);
		
		loginPage.enterToUsernameTextBox("standard_user");
		loginPage.enterToPasswordTextBox("secret_sauce");
		productPage = loginPage.clickToLoginButton();
		
	}
	
	@Test
	public void Sort_01_Name() {
		//Ascending
		productPage.selectItemInProductSortDropdown("Name (A to Z)");
		Assert.assertTrue(productPage.isProductNaeSortByAscending());
		productPage.sleep(3);
		
		// Descending
		productPage.selectItemInProductSortDropdown("Name (Z to A)");
		Assert.assertTrue(productPage.isProductNaeSortByDescending());
		productPage.sleep(3);
	}	
	
	@Test
	public void Sort_02_Price() {
		//Ascending
		productPage.selectItemInProductSortDropdown("Price (low to high)");
		Assert.assertTrue(productPage.isProductPriceSortByAscending());
		
		// Descending
		productPage.selectItemInProductSortDropdown("Price (high to low)");
		Assert.assertTrue(productPage.isProductPriceSortByDescending());
	}	
	
	public int random() {
		Random random = new Random();
		return random.nextInt(99999);
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
}  