package pageObject.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.UserHomeUI;
import pageUIs.wordpress.UserSearchPostPageUI;

public class UserSearchPostPO extends BasePage{
	WebDriver driver;

	public UserSearchPostPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isNothingFoundMessageDisplayed(String message) {
		waitForElementVisible(driver,UserSearchPostPageUI.NOTHING_FOUNDMESSAGE, message);
		return isElementDisplayed(driver, UserSearchPostPageUI.NOTHING_FOUNDMESSAGE, message);
	}
}