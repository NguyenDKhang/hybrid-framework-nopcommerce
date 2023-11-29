package pageObject.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.liveGuru.PageLoginUIs;

public class PageLoginObject extends BasePage{
	private WebDriver driver;
	public PageLoginObject(WebDriver driver) {
		this.driver = driver;
	}
	public PageHomeObject clickToLogin() {
		waitForElementClick(driver, PageLoginUIs.LOGIN_BUTTON);
		clickToElement(driver, PageLoginUIs.LOGIN_BUTTON);
		return new PageGeneratorManager().getDriverPageHomeObject(driver);
	}
	public void inputToPass(String pass) {
		waitForElementVisible(driver, PageLoginUIs.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, PageLoginUIs.PASSWORD_TEXTBOX, pass);
		
	}
	public void inputToEmail(String email) {
		waitForElementVisible(driver, PageLoginUIs.EMAIL_TEXTBOX);
		sendkeyToElement(driver, PageLoginUIs.EMAIL_TEXTBOX, email);
		
	}

}
