package com.wordpress.admin;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.wordpress.AdminDashboardPO;
import pageObject.wordpress.AdminLoginPO;
import pageObject.wordpress.AdminPostAddNewPO;
import pageObject.wordpress.AdminPostSearchPO;
import pageObject.wordpress.PageGeneratorManager_WordPressAdmin;
import pageObject.wordpress.UserHomePO;
import pageObject.wordpress.UserPostDetailPO;
import pageObject.wordpress.UserSearchPostPO;


public class Post_01_Create_Read_Update_Delete_Search extends BaseTest{
	WebDriver driver;
	private String adminUser = "demo_web";
	private String adminPass = "123456";
	private String searchPortUrl="";
	private String postTitle="Live code "+ random();
	private String postBody="Live code "+ random();
	private String editPostTitle="Edit title "+ random();
	private String editPostBody="Edit body "+ random();
	private String authorName ="demo_web";
	private String adminUrl, endUserUrl;
	private String currentDay = getCurrentDay();
	
	private AdminLoginPO adminLoginPage;
	private AdminDashboardPO adminDashBoard;
	private AdminPostSearchPO adminPostSearch;
	private AdminPostAddNewPO adminPostAddNew;
	private UserHomePO userHome;
	private UserPostDetailPO userPostDetail;
	private UserSearchPostPO userSearchPost;
	
	
	@Parameters({"browser","urlAdmin", "urlUser"})
	@BeforeClass
	public void beforeClass(String browserName, String adminUrl, String endUserUrl) {
		log.info("Pre-Conditon - Step 01: Open browser and Admin site");
		this.adminUrl = adminUrl;
		this.endUserUrl = endUserUrl;
		
		driver = getBrowserDriver(browserName, adminUrl);
		adminLoginPage = PageGeneratorManager_WordPressAdmin.getAdminLoginPO(driver);
		
		log.info("Pre-Conditon - Step 02: Enter to Username textbox with value: " + adminUser);
		adminLoginPage.enterToUsernameTextbox(adminUser);
		
		log.info("Pre-Conditon - Step 03: Enter to PassWord textbox with value: " + adminPass);
		adminLoginPage.enterToPasswordTextbox(adminPass);
	
		log.info("Pre-Conditon - Step 04: Click to button login");
		adminDashBoard = adminLoginPage.clickToLoginButton();
		
	}
	
	@Test
	public void Post_01_Create_New_Post() {
		log.info("Create_Post - Step 01: Click to 'Post' menu link");
		adminPostSearch = adminDashBoard.clickToPostMenuLink();
		
		log.info("Create_Post - Step 02: Click to 'Add New' button");
		searchPortUrl = adminPostSearch.getPageCurenURL(driver);
		adminPostAddNew = adminPostSearch.clickToAddNewButton();
		
		log.info("Create_Post - Step 03: Enter to post title");
		adminPostAddNew.enterToAddNewPostTitle(postTitle);

//		log.info("Create_Post - Step 04: Click body Visual");
//		adminPostAddNew.clickToVisual();
		
		log.info("Create_Post - Step 04: Enter to post body");
		adminPostAddNew.enterToAddNewPostBody(postBody);
		
		log.info("Create_Post - Step 04: Click to 'Pulish' button");
		adminPostAddNew.clickToPublicButton();
		
		log.info("Create_Post - Step 05: Verify 'Post published' message is displayed");
		verifyTrue(adminPostAddNew.isPostPublicMessageDisplayed());
	}
	
	@Test
	public void TC_02_Search_Post() {
		log.info("Search_Post - Step 01: Open 'Search Post' page");
		adminPostSearch = adminPostAddNew.openSearchPostPageUrl(searchPortUrl);
		
		log.info("Search_Port - Step 02: Enter to search textbox");
		adminPostSearch.enterToSearchTextbox(postTitle);
		
		log.info("Search_Port - Step 03: Click to 'Search Posts' button");
		adminPostSearch.clickToSearchPostButton();
		
		log.info("Search_Port - Step 04: Verify Search table contains '"+ postTitle +"'");
		verifyTrue(adminPostSearch.isPostSearchTableDisplayed("title", postTitle));
		
		log.info("Search_Port - Step 05: Verify Search table contains '"+ authorName +"'");
		verifyTrue(adminPostSearch.isPostSearchTableDisplayed("author", authorName));
		
		log.info("Search_Port - Step 06: Open End user site");
		userHome = adminPostSearch.openEndUserSite(driver,this.endUserUrl);
		
		log.info("Search_Port - Step 07: Verify post infor displayed at home");
		verifyTrue(userHome.isPostInfoDisplayedWithPostTitle(postTitle));
		verifyTrue(userHome.isPostInfoDisplayedWithPostBody(postTitle, postBody));
		verifyTrue(userHome.isPostInfoDisplayedWithAuthorName(postTitle, authorName));
		verifyTrue(userHome.isPostInfoDisplayedWithCurrentDay(postTitle, currentDay));
		
		log.info("Search_Port - Step 08: Click to post title");
		userPostDetail = userHome.clickToPostTitle(postTitle);
		
		log.info("Search_Port - Step 09: Verify Post info displayed at Post detail page");
		verifyTrue(userPostDetail.isPostInfoDisplayedWithPostTitle(postTitle));
		verifyTrue(userPostDetail.isPostInfoDisplayedWithPostBody(postTitle, postBody));
		verifyTrue(userPostDetail.isPostInfoDisplayedWithAuthorName(postTitle, authorName));
		verifyTrue(userPostDetail.isPostInfoDisplayedWithCurrentDay(postTitle, currentDay));
		
	}
	
	@Test
	public void TC_03_View_Post() {
		log.info("Edit_Post - Step 01: Open Admin site");
		adminDashBoard = userPostDetail.openAdminSite(driver, this.adminUrl);
			
		log.info("Edit_Post - Step 02: Click to 'Post' menu link");
		adminPostSearch = adminDashBoard.clickToPostMenuLink();
		
		log.info("Edit_Post - Step 03: Enter to search textbox");
		adminPostSearch.enterToSearchTextbox(postTitle);
		
		log.info("Edit_Post - Step 04: Click to 'Search Posts' button");
		adminPostSearch.clickToSearchPostButton();
		
		log.info("Edit_Post - Step 05: Click to post title link and navigate to Edit Post page");
		adminPostAddNew = adminPostSearch.clickToPostTitleLink(postTitle);
		
		log.info("Edit_Post - Step 06: Enter to post title");
		adminPostAddNew.enterToAddNewPostTitle(editPostTitle);

		log.info("Edit_Post - Step 07: Click body Visual");
		adminPostAddNew.clickToVisual();
		
		log.info("Edit_Post - Step 08: Enter to post body");
		adminPostAddNew.enterToAddNewPostBody(editPostBody);
		
		log.info("Edit_Post - Step 09: Click to 'Update' button");
		adminPostAddNew.clickToPublicButton();
		
		log.info("Search_Post - Step 10: Open 'Search Post' page");
		adminPostSearch = adminPostAddNew.openSearchPostPageUrl(searchPortUrl);
		
		log.info("Search_Port - Step 11: Enter to search textbox");
		adminPostSearch.enterToSearchTextbox(editPostTitle);
		
		log.info("Search_Port - Step 12: Click to 'Search Posts' button");
		adminPostSearch.clickToSearchPostButton();
		
		log.info("Search_Port - Step 13: Verify Search table contains '"+ editPostTitle +"'");
		verifyTrue(adminPostSearch.isPostSearchTableDisplayed("title", editPostTitle));
		
		log.info("Search_Port - Step 14: Verify Search table contains '"+ authorName +"'");
		verifyTrue(adminPostSearch.isPostSearchTableDisplayed("author", authorName));
		
		log.info("Search_Port - Step 15: Open End user site");
		userHome = adminPostSearch.openEndUserSite(driver,this.endUserUrl);
		
		log.info("Search_Port - Step 16: Verify post infor displayed at home");
		verifyTrue(userHome.isPostInfoDisplayedWithPostTitle(editPostTitle));
		verifyTrue(userHome.isPostInfoDisplayedWithPostBody(editPostTitle, editPostBody));
		verifyTrue(userHome.isPostInfoDisplayedWithAuthorName(editPostTitle, authorName));
		verifyTrue(userHome.isPostInfoDisplayedWithCurrentDay(editPostTitle, currentDay));
		
		log.info("Search_Port - Step 17: Click to post title");
		userPostDetail = userHome.clickToPostTitle(editPostTitle);
		
		log.info("Search_Port - Step 18: Verify Post info displayed at Post detail page");
		verifyTrue(userPostDetail.isPostInfoDisplayedWithPostTitle(editPostTitle));
		verifyTrue(userPostDetail.isPostInfoDisplayedWithPostBody(editPostTitle, editPostBody));
		verifyTrue(userPostDetail.isPostInfoDisplayedWithAuthorName(editPostTitle, authorName));
		verifyTrue(userPostDetail.isPostInfoDisplayedWithCurrentDay(editPostTitle, currentDay));
		
	}
	
	@Test
	public void TC_04_Edit_Post() {
		log.info("Edit_Post - Step 01: Open Admin site");
		adminDashBoard = userPostDetail.openAdminSite(driver, this.adminUrl);
			
		log.info("Edit_Post - Step 02: Click to 'Post' menu link");
		adminPostSearch = adminDashBoard.clickToPostMenuLink();
		
		log.info("Edit_Post - Step 03: Enter to search textbox");
		adminPostSearch.enterToSearchTextbox(editPostTitle);
		
		log.info("Edit_Post - Step 04: Click to 'Search Posts' button");
		adminPostSearch.clickToSearchPostButton();
		
		
		
	}

	@Test
	public void TC_05_Delete_Post() {
		log.info("Delete_Post - Step 05: Select Post detail checkbox");
		adminPostSearch.selectPostCheckboxByTitle();

		log.info("Delete_Post - Step 06: Select 'Move to Trash' item in dropdown");
		adminPostSearch.selectTextItemInActionDropdown("Move to Trash");
		
		log.info("Delete_Post - Step 07: Click to 'Apply' button");
		adminPostSearch.clickToApplyButton();

		log.info("Delete_Post - Step 08: Verfy '1 post moved to Trash.' message is displayed");
		verifyTrue(adminPostSearch.isMoveToTrashMessageDisplayed("1 post moved to the Trash. "));
		
		log.info("Delete_Post - Step 09: Enter to Search textbox");
		adminPostSearch.enterToSearchTextbox(editPostTitle);
		
		log.info("Delete_Post - Step 10: Click to 'Search Posts' button");
		adminPostSearch.clickToSearchPostButton();
		
		log.info("Delete_Post - Step 11: Verfy 'No posts found' message is displayed");
		verifyEquals(adminPostSearch.isNoPostFoundMessageDisplayed(),"No posts found.");
	
		log.info("Delete_Post - Step 12: Open End user site");
		userHome = adminPostSearch.openEndUserSite(driver,this.endUserUrl);
		
		log.info("Delete_Post - Step 13: Verify post title undisplayed at Home page");
		verifyTrue(userHome.isPostInforUndisplayedWithPostTitle(editPostTitle));
		
		log.info("Delete_Post - Step 14: Enter to Search textbox");
		userHome.enterToSearchTextbox(editPostTitle);
		
		log.info("Delete_Post - Step 15: Click to 'Search' button");
		userSearchPost = userHome.clickToSearchButton();
		
		log.info("Delete_Post - Step 16: Verfy 'Nothing found' message is displayed");
		verifyTrue(userSearchPost.isNothingFoundMessageDisplayed("Nothing Found"));
	}
	
	public int random() {
		Random random = new Random();
		return random.nextInt(99999);
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
//		closeBrowserDriver();
	}
}  