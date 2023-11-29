package pageObject.jQuery;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.jQuery.HomePageUI;

public class HomePageOjbect extends BasePage{
	private WebDriver driver;
	
	public HomePageOjbect(WebDriver driver) {
		this.driver = driver;
	}

	public void openPagingByPageNumber(String pageNumber) {
		waitForElementClick(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
		clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
	}

	public void enterToGeaderTextboxByLabel(String headerLable, String value) {
		waitForAllElementVisible(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, headerLable);
		sendkeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, value, headerLable);
		pressKeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, Keys.ENTER, value);
	}

	public boolean isPageNumberActived(String number) {
		return isElementDisplayed(driver, HomePageUI.PAGINATION_PAGE_ACTIVED_BY_NUMBER, number);
	}

	public Set<String> getValueEachRowAtAllPage() {
		int totalPage = getElementSize(driver, HomePageUI.TOTAL_PAGINATION);
	
		List<String> allRowValueAllPage =  new ArrayList<String>();
		// Thay list thành set sẽ không lưu trùng
		Set<String> allRowValueUniqueAllPage = new HashSet<String>();
		
		for (int index = 1; index < totalPage; index++) {
			clickToElement(driver, HomePageUI.PAGIANTION_PAGE_BY_INDEX, String.valueOf(index));
			sleep(1);
			
			List<WebElement> allRowElementEachPage = getListWebElement(driver, HomePageUI.ALL_ROWS_EACH_COUNTRY_PAGE);
			for (WebElement eachRow : allRowElementEachPage) {
				allRowValueUniqueAllPage.add(eachRow.getText());
			}
		}
		for (String value : allRowValueUniqueAllPage) {
			System.out.println("--------------------------------");
			System.out.println(value);
		}
		return allRowValueUniqueAllPage;
	}

	public void enterToTextboxAtByColumNameRowNumber(String columnName, String rowNumber, String valueInput) {
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
 
		waitForElementVisible(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		sendkeyToElement(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, valueInput, rowNumber, String.valueOf(columnIndex));
		
	}

	public void selectDropDownByColumNameRowNumber(String columnName, String rowNumber, String valueDropDown) {
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		
		waitForElementClick(driver, HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		selectItemInDefaultDropdownBy_Value(driver, HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, valueDropDown, rowNumber, String.valueOf(columnIndex));
	}

	public void checkBoxDropDownByColumNameRowNumber(String columnName, String rowNumber) {
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		
		waitForElementClick(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		clickToElement(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		
	}

	public void clickToIconByRowNumber(String rowNumber, String iconName) {
		waitForElementClick(driver, HomePageUI.CLICK_BUTTON_INDEX_AND_ROW_INDEX,rowNumber, iconName);
		clickToElement(driver, HomePageUI.CLICK_BUTTON_INDEX_AND_ROW_INDEX, rowNumber, iconName );

	}

}
