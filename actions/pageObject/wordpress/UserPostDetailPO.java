package pageObject.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.UserHomeUI;
import pageUIs.wordpress.UserPostDetailUI;

public class UserPostDetailPO extends BasePage{
	WebDriver driver;

	public UserPostDetailPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isPostInfoDisplayedWithPostTitle(String postTitle) {
		waitForElementVisible(driver, UserPostDetailUI.POST_TITLE_TEXT,postTitle);
		return isElementDisplayed(driver, UserPostDetailUI.POST_TITLE_TEXT,postTitle);
	}

	public boolean isPostInfoDisplayedWithPostBody(String postTitle, String postBody) {
		waitForElementVisible(driver, UserPostDetailUI.POST_BODY_TEXT_BY_POST_TITLE, postTitle, postBody);
		return isElementDisplayed(driver, UserPostDetailUI.POST_BODY_TEXT_BY_POST_TITLE, postTitle, postBody);
	}

	public boolean isPostInfoDisplayedWithAuthorName(String postTitle, String authorName) {
		waitForElementVisible(driver, UserPostDetailUI.POST_AUTHOR_TEXT_BY_POST_TITLE, postTitle ,authorName);
		return isElementDisplayed(driver, UserPostDetailUI.POST_AUTHOR_TEXT_BY_POST_TITLE, postTitle ,authorName);
	}

	public boolean isPostInfoDisplayedWithCurrentDay(String postTitle,String currentDay) {
		waitForElementVisible(driver, UserPostDetailUI.POST_CURRENT_DATE_TEXT_BY_POST_TITLE, postTitle ,currentDay);
		return isElementDisplayed(driver, UserPostDetailUI.POST_CURRENT_DATE_TEXT_BY_POST_TITLE, postTitle ,currentDay);
	}

	


}
