package pageObject.Upload;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	
	public static HomePageOjbectUpLoad getHomePage(WebDriver  driver) {
		return new HomePageOjbectUpLoad(driver);
	}
}
