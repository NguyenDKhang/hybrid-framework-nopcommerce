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
	
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageCurenURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait exlicitWait = new WebDriverWait(driver, 30);
		return exlicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}
	
	public void canceltAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	public String getTextAlert(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}
	
	public void senkeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}
	
	public void SwitchToWindownByID(WebDriver driver, String windownID) {
		Set<String> allWindowIDs = driver.getWindowHandles();

		for (String id : allWindowIDs) {
			if (!id.equals(windownID)) {
				driver.switchTo().window(id);
			}
		}
	}

	public void switchToWidownByTitle(WebDriver driver, String title) {
		Set<String> idAllWindownTab = driver.getWindowHandles();
		for (String id : idAllWindownTab) {
			driver.switchTo().window(id);
			String titlePage = driver.getTitle();
			if (titlePage.equals(title)) {
				break;
			}
		}
	}

	public void closeAllTabWithoutParent(WebDriver driver, String parenID) {
		Set<String> idAllTab = driver.getWindowHandles();
		for (String id : idAllTab) {
			if (!id.equals(parenID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parenID);
	}
	
	public List<WebElement> getListElement(WebDriver driver,String xpathLocator) {
		return driver.findElements(byElementXpath(xpathLocator));
	}
	
	public By byElementXpath(String xpathLocator) {
		return By.xpath(xpathLocator);
	}
	
	public WebElement getWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(By.xpath(xpathLocator));
	}

	public void clickToElement (WebDriver driver, String xpathLocator) {
		getWebElement(driver,xpathLocator).click();
	}
	
	public void sendkeyToElement (WebDriver driver, String xpathLocator, String valueText) {
		WebElement element = getWebElement(driver,xpathLocator);
		element.clear();
		element.sendKeys(valueText);
	}
	
	public void selectItemInDefaultDropdown (WebDriver driver, String xpathLocator, String valueText) {
		Select select = new Select(getWebElement(driver,xpathLocator));
		select.selectByValue(valueText);
	}
	
	public String getItemInDefaultDropdown (WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver,xpathLocator));
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropdownMultiple (WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver,xpathLocator));
		return select.isMultiple();
	}
	
	public void selectItemInCostomDropdown (WebDriver driver, String parentXpath, String childXpath , String valueText) {
		getWebElement(driver,parentXpath).click();
		
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		
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
	
	public String getAttrabuteValue (WebDriver driver, String xpathLocator, String attrabuteValue) {
		return getWebElement(driver,xpathLocator).getAttribute(attrabuteValue) ;
	}
	
	public String getElementText (WebDriver driver, String xpathLocator) {
		return getWebElement(driver,xpathLocator).getText() ;
	}
	
	public String getCssValue (WebDriver driver, String xpathLocator, String propertyName) {
		return getWebElement(driver,xpathLocator).getCssValue(propertyName);
	}

	public String getHexaColorFromRGBA (String rgbaName) {
		return Color.fromString(rgbaName).asHex();
	}

	public int getElementSize (WebDriver driver, String xpathLocator) {
		return getListElement(driver,xpathLocator).size();
	}

	public void checkTheCheckboxOrRadio (WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (! element.isSelected()) {
			element.click();
		}
	}
	
	public void unTheCheckbox (WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (element.isSelected()) {
			element.click();
		}
	}
	
	public boolean isElementDisplayed (WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isDisplayed();
	}
	
	public boolean isElementEnabled (WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isEnabled();
	}
	
	public boolean isElementSelected (WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isSelected();
	}

	public void switchToFramIframe (WebDriver driver, String xpathLocator) {
		driver.switchTo().frame(getWebElement(driver, xpathLocator));
	}
	
	public void switchToDefaultContent (WebDriver driver, String xpathLocator) {
		driver.switchTo().defaultContent();
	}
	
	public void hoverMouseToElement (WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, xpathLocator)).perform();
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void highlightElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, xpathLocator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleep(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
	}

	public void scrollToElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathLocator));
	}

	public void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, xpathLocator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
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

	public String getElementValidationMessage(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, xpathLocator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void sleep(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void waitForElementVisible (WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byElementXpath(xpathLocator)));
	}
	
	public void waitForAllElementVisible (WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byElementXpath(xpathLocator)));
	}

	public void waitForElementClik (WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byElementXpath(xpathLocator)));
	}
	
	public void waitForElementInvisible (WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byElementXpath(xpathLocator)));
	}
	
	public void waitForAllElementInvisible (WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver, xpathLocator)));
	}
	

}
