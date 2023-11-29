package pageObject.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.AdminDashboardUI;

public class AdminDashboardPO extends BasePage{
	WebDriver driver;

	public AdminDashboardPO(WebDriver driver) {
		this.driver = driver;
	}

	public AdminPostSearchPO clickToPostMenuLink() {
		waitForElementClick(driver, AdminDashboardUI.POST_MENU_LINK);
		clickToElement(driver, AdminDashboardUI.POST_MENU_LINK);
		return PageGeneratorManager_WordPressAdmin.getAdminPostSearchPO(driver);
	}
	
}
