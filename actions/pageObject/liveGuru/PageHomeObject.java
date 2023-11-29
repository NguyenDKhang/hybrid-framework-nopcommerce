package pageObject.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.liveGuru.PageHomeUIs;

public class PageHomeObject extends BasePage{
	private WebDriver driver;
	public PageHomeObject(WebDriver driver) {
		this.driver = driver;
	}

	public PageRegisterObject clickToRegister() {
		waitForElementClick(driver, PageHomeUIs.ACCOUNT_LINK);
		clickToElement(driver, PageHomeUIs.ACCOUNT_LINK);
		waitForElementClick(driver, PageHomeUIs.REGISTER_LINK);
		clickToElement(driver, PageHomeUIs.REGISTER_LINK);
		return new PageGeneratorManager().getDriverPageRegisterObject(driver);
	}

	public String getMessageSuccess() {
		waitForElementVisible(driver, PageHomeUIs.MESSAGE_SUCCESS);
		return getElementText(driver, PageHomeUIs.MESSAGE_SUCCESS);
	}

	public PageLoginObject clickToLogout() {
		waitForElementClick(driver, PageHomeUIs.ACCOUNT_LINK);
		clickToElement(driver, PageHomeUIs.ACCOUNT_LINK);
		waitForElementClick(driver, PageHomeUIs.LOGOUT_LINK);
		clickToElement(driver, PageHomeUIs.LOGOUT_LINK);
		return new PageGeneratorManager().getDriverPageLoginObject(driver);
	}

	public String getMessageMyDashboarh() {
		waitForElementVisible(driver, PageHomeUIs.MESSAGE_SUCCESS_MY_DASHBOARH);
		return getElementText(driver, PageHomeUIs.MESSAGE_SUCCESS_MY_DASHBOARH);
	}

	public PageLoginObject clickLogInt() {
		// TODO Auto-generated method stub
		waitForElementClick(driver, PageHomeUIs.ACCOUNT_LINK);
		clickToElement(driver, PageHomeUIs.ACCOUNT_LINK);
		waitForElementClick(driver, PageHomeUIs.LOGIN_LINK);
		clickToElement(driver, PageHomeUIs.LOGIN_LINK);
		return new PageGeneratorManager().getDriverPageLoginObject(driver);
	}

}
