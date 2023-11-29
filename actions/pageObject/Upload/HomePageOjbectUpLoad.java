package pageObject.Upload;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.jQuery.HomePageUI;

public class HomePageOjbectUpLoad extends BasePage{
	private WebDriver driver;
	
	public HomePageOjbectUpLoad(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isFileLoadedByName(String fileName) {
		waitForElementVisible(driver, pageUIs.UploadFile.HomePageUI.FILE_NAME_LOADED, fileName);
		return isElementDisplayed(driver, pageUIs.UploadFile.HomePageUI.FILE_NAME_LOADED, fileName);
	}

	public boolean isFileLinkUpLoadedByName(String fileName) {
		waitForElementVisible(driver, pageUIs.UploadFile.HomePageUI.FILE_NAME_UPLOADED_LINK, fileName);
		return isElementDisplayed(driver, pageUIs.UploadFile.HomePageUI.FILE_NAME_UPLOADED_LINK, fileName);
	}

	public void clickToStartButton() {
		List<WebElement> startButtons = getListWebElement(driver, pageUIs.UploadFile.HomePageUI.START_BUTTON);
		for (WebElement startButton : startButtons) {
			startButton.click();
			sleep(2);
		}
	}

	public boolean isFileImageUpLoadedByName(String fileName) {
		waitForElementVisible(driver, pageUIs.UploadFile.HomePageUI.FILE_NAME_UPLOADED_IMAGE, fileName);
		return isImageLoaded(driver, pageUIs.UploadFile.HomePageUI.FILE_NAME_UPLOADED_IMAGE, fileName);

	}
}
