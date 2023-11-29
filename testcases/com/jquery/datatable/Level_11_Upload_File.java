package com.jquery.datatable;

import java.util.Random;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.Upload.HomePageOjbectUpLoad;
import pageObject.Upload.PageGeneratorManager;
import pageObject.jQuery.HomePageOjbect;
import pageUIs.UploadFile.HomePageUI;

public class Level_11_Upload_File extends BaseTest{
	private WebDriver driver;
	private HomePageOjbectUpLoad homePage;
	String image_1 =  "1000.jpg";
	String image_2 =  "bảng chỉ đường.jpg";
	String image_3 =  "th.jpg";
	String [] multipleFileNames = {image_1, image_2, image_3};
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
	}
	@Test
	public void Upload_01_One_File_Tim() {
		// Step 01: Load single file
		homePage.uploadMultipleFiles(driver, image_1);
		
		// Step 02: Verify single file loaded success
		Assert.assertTrue(homePage.isFileLoadedByName(image_1));
		
		// Step 03: click to start button
		homePage.clickToStartButton();
		
		// Step 04: Verify single link uploaded success
		Assert.assertTrue(homePage.isFileLinkUpLoadedByName(image_1));
		
		// Step 05: Verify single file image uploaded success
		Assert.assertTrue(homePage.isFileImageUpLoadedByName(image_1));
	}
	@Test
	public void Upload_02_Multiple_File_Tim() {
		homePage.refreshCurrentPage(driver);
		homePage.uploadMultipleFiles(driver, multipleFileNames);

		// Step 02: Verify single file loaded success
		Assert.assertTrue(homePage.isFileLoadedByName(image_1));
		Assert.assertTrue(homePage.isFileLoadedByName(image_2));
		Assert.assertTrue(homePage.isFileLoadedByName(image_3));
		
		// Step 03: click to start button
		homePage.clickToStartButton();
		
		// Step 04: Verify single link uploaded success
		Assert.assertTrue(homePage.isFileLinkUpLoadedByName(image_1));
		Assert.assertTrue(homePage.isFileLinkUpLoadedByName(image_2));
		Assert.assertTrue(homePage.isFileLinkUpLoadedByName(image_3));
		
		// Step 05: Verify single file image uploaded success
		Assert.assertTrue(homePage.isFileImageUpLoadedByName(image_1));
		Assert.assertTrue(homePage.isFileImageUpLoadedByName(image_2));
		Assert.assertTrue(homePage.isFileImageUpLoadedByName(image_3));
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
