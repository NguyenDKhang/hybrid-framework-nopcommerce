package commons;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	// Chứa các hàm dùng chung cho base object
	public static BasePage getBasePageObject() {
		return new BasePage();
	}
	
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
	
	private List<WebElement> getListElement(WebDriver driver,String xpathLocator) {
		return driver.findElements(byElementXpath(xpathLocator));
	}
	
	private By byElementXpath(String xpathLocator) {
		return By.xpath(xpathLocator);
	}
	
	private WebElement getWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(By.xpath(xpathLocator));
	}

	protected void clickToElement (WebDriver driver, String xpathLocator) {
		getWebElement(driver,xpathLocator).click();
	}
	
	protected void sendkeyToElement (WebDriver driver, String xpathLocator, String valueText) {
		WebElement element = getWebElement(driver,xpathLocator);
		element.clear();
		element.sendKeys(valueText);
	}
	
	protected void selectItemInDefaultDropdownBy_Value (WebDriver driver, String xpathLocator, String valueText) {
		Select select = new Select(getWebElement(driver,xpathLocator));
		select.selectByValue(valueText);
	}
	protected void selectItemInDefaultDropdownBy_Text (WebDriver driver, String xpathLocator, String valueText) {
		Select select = new Select(getWebElement(driver,xpathLocator));
		select.selectByVisibleText(valueText);
	}
	protected String getItemInDefaultDropdown (WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver,xpathLocator));
		return select.getFirstSelectedOption().getText();
	}
	
	protected boolean isDropdownMultiple (WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver,xpathLocator));
		return select.isMultiple();
	}
	
	protected void selectItemInCostomDropdown (WebDriver driver, String parentXpath, String childXpath , String valueText) {
		getWebElement(driver,parentXpath).click();
		
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		
		List<WebElement> allItem = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byElementXpath(childXpath)));
		
		for (WebElement item : allItem) {
			
			if(item.getText().trim().equals(valueText)) {
				JavascriptExecutor jExecutor = (JavascriptExecutor) driver;
				jExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver,childXpath));
				item.click();
				break;
			}
		}
	}
	
	protected String getAttrabuteValue (WebDriver driver, String xpathLocator, String attrabuteValue) {
		return getWebElement(driver,xpathLocator).getAttribute(attrabuteValue) ;
	}
	
	protected String getElementText (WebDriver driver, String xpathLocator) {
		return getWebElement(driver,xpathLocator).getText() ;
	}
	
	protected String getCssValue (WebDriver driver, String xpathLocator, String propertyName) {
		return getWebElement(driver,xpathLocator).getCssValue(propertyName);
	}

	protected String getHexaColorFromRGBA (String rgbaName) {
		return Color.fromString(rgbaName).asHex();
	}

	protected int getElementSize (WebDriver driver, String xpathLocator) {
		return getListElement(driver,xpathLocator).size();
	}

	protected void checkTheCheckboxOrRadio (WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (! element.isSelected()) {
			element.click();
		}
	}
	
	protected void unTheCheckbox (WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (element.isSelected()) {
			element.click();
		}
	}
	
	protected boolean isElementDisplayed (WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isDisplayed();
	}
	
	protected boolean isElementEnabled (WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isEnabled();
	}
	
	protected boolean isElementSelected (WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isSelected();
	}

	protected void switchToFramIframe (WebDriver driver, String xpathLocator) {
		driver.switchTo().frame(getWebElement(driver, xpathLocator));
	}
	
	protected void switchToDefaultContent (WebDriver driver, String xpathLocator) {
		driver.switchTo().defaultContent();
	}
	
	protected void hoverMouseToElement (WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, xpathLocator)).perform();
	}

	protected void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	protected void highlightElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, xpathLocator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleep(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	protected void clickToElementByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
	}

	protected void scrollToElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathLocator));
	}

	protected void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, xpathLocator));
	}

	protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	protected String getElementValidationMessage(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
	}

	protected boolean isImageLoaded(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, xpathLocator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	protected void sleep(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void waitForElementVisible (WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byElementXpath(xpathLocator)));
	}
	
	protected void waitForAllElementVisible (WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byElementXpath(xpathLocator)));
	}

	protected void waitForElementClick (WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byElementXpath(xpathLocator)));
	}
	
	protected void waitForElementInvisible (WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byElementXpath(xpathLocator)));
	}
	
	protected void waitForAllElementInvisible (WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver, xpathLocator)));
	}
	private long longTimeout = 30;
}
