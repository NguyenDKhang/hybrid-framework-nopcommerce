package pageObject.wordpress;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager_WordPressAdmin {
	
	public static AdminDashboardPO getAdminDashboardPO(WebDriver driver) {
		return new AdminDashboardPO(driver);
	}
	
	public static AdminLoginPO getAdminLoginPO(WebDriver driver) {
		return new AdminLoginPO(driver);
	}
	
	
	public static AdminPostAddNewPO getAdminPostAddNewPO(WebDriver driver) {
		return new AdminPostAddNewPO(driver);
	}
	
	public static AdminPostCategoriesPO getAdminPostCategoriesPO(WebDriver driver) {
		return new AdminPostCategoriesPO(driver);
	}
	
	public static AdminPostSearchPO getAdminPostSearchPO(WebDriver driver) {
		return new AdminPostSearchPO(driver);
	}
	
	public static AdminProductAddNewPO getAdminProductAddNewPO(WebDriver driver) {
		return new AdminProductAddNewPO(driver);
	}
	
	public static AdminProductAttributesPO getAdminProductAttributesPO(WebDriver driver) {
		return new AdminProductAttributesPO(driver);
	}
	
	public static AdminProductCategoriesPO getAdminProductCategoriesPO(WebDriver driver) {
		return new AdminProductCategoriesPO(driver);
	}

	public static AdminProductTagsPO getAdminProductTagsPO(WebDriver driver) {
		return new AdminProductTagsPO(driver);
	}
	
	public static UserHomePO getUserHomePOP(WebDriver driver) {
		return new UserHomePO(driver);
	}
	
	public static UserPostDetailPO getUserPostDetailPO(WebDriver driver) {
		return new UserPostDetailPO(driver);
	}
	
	public static UserSearchPostPO getUserSearchPostPO(WebDriver driver) {
		return new UserSearchPostPO(driver);
	}
	
	
}
