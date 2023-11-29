package pageUIs.nopCommerce.user;

public class BasePageNocommerceUI {
	public static final  String MY_PRODUCT_VIEW_PAGE = "xpath=//div[@class='listbox']//a[contains(@href,'productreviews')]";
	public static final  String REWARD_POINT_PAGE = "xpath=//div[@class='listbox']//a[contains(@href,'rewardpoints')]";
	public static final  String ADDRESS_PAGE = "xpath=//div[@class='listbox']//a[contains(@href,'address')]";
	public static final  String LOGOUT_LINK_AT_USER = "xpath=//a[@class='ico-logout']";
	public static final  String LOGOUT_LINK_AT_ADMIN = "xpath=//div[@id='navbarText']//a[text()='Logout']";

	public static final  String DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA = "xpath=//div[@class='listbox']//a[contains(@href,'%s')]";
	public static final  String DYNAMIC_TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
	public static final  String DYNAMIC_BUTTON_BY_TEXT = "xpath=//button[text()='%s']";
	public static final  String DYNAMIC_DROPDOWN_BY_NAME = "xpath=//select[@name='%s']";
	public static final  String DYNAMIC_RADIO_BUTTON_BY_LABLE = "xpath=//label[text()='%s']/preceding-sibling::input";
	public static final  String DYNAMIC_CHECKBOX_BY_LABLE = "xpath=//label[contains(text(),'%s')]/following-sibling::input";
	
}
