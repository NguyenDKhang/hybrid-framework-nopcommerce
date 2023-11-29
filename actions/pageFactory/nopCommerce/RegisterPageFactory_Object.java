package pageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;
import pageUIs.nopCommerce.user.UserRegisterPageUI;

public class RegisterPageFactory_Object extends BasePageFactory{
private WebDriver driver;
	
	public RegisterPageFactory_Object(WebDriver driver) {
			PageFactory.initElements(driver, this);
			this.driver = driver;
	}
	// Page UI
	@FindBy(id ="FirstName")
	private WebElement firstNameTextBox;
	
	@FindBy(id="LastName")
	private WebElement lastNameTextBox;
	
	@FindBy(xpath = "//input[@id='Email']")
	private WebElement emailTextBox;
	
	@FindBy(xpath = "//input[@id='Password']")
	private WebElement passWordTextBox;
	
	@FindBy(xpath = "//input[@id='ConfirmPassword']")
	private WebElement confirmPassWordTextBox;
	
	@FindBy(xpath = "//button[@id='register-button']")
	private WebElement registerButton;
	
	@FindBy(xpath = "//span[@id='FirstName-error']")
	private WebElement firstNameErrorMessage;
	
	@FindBy(xpath = "//span[@id='LastName-error']")
	private WebElement lastNameErrorMessage;
	
	@FindBy(xpath = "//span[@id='Email-error']")
	private WebElement emailErrorMessage;
	
	@FindBy(xpath = "//span[@id='Password-error']")
	private WebElement passwordErrorMessage;
	
	@FindBy(xpath = "//span[@id='ConfirmPassword-error']")
	private WebElement confirmPasswordErrorMessage;
	
	@FindBy(xpath = "//div[@class='result']")
	private WebElement registerSuccessMessage;
	
	@FindBy(xpath = "//a[@class='ico-login']")
	private WebElement logoutLink;
	
	@FindBy(xpath = "//div[@class='page-body']//li")
	private WebElement existingEmailErrorMessage;
	
	// Page Object
	public void clickToRegisterButton() {
		waitForElementClick(driver, registerButton);
		clickToElement(driver, registerButton);
	}
	
	public String getErrorMessageFirstNameTextBox() {
		waitForElementVisible(driver, firstNameErrorMessage);
		return getElementText(driver, firstNameErrorMessage);
	}

	public String getErrorMessageLastNameTextBox() {
		waitForElementVisible(driver, lastNameErrorMessage);
		return getElementText(driver, lastNameErrorMessage);
	}

	public String getErrorMessageEmailTextBox() {
		waitForElementVisible(driver, emailErrorMessage);
		return getElementText(driver, emailErrorMessage);
	}

	public String getErrorMessagePasswordTextBox() {
		waitForElementVisible(driver, passwordErrorMessage);
		return getElementText(driver, passwordErrorMessage);
	}

	public String getErrorMessageConfirmPasswordTextBox() {
		waitForElementVisible(driver, confirmPasswordErrorMessage);
		return getElementText(driver, confirmPasswordErrorMessage);
	}
	
	public String getSuccessMessage() {
		waitForElementVisible(driver, registerSuccessMessage);
		return getElementText(driver, registerSuccessMessage);
	}
	
	public String getErrorExistingEmailMessage() {
		waitForElementVisible(driver, existingEmailErrorMessage);
		return getElementText(driver, existingEmailErrorMessage);
	}
	
	public void inputFirstNameTextbox(String firstNam) {
		waitForElementVisible(driver, firstNameTextBox);
		sendkeyToElement(driver, firstNameTextBox,firstNam);
	}

	public void inputLastNameTextbox(String lastName) {
		waitForElementVisible(driver, lastNameTextBox);
		sendkeyToElement(driver, lastNameTextBox,lastName);		
	}

	public void inputEmailTextbox(String email) {
		waitForElementVisible(driver, emailTextBox);
		sendkeyToElement(driver, emailTextBox, email);		
	}

	public void inputPasswordTextbox(String passWord) {
		waitForElementVisible(driver, passWordTextBox);
		sendkeyToElement(driver, passWordTextBox, passWord);
		
	}

	public void inputConfirmPasswordTextbox(String confirmPassWord) {
		waitForElementVisible(driver, confirmPassWordTextBox);
		sendkeyToElement(driver, confirmPassWordTextBox, confirmPassWord);
		
	}
}
