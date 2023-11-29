package pageObject.jQuery;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	
	public static HomePageOjbect getHomePage(WebDriver  driver) {
		return new HomePageOjbect(driver);
	}
}
