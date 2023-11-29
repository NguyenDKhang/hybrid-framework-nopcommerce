package pageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.BasePage;
import commons.BasePageFactory;
import pageUIs.nopCommerce.user.UserHomePageUI;

public class HomePageFactory_Object extends BasePageFactory{
private WebDriver driver;
	
	public HomePageFactory_Object(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
	}
	
	// Page UI
	@FindBy(how = How.XPATH, using="//a[@class='ico-register']")
	private WebElement registerLink;
	
	@FindBy(xpath = "//a[@class='ico-login']")
	private WebElement loginLink;

	@FindBy(css = "a[class='ico-account']")
	private WebElement myAccountLink;
	
	// Page Object/ Action
	
	public void clickToRegisterLink() {
		waitForElementClick(driver, registerLink);
		clickToElement(driver, registerLink);
	}
	
	public void clickToLoginLink() {
		waitForElementVisible(driver, loginLink);
		clickToElement(driver, loginLink);
	}

	public boolean isMyAccountLinkDisplayer() {
		waitForElementVisible(driver, myAccountLink);
		return isElementDisplayed(driver, myAccountLink);
	}
	
}
