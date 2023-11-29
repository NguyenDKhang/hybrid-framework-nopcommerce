package pageUIs.jQuery;

public class HomePageUI {
	public static final String PAGINATION_PAGE_BY_NUMBER = "xpath=//li[@class='qgrd-pagination-page']//a[text()='%s']";
	public static final String PAGINATION_PAGE_ACTIVED_BY_NUMBER= "//li[@class='qgrd-pagination-page']//a[@class ='qgrd-pagination-page-link active' and text()='%s']";
	public static final String HEADER_TEXTBOX_BY_LABEL= "xpath=//div[@class='qgrd-header-text' and text()='%s']/parent::div/following-sibling::input";
	public static final String TOTAL_PAGINATION = "css=ul.qgrd-pagination-ul > li.qgrd-pagination-page";
	public static final String PAGIANTION_PAGE_BY_INDEX = "xpath=//ul[@class='qgrd-pagination-ul']//li[@class='qgrd-pagination-page']/a[text()='%s']";
	public static final String ALL_ROWS_EACH_PAGE = "xpath=//tbody//tr";
	public static final String ALL_ROWS_EACH_COUNTRY_PAGE = "xpath=//tbody//td[@data-key='country']";
	
	// Index của cái cột mà mình cần Enter/ click/ select
	public static final String COLUMN_INDEX_BY_NAME = "xpath=//th[text()='%s']//preceding-sibling::th";
	public static final String TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//tbody//tr[%s]//td[%s]//input";
	public static final String DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//tbody//tr[%s]/td[%s]//select";
	public static final String CLICK_BUTTON_INDEX_AND_ROW_INDEX = "xpath=//tbody//tr[%s]//button[@title='%s']";
	
}
