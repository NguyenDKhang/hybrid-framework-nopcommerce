package pageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;
import pageUIs.nopCommerce.user.UserLoginPageUI;

public class LoginPageFactory_Object extends BasePageFactory{
	private WebDriver driver;
	public LoginPageFactory_Object(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
	}
	// Page UI
	// how dùng gì để tình locater | using : tìm locater nào
	@FindBy(how = How.XPATH,using = "//input[@class='email']")
	private WebElement emailTextBox; 
	// 1 kiểu khai báo khác nhưng vẫn đúng
	@FindBy(xpath = "//input[@class='password']")
	private WebElement passwordTextBox;
	
	@FindBy(xpath = "//button[@class='button-1 login-button']")
	private WebElement loginTextBox;
	
	@FindBy(xpath = "//span[@id='Email-error']")
	private WebElement errorMassegeEmail;
	
	@FindBy(xpath = "//div[@class='message-error validation-summary-errors']")
	private WebElement errorMassege;
	
	// Page Object
	public String getErrorMessageAtEmailTextBox() {
		waitForElementVisible(driver, errorMassegeEmail);
		return getElementText(driver, errorMassegeEmail);
	}

	public void clickToLoginButton() {
		waitForElementClick(driver, loginTextBox);
		clickToElement(driver, loginTextBox);
	}

	public void inputToEmailTextbox(String email) {
		waitForElementClick(driver,emailTextBox);
		sendkeyToElement(driver, emailTextBox,  email);
	}

	public String getErrorMessageUnsuccessfull() {
		waitForElementVisible(driver, errorMassege);
		return getElementText(driver, errorMassege);
	}

	public void inputToPassWordTextbox(String password) {
		waitForElementClick(driver, passwordTextBox);
		sendkeyToElement(driver, passwordTextBox, password);
		
	}
	
}
