package pageObject.sauceLab;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.liveGuru.PageLoginUIs;
import pageUIs.sauceLab.LoginPageUIs;

public class LoginPageObject extends BasePage{
	private WebDriver driver;
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void enterToUsernameTextBox(String username) {
		waitForElementVisible(driver, LoginPageUIs.USERNAME_TEXTBBOX);
		sendkeyToElement(driver, LoginPageUIs.USERNAME_TEXTBBOX, username);
	}
	public void enterToPasswordTextBox(String password) {
		waitForElementVisible(driver, LoginPageUIs.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUIs.PASSWORD_TEXTBOX, password);
		
	}
	public ProductPageObject clickToLoginButton() {
		waitForElementVisible(driver, LoginPageUIs.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUIs.LOGIN_BUTTON);
		return PageGeneratorManager.getProductPageObject(driver);
	}
	
}
