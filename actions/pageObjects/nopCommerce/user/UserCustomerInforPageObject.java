package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.UserMyAccountPageUI;

public class UserCustomerInforPageObject extends BasePage{
	private WebDriver driver;
	public UserCustomerInforPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public String getTextMyAccount() {
		waitForElementVisible(driver, UserMyAccountPageUI.MY_ACCOUNT_HEADER);
		return getElementText(driver, UserMyAccountPageUI.MY_ACCOUNT_HEADER);
	}
	
}
