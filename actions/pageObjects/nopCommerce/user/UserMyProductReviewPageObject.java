package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.UserMyProductReviewPageUI;

public class UserMyProductReviewPageObject extends BasePage{
	private WebDriver driver;
	public UserMyProductReviewPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
