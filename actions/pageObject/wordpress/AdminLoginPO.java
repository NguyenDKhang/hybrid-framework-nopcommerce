package pageObject.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.AdminLoginUI;

public class AdminLoginPO extends BasePage{
	WebDriver driver;

	public AdminLoginPO(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToUsernameTextbox(String adminUser) {
		waitForElementVisible(driver, AdminLoginUI.USERNAME_TEXTBOX);
		sendkeyToElement(driver, AdminLoginUI.USERNAME_TEXTBOX, adminUser);
	}

	public void enterToPasswordTextbox(String adminPass) {
		waitForElementVisible(driver, AdminLoginUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, AdminLoginUI.PASSWORD_TEXTBOX, adminPass);

		
	}

	public AdminDashboardPO clickToLoginButton() {
		waitForElementClick(driver, AdminLoginUI.LOGIN_BUTTON);
		clickToElement(driver, AdminLoginUI.LOGIN_BUTTON);
		return PageGeneratorManager_WordPressAdmin.getAdminDashboardPO(driver);
	}
	
}
