package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageFactory {
	private long longTimeout = 30;
	
	protected void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	protected String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	protected String getPageCurenURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	protected void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	protected void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	protected void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	protected String getElementText (WebDriver driver, WebElement xpathLocator) {
		return getWebElement(driver,xpathLocator).getText() ;
	}
	protected Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait exlicitWait = new WebDriverWait(driver, longTimeout);
		return exlicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	protected void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}
	
	protected void canceltAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	protected String getTextAlert(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}
	
	protected void senkeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}
	
	protected void SwitchToWindownByID(WebDriver driver, String windownID) {
		Set<String> allWindowIDs = driver.getWindowHandles();

		for (String id : allWindowIDs) {
			if (!id.equals(windownID)) {
				driver.switchTo().window(id);
			}
		}
	}

	protected void switchToWidownByTitle(WebDriver driver, String title) {
		Set<String> idAllWindownTab = driver.getWindowHandles();
		for (String id : idAllWindownTab) {
			driver.switchTo().window(id);
			String titlePage = driver.getTitle();
			if (titlePage.equals(title)) {
				break;
			}
		}
	}

	protected void closeAllTabWithoutParent(WebDriver driver, String parenID) {
		Set<String> idAllTab = driver.getWindowHandles();
		for (String id : idAllTab) {
			if (!id.equals(parenID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parenID);
	}
	
	protected void waitForElementVisible (WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOf(element));
	}
	
	protected void waitForAllElementVisible (WebDriver driver, List<WebElement> element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	protected void waitForElementClick (WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	protected void waitForElementInvisible (WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	protected void waitForAllElementInvisible (WebDriver driver, List<WebElement> element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(element));
	}
	
	protected void clickToElement (WebDriver driver, WebElement element) {
		element.click();
	}
	
	protected void sendkeyToElement (WebDriver driver, WebElement element, String valueText) {
		element.clear();
		element.sendKeys(valueText);
	}
	
	private WebElement getWebElement(WebDriver driver, WebElement xpathLocator) {
		return xpathLocator;
	}
	
	protected boolean isElementDisplayed (WebDriver driver, WebElement xpathLocator) {
		return getWebElement(driver, xpathLocator).isDisplayed();
	}
}
