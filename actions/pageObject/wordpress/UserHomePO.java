package pageObject.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.user.UserHomePageUI;
import pageUIs.wordpress.UserHomeUI;

public class UserHomePO extends BasePage{
	WebDriver driver;

	public UserHomePO(WebDriver driver) {
		this.driver = driver;
	}


	public boolean isPostInfoDisplayedWithPostTitle(String postTitle) {
		waitForElementVisible(driver, UserHomeUI.POST_TITLE_TEXT,postTitle);
		return isElementDisplayed(driver, UserHomeUI.POST_TITLE_TEXT,postTitle);
	}

	public boolean isPostInfoDisplayedWithPostBody(String postTitle, String postBody) {
		waitForElementVisible(driver, UserHomeUI.POST_BODY_TEXT_BY_POST_TITLE, postTitle, postBody);
		return isElementDisplayed(driver, UserHomeUI.POST_BODY_TEXT_BY_POST_TITLE, postTitle, postBody);
	}

	public boolean isPostInfoDisplayedWithAuthorName(String postTitle, String authorName) {
		waitForElementVisible(driver, UserHomeUI.POST_AUTHOR_TEXT_BY_POST_TITLE, postTitle ,authorName);
		return isElementDisplayed(driver, UserHomeUI.POST_AUTHOR_TEXT_BY_POST_TITLE, postTitle ,authorName);
	}

	public boolean isPostInfoDisplayedWithCurrentDay(String postTitle,String currentDay) {
		waitForElementVisible(driver, UserHomeUI.POST_CURRENT_DATE_TEXT_BY_POST_TITLE, postTitle ,currentDay);
		return isElementDisplayed(driver, UserHomeUI.POST_CURRENT_DATE_TEXT_BY_POST_TITLE, postTitle ,currentDay);
	}

	public UserPostDetailPO clickToPostTitle(String postTitle) {
		waitForElementClick(driver, UserHomeUI.POST_TITLE_TEXT,postTitle);
		clickToElement(driver, UserHomeUI.POST_TITLE_TEXT,postTitle);
		return PageGeneratorManager_WordPressAdmin.getUserPostDetailPO(driver);
	}

	public boolean isPostInforUndisplayedWithPostTitle(String editPostTitle) {
		return isElementUndisplayed(driver, UserHomeUI.POST_TITLE_TEXT, editPostTitle);
	}

	public void enterToSearchTextbox(String editPostTitle) {
		waitForElementVisible(driver, UserHomeUI.SEARCH_TEXTBOX, editPostTitle);
		sendkeyToElement(driver, UserHomeUI.SEARCH_TEXTBOX, editPostTitle);
	}

	public UserSearchPostPO clickToSearchButton() {
		waitForElementClick(driver, UserHomeUI.SEARCH_BUTTON);
		clickToElement(driver, UserHomeUI.SEARCH_BUTTON);
		return PageGeneratorManager_WordPressAdmin.getUserSearchPostPO(driver);
	}
}
