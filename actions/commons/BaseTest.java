package commons;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	protected final Log log;
	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}
	
	@BeforeSuite
	public void initBeforeSuite() {
		
	}
	
	public WebDriver getDriverInstance() {
		return this.driver;
	}
	
	// Chứa các hàm dùng chung cho page test case
	protected WebDriver getBrowserDriver(String browserName, String appUrl) {
		switch (browserName) {
		case "chrome":
//			System.setProperty("webdriver.chrome.driver", pojectPath + "\\browserDrivers\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.args", "--disable-logging");
			System.setProperty("webdriver.chrome.silentOutput", "true");
			// Add extension
			File file = new File(GlobalConstants.PROJECT_PATH + "\\browserExtension\\google-translator.crx");
			ChromeOptions optionsChorme = new ChromeOptions();
			optionsChorme.addExtensions(file);
			driver = new ChromeDriver(optionsChorme);
			break;
		case "h_chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");  
//			options.addArguments("--headless");
//			options.addArguments("-window-size=1920×1080");
			driver = new ChromeDriver(options);
			break;
		case "firefox":
//			System.setProperty("webdriver.gecko.driver", pojectPath + "\\browserDrivers\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			
			// Disable Browser driver log in Console
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,GlobalConstants.PROJECT_PATH + "\\browserLog\\firefox.log");
						
			// Add extension
			FirefoxProfile profile = new FirefoxProfile();
			File translatorFixFox = new File(GlobalConstants.PROJECT_PATH + "\\browserExtension\\to_google_translate-4.2.0.xpi");
			profile.addExtension(translatorFixFox);
			profile.setAcceptUntrustedCertificates(true);
			profile.setAssumeUntrustedCertificateIssuer(false);
			FirefoxOptions firefox = new FirefoxOptions();
			firefox.setProfile(profile);
			
			driver = new FirefoxDriver(firefox); 
			break;
		case "h_firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions optionsFx = new FirefoxOptions();
			optionsFx.addArguments("--headless");
			optionsFx.addArguments("window-size=1920×1080");
			driver = new FirefoxDriver(optionsFx);
			break;
		case "edge":
//			System.setProperty("webdriver.edge.driver", pojectPath + "\\browserDrivers\\msedgedriver.exe");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			throw new RuntimeException("Browser name invailid");
		}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.get(appUrl);
		return driver;
	}
	
	protected WebDriver getBrowserDriver(String browserName) {
//		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		switch (browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			
			// Disable Browser driver log in Console
			System.setProperty("webdriver.chrome.args", "--disable-logging");
			System.setProperty("webdriver.chrome.silentOutput", "true");
			// Add extension
			File file = new File(GlobalConstants.PROJECT_PATH + "\\browserExtension\\google-translator.crx");
			ChromeOptions optionsChorme = new ChromeOptions();
			Map<String,Object> prefs = new HashMap<String,Object>();
			// Cài đặt lưu mặt khẩu
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			// Lưu file donw load
			prefs.put("profile.default_content_setting.popups", 0);
			prefs.put("download.default_directory",GlobalConstants.PROJECT_PATH+ "\\downloadFiles");
			
			optionsChorme.setExperimentalOption("prefs", prefs);
			
			optionsChorme.addExtensions(file);
			// Ngôn ngữ
			optionsChorme.addArguments("--lang=vi");
			// Tắt thông báo 
			optionsChorme.addArguments("--disable-notifications");
			
			
			
			driver = new ChromeDriver(optionsChorme);
			break;
			
		case "h_chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1920×1080");
			driver = new ChromeDriver(options);
			break;
			
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			
			// Disable Browser driver log in Console
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,GlobalConstants.PROJECT_PATH + "\\browserLog\\firefox.log");
						
			// Add extension
			FirefoxProfile profile = new FirefoxProfile();
			File translatorFixFox = new File(GlobalConstants.PROJECT_PATH + "\\browserExtension\\to_google_translate-4.2.0.xpi");
			profile.addExtension(translatorFixFox);
			profile.setAcceptUntrustedCertificates(true);
			profile.setAssumeUntrustedCertificateIssuer(false);
			FirefoxOptions firefox = new FirefoxOptions();
			firefox.setProfile(profile);
			
			driver = new FirefoxDriver(firefox); 
			break;
		case "h_firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions optionsFx = new FirefoxOptions();
			optionsFx.addArguments("headless");
			optionsFx.addArguments("window-size=1920×1080");
			driver = new FirefoxDriver(optionsFx);
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			throw new RuntimeException("Browser name invailid");
		}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		return driver;
	}

	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);

		} catch (Throwable e) {
			pass = false;

			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);

		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);

		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);

		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);

		}
		return pass;
	}
	
	public void deleteAlleruReport(String folderName) {
		try {
			String pathFolderDownload = GlobalConstants.PROJECT_PATH + File.separator + "allure-json";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			if (listOfFiles.length != 0) {
				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
						new File(listOfFiles[i].toString()).delete();
					}
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	protected String getCurrentDate() {
		DateTime nowUTC = new DateTime();
		int day = nowUTC.getDayOfMonth();
		if (day < 10) {
			String dayValue = "0" + day;
			return dayValue;
		}
		return String.valueOf(day);
	}

	protected String getCurrentMonth() {
		DateTime now = new DateTime();
		int month = now.getMonthOfYear();
		if (month < 10) {
			String monthValue = "0" + month;
			return monthValue;
		}
		return String.valueOf(month);
	}

	protected String getCurrentYear() {
		DateTime now = new DateTime();
		return String.valueOf(now.getYear());
	}

	protected String getCurrentDay() {
		return getCurrentDate() + "/" + getCurrentMonth() + "/" + getCurrentYear();
	}
	
	protected void closeBrowserDriver() {
		String cmd = null;
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driver.toString().toLowerCase();
			log.info("Driver instance name = " + driverInstanceName);

			String browserDriverName = null;

			if (driverInstanceName.contains("chrome")) {
				browserDriverName = "chromedriver";
			} else if (driverInstanceName.contains("internetexplorer")) {
				browserDriverName = "IEDriverServer";
			} else if (driverInstanceName.contains("firefox")) {
				browserDriverName = "geckodriver";
			} else if (driverInstanceName.contains("edge")) {
				browserDriverName = "msedgedriver";
			} else if (driverInstanceName.contains("opera")) {
				browserDriverName = "operadriver";
			} else {
				browserDriverName = "safaridriver";
			}

			if (osName.contains("window")) {
				cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
			} else {
				cmd = "pkill " + browserDriverName;
			}

			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	protected void showBrowserConsoleLogs(WebDriver driver) {
		if(driver.toString().contains("chrome")||driver.toString().contains("edge")) {
			LogEntries logs = driver.manage().logs().get("browser");
			List<LogEntry> logList = logs.getAll();
			for(LogEntry logging : logList) {
				if(logging.getLevel().toString().toLowerCase().contains("error")) {
					log.info("--------" + logging.getLevel().toString() + "---------\n" + logging.getMessage());
				}
			}
		}
	}
}
