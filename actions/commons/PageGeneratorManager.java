package commons;

import org.openqa.selenium.WebDriver;

import pageObject.wordpress.AdminLoginPO;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;

public class PageGeneratorManager {
	
	// dùng để quản lý việc khởi tạo các page object
	
	public static UserHomePageObject getUserHomePageObject(WebDriver driver) {
		return new UserHomePageObject(driver);
	}
	public static UserLoginPageObject getUserLoginPageObject(WebDriver driver) {
		return new UserLoginPageObject(driver);
	}
	public static UserRegisterPageObject getUserRegisterPageObject(WebDriver driver) {
		return new UserRegisterPageObject(driver);
	}
	public static UserCustomerInforPageObject getUserCustomerInforPageObject(WebDriver driver) {
		return new UserCustomerInforPageObject(driver);
	}
	public static UserAddressPageObject getUserAddressPageObject(WebDriver driver) {
		return new UserAddressPageObject(driver);
	}
	public static UserRewardPointPageObject getUserRewardPointPageObject(WebDriver driver) {
		return new UserRewardPointPageObject(driver);
	}
	public static UserMyProductReviewPageObject getUserMyProductReviewPageObject(WebDriver driver) {
		return new UserMyProductReviewPageObject(driver);
	}
	
	public static AdminLoginPageObject getAdminLoginPageObject (WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}
	
	public static AdminDashboardPageObject getDashboarhPageObject (WebDriver driver) {
		return new AdminDashboardPageObject(driver);
	}

	
}
