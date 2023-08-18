package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private String pojectPath = System.getProperty("user.dir");;
	private WebDriver driverBaseTest;

	
	// Chứa các hàm dùng chung cho page test case
	protected WebDriver getBrowserDriver(String browserName) {
		switch (browserName) {
		case "chrome":
//			System.setProperty("webdriver.chrome.driver", pojectPath + "\\browserDrivers\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driverBaseTest = new ChromeDriver();
			break;
		case "firefox":
//			System.setProperty("webdriver.gecko.driver", pojectPath + "\\browserDrivers\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			driverBaseTest = new FirefoxDriver();
			break;
		case "edge":
//			System.setProperty("webdriver.edge.driver", pojectPath + "\\browserDrivers\\msedgedriver.exe");
			WebDriverManager.edgedriver().setup();
			driverBaseTest = new EdgeDriver();
			break;
		default:
			throw new RuntimeException("Browser name invailid");
		}
		return driverBaseTest;
	}
}
