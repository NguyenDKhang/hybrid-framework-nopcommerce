package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.user.UserLoginPageUI;

public class UserLoginPageObject extends BasePage{
	private WebDriver driver;

	public UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getErrorMessageAtEmailTextBox() {
		waitForElementVisible(driver, UserLoginPageUI.ERROR_MASSEGE_EMAIL);
		return getElementText(driver, UserLoginPageUI.ERROR_MASSEGE_EMAIL);
	}

	public UserHomePageObject clickToLoginButton() {
		waitForElementClick(driver, UserLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
		return new PageGeneratorManager().getUserHomePageObject(driver);
	}

	public void inputToEmailTextbox(String email) {
		waitForElementClick(driver, UserLoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX, email);
	}

	public String getErrorMessageUnsuccessfull() {
		waitForElementVisible(driver, UserLoginPageUI.ERROR_MASSEGE);
		return getElementText(driver, UserLoginPageUI.ERROR_MASSEGE);
	}

	public void inputToPassWordTextbox(String password) {
		waitForElementClick(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, password);
		
	}
	// Wrapper nhiều hàm vào 1 hàm
	public UserHomePageObject loginAsUser(String email, String password) {
		inputToEmailTextbox(email);
		inputToPassWordTextbox(password);
		clickToLoginButton();
		return PageGeneratorManager.getUserHomePageObject(driver);
	}
	
}
