package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.admin.AdminDashboarhPageUI;

public class AdminDashboardPageObject extends BasePage{
	private WebDriver driver;

	public AdminDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isDashboardHeaderDisplayed() {
		waitForElementVisible(driver, AdminDashboarhPageUI.HEADER_DASHBOARH);
		return isElementDisplayed(driver, AdminDashboarhPageUI.HEADER_DASHBOARH);
	}
	
}
