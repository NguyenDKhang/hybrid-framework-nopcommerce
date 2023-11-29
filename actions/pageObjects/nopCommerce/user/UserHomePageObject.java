package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.user.UserHomePageUI;
import pageUIs.nopCommerce.user.UserLoginPageUI;

public class UserHomePageObject extends BasePage{
	private WebDriver driver;
	
	public UserHomePageObject(WebDriver driver) {
			this.driver = driver;
	}

	public UserRegisterPageObject clickToRegisterLink() {
		waitForElementClick(driver, UserHomePageUI.REGISTER_TEXTBOX);
		clickToElement(driver, UserHomePageUI.REGISTER_TEXTBOX);
		return new PageGeneratorManager().getUserRegisterPageObject(driver);
	}

	public UserLoginPageObject openLoginPage() {
		waitForElementClick(driver, UserHomePageUI.LOGIN_TEXTBOX);
		clickToElement(driver, UserHomePageUI.LOGIN_TEXTBOX);
		return new PageGeneratorManager().getUserLoginPageObject(driver);
	}

	public boolean isMyAccountLinkDisplayer() {
		waitForElementVisible(driver, UserHomePageUI.MY_ACCOUNT_TEXTBOX);
		return isElementDisplayed(driver, UserHomePageUI.MY_ACCOUNT_TEXTBOX);
	}

	public String getTextMyAccount() {
		waitForElementVisible(driver, UserHomePageUI.TEXT_MY_ACCOUNT_LINK);
		return getElementText(driver, UserHomePageUI.TEXT_MY_ACCOUNT_LINK);
	}

	public UserCustomerInforPageObject openMyAccountPage() {
		waitForElementVisible(driver, UserHomePageUI.TEXT_MY_ACCOUNT_LINK);
		clickToElement(driver, UserHomePageUI.TEXT_MY_ACCOUNT_LINK);
		return PageGeneratorManager.getUserCustomerInforPageObject(driver);
	}

	public void clickLogOutPage() {
		waitForElementClick(driver, UserHomePageUI.LOGOUT_TEXTBOX);
		clickToElement(driver, UserHomePageUI.LOGOUT_TEXTBOX);
	}

}
