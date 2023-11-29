package com.nopcommerce.TestCase;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;
import pageUIs.nopCommerce.user.BasePageNocommerceUI;

public class Level_08_Switch_Role extends BaseTest{
	// Quản lý việc khởi tạo page
	private WebDriver driver;
	private String firstName , lastName ,userPassWord ,confirmPassWord,userEmail, userName, adminEmail, adminPass;
	
	private UserRegisterPageObject userRegisterPageObject; 
	private UserHomePageObject userHomePageObject; 
	private UserLoginPageObject userLoginPageObject;
	private AdminLoginPageObject adminLoginPageObject;
	private UserCustomerInforPageObject userCustomerInforPage;
	private UserAddressPageObject userAddressPageObject;
	private UserRewardPointPageObject userRewardPointPageObject;
	private UserMyProductReviewPageObject userMyProductReviewPageObject;
	private AdminDashboardPageObject adminDashboardPageObject;
	
	// Cách 1: Khởi tạo trực tiếp trên testCase
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		// Initial (Khởi tạo)
		// Che dấu việc khởi tạo của đối tượng
		
		// Click logout thì bussines quay về trang home page
		userHomePageObject = PageGeneratorManager.getUserHomePageObject(driver);
				
		firstName ="khang" + random();
		lastName="tesst "+random();
		userEmail = "khang"+random()+"@gmasidl.com";
		userPassWord 			   	="ASd1651c61616515";
		confirmPassWord 	="ASd1651c61616515";
		
		adminEmail = "admin@yourstore.com";
		adminPass = "admin";
		
		// create account 
		userRegisterPageObject = userHomePageObject.clickToRegisterLink();
				
		userRegisterPageObject.inputFirstNameTextbox(firstName);
		userRegisterPageObject.inputLastNameTextbox(lastName);
		userRegisterPageObject.inputEmailTextbox(userEmail);
		userRegisterPageObject.inputPasswordTextbox(userPassWord);
		userRegisterPageObject.inputConfirmPasswordTextbox(confirmPassWord);
				
		userRegisterPageObject.clickToRegisterButton();
		
		Assert.assertEquals(userRegisterPageObject.getSuccessMessage(),"Your registration completed");
		
//		userHomePageObject.clickLogOutPage();
		
	}
	@Test
	public void Role_01_User_To_Admin() {
		driver.get(GlobalConstants.PORTA_PAGE_URL);
		userLoginPageObject = userHomePageObject.openLoginPage();
		// Login as User role
		userHomePageObject =userLoginPageObject.loginAsUser(userEmail, userPassWord);
		Assert.assertEquals(userHomePageObject.getTextMyAccount(), "My account");
	
		// Home page -> Customer Infor
		userCustomerInforPage = userHomePageObject.openMyAccountPage();
		
		// Customer Infor -> Click Logout -> home page (Admin)
		userHomePageObject = userCustomerInforPage.clickToLogoutLinkAtUserPage(driver);
		
		// User home Page -> Open Admin page -> Login Page (Admin)
		userHomePageObject.openPageUrl(driver, GlobalConstants.ADMIN_PAGE_URL);
		adminLoginPageObject = PageGeneratorManager.getAdminLoginPageObject(driver);
		
		// Login as Admin role
		adminDashboardPageObject = adminLoginPageObject.loginAsAdmin(adminEmail, adminPass);
		Assert.assertTrue(adminDashboardPageObject.isDashboardHeaderDisplayed());
	
		// Dashboard Page ->Click logout -> Login Page(Admin)
		adminLoginPageObject = adminDashboardPageObject.clickToLogoutLinkAtAdminPage(driver);
	}
	
	@Test
	public void Role_02_Admin_To_User() {
		// Login Page (Admin) -> Open user URl -> Home Page (User)
		adminLoginPageObject.openPageUrl(driver, GlobalConstants.PORTA_PAGE_URL);
		userHomePageObject = PageGeneratorManager.getUserHomePageObject(driver);
		
		// Home Page -> Login Page (User)
		userLoginPageObject = userHomePageObject.openLoginPage();
		
		// Login as User role
		userHomePageObject = userLoginPageObject.loginAsUser(userEmail, userPassWord);
		Assert.assertEquals(userHomePageObject.getTextMyAccount(), "My account");
	}
	

	public int random() {
		Random random = new Random();
		return random.nextInt(99999);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}


}
