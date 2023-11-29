package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.UserAddressPageObjectUI;
import pageUIs.nopCommerce.user.UserRewardPointPageUI;

public class UserRewardPointPageObject extends BasePage{
	private WebDriver driver;
	public UserRewardPointPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
