package pageObject.liveGuru;

import org.openqa.selenium.WebDriver;

import pageUI.liveGuru.PageRegisterUIs;

public class PageGeneratorManager {
	
	public static PageHomeObject getDriverPageHomeObject(WebDriver driver) {
		return new PageHomeObject(driver);
	}
	public static PageRegisterObject getDriverPageRegisterObject(WebDriver driver) {
		return new PageRegisterObject(driver);
	}
	public static PageLoginObject getDriverPageLoginObject(WebDriver driver) {
		return new PageLoginObject(driver);
	}
}
