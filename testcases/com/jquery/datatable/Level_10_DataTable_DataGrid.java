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
import pageObject.jQuery.HomePageOjbect;

public class Level_10_DataTable_DataGrid extends BaseTest{
	private WebDriver driver;
	private HomePageOjbect homePage;
	private Set<String> allCountryValue;
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = pageObject.jQuery.PageGeneratorManager.getHomePage(driver);
	}
//	@Test
	public void Table_01_Paging() {
		homePage.openPagingByPageNumber("2");
		homePage.sleep(1);
		Assert.assertTrue(homePage.isPageNumberActived("2"));
		
		homePage.openPagingByPageNumber("6");
		homePage.sleep(1);
		Assert.assertTrue(homePage.isPageNumberActived("6"));

		homePage.openPagingByPageNumber("8");
		homePage.sleep(1);
		Assert.assertTrue(homePage.isPageNumberActived("8"));
	}
//	@Test
	public void Table_02_Enter_To_Header() {
		
		homePage.sleep(2);
		homePage.enterToGeaderTextboxByLabel("Females","384187");
		homePage.sleep(2);
		homePage.enterToGeaderTextboxByLabel("Country","Afghanistan");
		homePage.sleep(2);
		homePage.enterToGeaderTextboxByLabel("Males","407124");
		homePage.sleep(2);
		homePage.enterToGeaderTextboxByLabel("Total","791312");
		
		homePage.refreshCurrentPage(driver);
		homePage.sleep(2);
		homePage.enterToGeaderTextboxByLabel("Country","Algeria");
		homePage.sleep(2);
		homePage.enterToGeaderTextboxByLabel("Females","283821");
		homePage.sleep(2);
		homePage.enterToGeaderTextboxByLabel("Males","295140");
	}
	
//	@Test
	public void Table_03_Enter_To_Header() {
		
		allCountryValue =	homePage.getValueEachRowAtAllPage();
	}

	@Test
	public void Table_04_Enter_To_Textbox_At_Any_Row() {
		// value để nhập liệu - tham số 1
		// Row number: Tại row nào
		// Ex: Nhập vào textbox tại dòng số 3/ 5/ 2
		// Colum name:
		homePage.enterToTextboxAtByColumNameRowNumber("Company", "1","VN");
		homePage.enterToTextboxAtByColumNameRowNumber("Contact Person", "1","VN");
		homePage.selectDropDownByColumNameRowNumber("Country", "1","Malaysia");
		homePage.checkBoxDropDownByColumNameRowNumber("NPO?", "1");
		homePage.enterToTextboxAtByColumNameRowNumber("Order Placed", "1","123");
		homePage.enterToTextboxAtByColumNameRowNumber("Member Since", "1","02/03/2005");
		homePage.sleep(3);
//		
//		homePage.enterToTextboxAtByColumNameRowNumber("Company", "3","VN");
//		homePage.enterToTextboxAtByColumNameRowNumber("Contact Person", "3","VN");
//		homePage.selectDropDownByColumNameRowNumber("Country", "3","Malaysia");
//		homePage.checkBoxDropDownByColumNameRowNumber("NPO?", "3");
//		homePage.enterToTextboxAtByColumNameRowNumber("Order Placed", "3","123");
//		homePage.enterToTextboxAtByColumNameRowNumber("Member Since", "3","02/03/2005");
//		homePage.sleep(5);
//		
		homePage.clickToIconByRowNumber("1", "Remove Current Row");
		homePage.sleep(5);
		
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
