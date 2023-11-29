package pageObject.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.AdminPostSearchUI;

public class AdminPostSearchPO extends BasePage{
	WebDriver driver;

	public AdminPostSearchPO(WebDriver driver) {
		this.driver = driver;
	}

	public AdminPostAddNewPO clickToAddNewButton() {
		waitForElementClick(driver, AdminPostSearchUI.ADD_NEW_BUTTON);
		clickToElement(driver, AdminPostSearchUI.ADD_NEW_BUTTON);
		return PageGeneratorManager_WordPressAdmin.getAdminPostAddNewPO(driver);
	}

	public void enterToSearchTextbox(String postTitle) {
		waitForElementVisible(driver, AdminPostSearchUI.SEARCH_TEXTBOX);
		sendkeyToElement(driver, AdminPostSearchUI.SEARCH_TEXTBOX, postTitle);
	}

	public void clickToSearchPostButton() {
		waitForElementVisible(driver, AdminPostSearchUI.SEARCH_POSTS_BUTTON);
		clickToElement(driver, AdminPostSearchUI.SEARCH_POSTS_BUTTON);
	}

	public boolean isPostSearchTableDisplayed(String headerID, String cellValue) {
		int headerIndex = getElementSize(driver, AdminPostSearchUI.TABLE_HEADER_INDEX_BY_HEADER_NAME, headerID) +1;
		waitForElementVisible(driver, AdminPostSearchUI.TABLE_ROW_VALUE_BY_HEADER_INDEX, String.valueOf(headerIndex), cellValue);
		return isElementDisplayed(driver, AdminPostSearchUI.TABLE_ROW_VALUE_BY_HEADER_INDEX, String.valueOf(headerIndex), cellValue);
	}

	public AdminPostAddNewPO clickToPostTitleLink(String postTitle) {
		waitForElementClick(driver, AdminPostSearchUI.ROW_TITLE_DETAIL_BY_TITLE_NAME, postTitle);
		clickToElement(driver, AdminPostSearchUI.ROW_TITLE_DETAIL_BY_TITLE_NAME, postTitle);
		return PageGeneratorManager_WordPressAdmin.getAdminPostAddNewPO(driver);
	}

	public void selectPostCheckboxByTitle() {
		waitForElementClick(driver, AdminPostSearchUI.ROW_CHECKBOX_BY_TITLE_NAME);
		checkTheCheckboxOrRadio(driver,  AdminPostSearchUI.ROW_CHECKBOX_BY_TITLE_NAME);
	}

	public void selectTextItemInActionDropdown(String dropdownItem) {
		waitForElementClick(driver, AdminPostSearchUI.ACTION_DROPDOWN);
		selectItemInDefaultDropdownBy_Text(driver, AdminPostSearchUI.ACTION_DROPDOWN, dropdownItem);
		
	}

	public void clickToApplyButton() {
		waitForElementClick(driver, AdminPostSearchUI.APPLY_BUTTON);
		clickToElement(driver, AdminPostSearchUI.APPLY_BUTTON);
	}

	public boolean isMoveToTrashMessageDisplayed(String message) {
		waitForElementVisible(driver, AdminPostSearchUI.MOVE_TO_TRASH_MESSAGE, message);
		return isElementDisplayed(driver, AdminPostSearchUI.MOVE_TO_TRASH_MESSAGE, message);
	}

	public String isNoPostFoundMessageDisplayed() {
		waitForElementVisible(driver, AdminPostSearchUI.NO_POST_FOUND_MESSAGE);
		return getElementText(driver, AdminPostSearchUI.NO_POST_FOUND_MESSAGE);
	}

}
