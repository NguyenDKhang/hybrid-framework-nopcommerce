package pageObject.wordpress;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.wordpress.AdminPostAddNewUI;

public class AdminPostAddNewPO extends BasePage{
	WebDriver driver;

	public AdminPostAddNewPO(WebDriver driver) {
		this.driver = driver;
	}

	public AdminPostSearchPO openSearchPostPageUrl(String searchPortUrl) {
		openPageUrl(driver, searchPortUrl);
		return PageGeneratorManager_WordPressAdmin.getAdminPostSearchPO(driver);
	}

	public void enterToAddNewPostTitle(String postTitleValue) {
		waitForElementVisible(driver, AdminPostAddNewUI.TITLE_INPUT_TEXTBOX);
		sendkeyToElement(driver, AdminPostAddNewUI.TITLE_INPUT_TEXTBOX, postTitleValue);
	}

	public void enterToAddNewPostBody(String postTitleBody) {
		driver.switchTo().frame(getWebElement(driver, AdminPostAddNewUI.CONTENT_BODY_IFRAM));
		waitForElementClick(driver, AdminPostAddNewUI.POST_BODY_TEXTBOX);
		clickToElementByJS(driver, AdminPostAddNewUI.POST_BODY_TEXTBOX);
		sendkeyToElement(driver, AdminPostAddNewUI.POST_BODY_TEXTBOX, postTitleBody);
		sleep(3);
		driver.switchTo().defaultContent();
	}

	public void clickToPublicButton() {
		waitForElementClick(driver, AdminPostAddNewUI.PUBLIC_BUTTON);
		clickToElement(driver, AdminPostAddNewUI.PUBLIC_BUTTON);
		sleep(3);
	}
	
	public boolean isPostPublicMessageDisplayed() {
		
		waitForElementVisible(driver, AdminPostAddNewUI.MESSAGE_POST_PUBLIC);
		return isElementDisplayed(driver, AdminPostAddNewUI.MESSAGE_POST_PUBLIC);
		
	}

	public void clickToVisual() {
		waitForElementClick(driver, AdminPostAddNewUI.VISUAL_CLICK_BUTTON);		
		clickToElement(driver, AdminPostAddNewUI.VISUAL_CLICK_BUTTON);		
	}

}
