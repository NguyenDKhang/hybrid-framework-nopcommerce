package pageUIs.nopCommerce.user;

public class UserLoginPageUI {
	// Dùng lấy các element để tương tác với page login
	// Ten biến locator => Tên field - loại field
	// VD: email textbox => EMAIL_TEXTBOX
	public static final String EMAIL_TEXTBOX = "xpath=//input[@class='email']";
	public static final String PASSWORD_TEXTBOX ="xpath=//input[@class='password']";
	public static final String LOGIN_BUTTON ="xpath=//button[@class='button-1 login-button']";
	public static final String ERROR_MASSEGE_EMAIL = "xpath=//span[@id='Email-error']";
	public static final String ERROR_MASSEGE = "xpath=//div[@class='message-error validation-summary-errors']";
	
	// là hằng số để không cho gán lại dữ liệu
	
}
