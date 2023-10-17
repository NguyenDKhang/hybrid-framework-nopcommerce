package commons;

import java.io.File;

public class GlobalConstants {
	// Chứa các hằng số dùng chung cho toàn bộ frame work
	public static final String PORTA_PAGE_URL = "https://demo.nopcommerce.com";
	public static final String ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com/";
	public static final String REGISTOR_PAGE_URL = "https://demo.nopcommerce.com/register";
	public static final String JAVA_VERSION = System.getProperty("java.version");
	
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	// Windows/ MAC/ Linux
	
	// Trỏ về 1 thư mục mặc định của user
	// Window: Downloads
	public static final String  DOWNLOAD_FILE_FODER = PROJECT_PATH + File.separator +"downloadFiles"+ File.separator ;
	public static final String  UPLOAD_FILE_FODER = PROJECT_PATH + File.separator +"uploadFiles"+ File.separator ;
	public static final String  BROWSER_LOG_FODER = PROJECT_PATH + File.separator +"browserDrivers"+ File.separator ;
	public static final String  DRAG_DROP_HTML5 = PROJECT_PATH + File.separator +"dragDropHTML5"+ File.separator ;
	public static final String  REPORTNG_CREENSHOT = PROJECT_PATH + File.separator +"reportNGImages"+ File.separator ;
	public static final String  EXTENT_PATH = PROJECT_PATH + File.separator +"extendReportV2"+ File.separator ;

	public static final String DB_DEV_URL  = "";
	public static final String DB_DEV_USER  = "";
	public static final String DB_DEV_PASS  = "";
	
	public static final String DB_TEST_URL  = "";
	public static final String DB_TEST_USER  = "";
	public static final String DB_TEST_PASS  = "";
	
	public static final long SHORT_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 30;
	public static final long RETRY_TEST_FAIL = 3;
}
