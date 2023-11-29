package pageObject.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.liveGuru.PageRegisterUIs;

public class PageRegisterObject extends BasePage{

	private WebDriver driver;
	public PageRegisterObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToFirstName(String firstName) {
		waitForElementClick(driver, PageRegisterUIs.FIRST_NAME_TEXTBOX);
		sendkeyToElement(driver, PageRegisterUIs.FIRST_NAME_TEXTBOX, firstName);
	}

	public void inputToLastName(String lastName) {
		waitForElementClick(driver, PageRegisterUIs.LAST_NAME_TEXTBOX);
		sendkeyToElement(driver, PageRegisterUIs.LAST_NAME_TEXTBOX, lastName);
	}

	public void inputEmail(String email) {
		waitForElementClick(driver, PageRegisterUIs.EMAIL_TEXTBOX);
		sendkeyToElement(driver, PageRegisterUIs.EMAIL_TEXTBOX, email);
	}

	public void inputPassWord(String pass) {
		waitForElementClick(driver, PageRegisterUIs.PASS_WORD_TEXTBOX);
		sendkeyToElement(driver, PageRegisterUIs.PASS_WORD_TEXTBOX, pass);
	}

	public void inputCofirmPassWord(String confirmPassWord) {
		waitForElementClick(driver, PageRegisterUIs.CONFIRM_PASS_WORD_TEXTBOX);
		sendkeyToElement(driver, PageRegisterUIs.CONFIRM_PASS_WORD_TEXTBOX, confirmPassWord);
	}

	public PageHomeObject clickToButtonRegister() {
		waitForElementClick(driver, PageRegisterUIs.RIGISTER_BUTTON);
		clickToElement(driver, PageRegisterUIs.RIGISTER_BUTTON);
		return new PageGeneratorManager().getDriverPageHomeObject(driver);
	}

}
