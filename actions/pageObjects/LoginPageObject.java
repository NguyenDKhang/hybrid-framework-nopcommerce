package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage{
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getErrorMessageAtEmailTextBox() {
		waitForElementVisible(driver, LoginPageUI.ERROR_MASSEGE_EMAIL);
		return getElementText(driver, LoginPageUI.ERROR_MASSEGE_EMAIL);
	}

	public void clickToLoginButton() {
		waitForElementClick(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
	}

	public void inputToEmailTextbox(String email) {
		waitForElementClick(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, email);
	}

	public String getErrorMessageUnsuccessfull() {
		waitForElementVisible(driver, LoginPageUI.ERROR_MASSEGE);
		return getElementText(driver, LoginPageUI.ERROR_MASSEGE);
	}

	public void inputToPassWordTextbox(String password) {
		waitForElementClick(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
		
	}
	
}
