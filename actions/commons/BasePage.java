package commons;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nopcommerce.common.Common_01_Register_Cookie;

import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;
import pageUIs.UploadFile.BasePageUploadUI;
import pageUIs.nopCommerce.user.BasePageNocommerceUI;

public class BasePage {
	// Chứa các hàm dùng chung cho base object
	public static BasePage getBasePageObject() {
		return new BasePage();
	}
	
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
	
	public Set<Cookie> getAllCookie(WebDriver driver){
		return driver.manage().getCookies();
	}
	
	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleep(3);
	}
	
	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait exlicitWait = new WebDriverWait(driver, longTimeout);
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
	
	private List<WebElement> getListElement(WebDriver driver,String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}
	
//	private By getByXpath(String locatorType) {
//		return By.xpath(locatorType);
//	}
	// locatorType: id=/ css=/ xpath=/ name=/ class=
	// locatorType: ID=/ CSS=/ XPATH=/ NAME=/ CLASS=
	// locatorType: Id=/ Css=/ Xpath=/ Name=/ Class=
	private By getByLocator(String locatorType) {
		By by= null;
		if(locatorType.startsWith("id=") || locatorType.startsWith("ID=") ||locatorType.startsWith("Id=") ) {
			by = By.id(locatorType.substring(3));
		}else if(locatorType.startsWith("class=") || locatorType.startsWith("CLASS=") ||locatorType.startsWith("Class=") ) {
			by = By.className(locatorType.substring(6));
		}else if(locatorType.startsWith("name=") || locatorType.startsWith("NAME=") ||locatorType.startsWith("Name=") ) {
			by = By.name(locatorType.substring(5));
		}else if(locatorType.startsWith("css=") || locatorType.startsWith("CSS=") ||locatorType.startsWith("Css=") ) {
			by = By.cssSelector(locatorType.substring(4));
		}else if(locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") ||locatorType.startsWith("Xpath=") ) {
			by = By.xpath(locatorType.substring(6));
		}else {
			throw new RuntimeException("Locator type is not supporter!");
		}
		return by;
	}
	
	private String getDynamicXpath(String locatorType, String... dynamicValues) {
		if(locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("XPath="))
		{
			locatorType = String.format(locatorType, ( Object [] ) dynamicValues);
		}
		return locatorType;
	}
	
	private WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}
	
	public List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}

	public void clickToElement (WebDriver driver, String locatorType) {
		getWebElement(driver, locatorType ).click();
	}

	public void clickToElement (WebDriver driver, String locatorType, String... dynamicValues) {
		getWebElement(driver, getDynamicXpath(locatorType, dynamicValues) ).click();
	}
	
	public void sendkeyToElement (WebDriver driver, String locatorType, String valueText) {
		WebElement element = getWebElement(driver,locatorType);
		element.clear();
		element.sendKeys(valueText);
	}
	
	public void sendkeyToElement (WebDriver driver, String locatorType, String valueText, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(valueText);
	}
	
	public void selectItemInDefaultDropdownBy_Value (WebDriver driver, String locatorType, String valueText) {
		Select select = new Select(getWebElement(driver,locatorType));
		select.selectByValue(valueText);
	}
	
	public void selectItemInDefaultDropdownBy_Value (WebDriver driver, String locatorType, String valueText, String... dynamicValues) {
		Select select = new Select(getWebElement(driver,getDynamicXpath(locatorType, dynamicValues)));
		select.selectByValue(valueText);
	}
	
	public void selectItemInDefaultDropdownBy_Text (WebDriver driver, String locatorType, String valueText) {
		Select select = new Select(getWebElement(driver,locatorType));
		select.selectByVisibleText(valueText);
	}
	
	public String getItemInDefaultDropdown (WebDriver driver, String locatorType) {
			Select select = new Select(getWebElement(driver,locatorType));
			return select.getFirstSelectedOption().getText();
		}
	
	public boolean isDropdownMultiple (WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver,locatorType));
		return select.isMultiple();
	}
	
	public void selectItemInCostomDropdown (WebDriver driver, String parentXpath, String childXpath , String valueText) {
		getWebElement(driver,parentXpath).click();
		
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		
		List<WebElement> allItem = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));
		
		for (WebElement item : allItem) {
			
			if(item.getText().trim().equals(valueText)) {
				JavascriptExecutor jExecutor = (JavascriptExecutor) driver;
				jExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver,childXpath));
				item.click();
				break;
			}
		}
	}
	
	public String getAttrabuteValue (WebDriver driver, String locatorType, String attrabuteValue) {
		return getWebElement(driver,locatorType).getAttribute(attrabuteValue) ;
	}
	
	public String getElementText (WebDriver driver, String locatorType) {
		return getWebElement(driver,locatorType).getText() ;
	}
	
	public String getElementText (WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver,getDynamicXpath(locatorType, dynamicValues)).getText() ;
	}
	
	public String getCssValue (WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver,locatorType).getCssValue(propertyName);
	}
	
	public String getCssValue (WebDriver driver, String locatorType, String propertyName, String... dynamicValues) {
		return getWebElement(driver,getDynamicXpath(locatorType, dynamicValues)).getCssValue(propertyName);
	}

	public String getHexaColorFromRGBA (String rgbaName) {
		return Color.fromString(rgbaName).asHex();
	}

	public int getElementSize (WebDriver driver, String locatorType) {
		return getListElement(driver,locatorType).size();
	}
	
	public int getElementSize (WebDriver driver, String locatorType, String... dynamicValues) {
		return getListElement(driver,getDynamicXpath(locatorType, dynamicValues)).size();
	}

	public void checkTheCheckboxOrRadio (WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (! element.isSelected()) {
			element.click();
		}
	}
	
	public void checkTheCheckboxOrRadio (WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if (! element.isSelected()) {
			element.click();
		}
	}
	
	public void unTheCheckbox (WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}

	public void unTheCheckbox (WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if (element.isSelected()) {
			element.click();
		}
	}
	
	public boolean isElementDisplayed (WebDriver driver, String locatorType) {
		try {
			return getWebElement(driver, locatorType).isDisplayed();
		}catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public boolean isElementDisplayed (WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
	}

	public boolean isElementUndisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		overrideImplicitTimeout(driver,shortTimeout);
		List<WebElement> elements = getListElement(driver, getDynamicXpath(locatorType, dynamicValues));
		overrideImplicitTimeout(driver, longTimeout);
		if(elements.size()==0) {
			System.out.println("Element not in DOM");
			System.out.println("End time = "+ new Date().toString());
			return true;
		}else if (elements.size()>0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible/ displayed");
			System.out.println("End time = "+ new Date().toString());
			return true;
		}else {
			System.out.println("Element in DOM and visible");
			return false;
		}
	}
	public boolean isElementUndisplayed(WebDriver driver, String locatorType) {
		System.out.println("Start time = "+ new Date().toString());
		overrideImplicitTimeout(driver,shortTimeout);
		List<WebElement> elements = getListElement(driver, locatorType);
		overrideImplicitTimeout(driver, longTimeout);
		if(elements.size()==0) {
			System.out.println("Element not in DOM");
			System.out.println("End time = "+ new Date().toString());
			return true;
		}else if (elements.size()>0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible/ displayed");
			System.out.println("End time = "+ new Date().toString());
			return true;
		}else {
			System.out.println("Element in DOM and visible");
			return false;
		}
	}
	
	public void overrideImplicitTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}
	public boolean isElementEnabled (WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}
	public boolean isElementEnabled (WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isEnabled();
	}
	
	public boolean isElementSelected (WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}
	public boolean isElementSelected (WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isSelected();
	}

	public void switchToFramIframe (WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}
	
	public void switchToDefaultContent (WebDriver driver, String locatorType) {
		driver.switchTo().defaultContent();
	}
	
	public void hoverMouseToElement (WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}
	
	public void pressKeyToElement (WebDriver driver, String locatorType, Keys key, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)), key ).perform();
	}
	
	public void pressKeyToElement (WebDriver driver, String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locatorType), key ).perform();
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public String getElementValueByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		xpathLocator = xpathLocator.replace("xpath=", "");
		return(String) jsExecutor.executeScript("return $(document.evaluate(\""+xpathLocator+"\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue).val()");
	}
	
	public void highlightElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleep(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}
	public void clickToElementByJS(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
	}

	public void scrollToElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}
	public void scrollToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
	}

	public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
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

	public String getElementValidationMessage(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locatorType));
		return status;
	}
	
	public boolean isImageLoaded(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
			return status;
	}

	public void sleep(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void waitForElementVisible (WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}
	public void waitForElementVisible (WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public void waitForAllElementVisible (WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}
	public void waitForAllElementVisible (WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	public void waitForElementClick (WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}
	public void waitForElementClick (WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public void waitForElementInvisible (WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}
	
	/*
	 * Wait for element undisplayed in DOM or not in DOM and override implicit timout
	 * */
	public void waitForElementUndisplay(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideImplicitTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
		overrideImplicitTimeout(driver, longTimeout);
	}
	public void waitForElementInvisible (WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public void waitForAllElementInvisible (WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver, locatorType)));
	}
	public void waitForAllElementInvisible (WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver, getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		String filePath = GlobalConstants.UPLOAD_FILE_FODER;
		String fullFileName = "";
		for(String file : fileNames) {
			fullFileName = fullFileName +filePath + file +"\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(driver, BasePageUploadUI.UPLOAD_FILE).sendKeys(fullFileName);
	}
	
	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
	
	// Dùng cho các page chuyển qua chuyển lại các page
	// Tối ưu ở bài học Level_07_Switch_Page
	public UserAddressPageObject openAddressPage(WebDriver driver) {
		waitForElementVisible(driver, BasePageNocommerceUI.ADDRESS_PAGE);
		clickToElement(driver, BasePageNocommerceUI.ADDRESS_PAGE);
		return new PageGeneratorManager().getUserAddressPageObject(driver);
	}
	
	public UserMyProductReviewPageObject openMyProductReviewPage(WebDriver driver) {
		waitForElementVisible(driver, BasePageNocommerceUI.MY_PRODUCT_VIEW_PAGE);
		clickToElement(driver, BasePageNocommerceUI.MY_PRODUCT_VIEW_PAGE);
		return new PageGeneratorManager().getUserMyProductReviewPageObject(driver);
	}
	
	public UserRewardPointPageObject openRewardPointPage(WebDriver driver) {
		waitForElementVisible(driver, BasePageNocommerceUI.REWARD_POINT_PAGE);
		clickToElement(driver, BasePageNocommerceUI.REWARD_POINT_PAGE);
		return new PageGeneratorManager().getUserRewardPointPageObject(driver);
	}
	// Tối ưu ở bài Level_09_Dynamic_Locator
	public BasePage openPageAtMyAccountByName(WebDriver driver, String pageName) {
		waitForElementVisible(driver, BasePageNocommerceUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		clickToElement(driver, BasePageNocommerceUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		
		switch (pageName) {
		case "productreviews":
			return PageGeneratorManager.getUserMyProductReviewPageObject(driver);
		case "rewardpoints":
			return PageGeneratorManager.getUserRewardPointPageObject(driver);
		case "addresses":
			return PageGeneratorManager.getUserAddressPageObject(driver);
		default:
			throw new RuntimeException("Invalid page name My Account area.");
		}
	}
	
	public void openPageAtMyAccountByPageName(WebDriver driver, String pageName) {
		waitForElementVisible(driver, BasePageNocommerceUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		clickToElement(driver, BasePageNocommerceUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		
	}
	
	// Level_08_Switch_Role
	public UserHomePageObject clickToLogoutLinkAtUserPage(WebDriver driver) {
		waitForElementClick(driver, BasePageNocommerceUI.LOGOUT_LINK_AT_USER);
		clickToElement(driver, BasePageNocommerceUI.LOGOUT_LINK_AT_USER);
		return PageGeneratorManager.getUserHomePageObject(driver);
	}
	
	public AdminLoginPageObject clickToLogoutLinkAtAdminPage(WebDriver driver) {
		waitForElementClick(driver, BasePageNocommerceUI.LOGOUT_LINK_AT_ADMIN);
		clickToElement(driver, BasePageNocommerceUI.LOGOUT_LINK_AT_ADMIN);
		return PageGeneratorManager.getAdminLoginPageObject(driver);
	}
}
