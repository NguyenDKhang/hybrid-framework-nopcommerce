package com.nopcommerce.data;

public class UserData {
	// Để vầy có thể gọi
	public static final String FRIST_NAME = "Automation";
	public static final String LAST_NAME = "Au";
	
	// Nếu dùng 1 class chứa nhiều data thì tạo nhiều sub class con như này
	public static class Resgitor{
		public static final String FRIST_NAME = "Automation";
		public static final String LAST_NAME = "Au";
		public static final String EMAIL_ADDRESS = "Automation";
		public static final String PASSWORD = "Automation";
		public static final String DATE = "10";
		public static final String MONTH = "August";
		public static final String YEAR = "1999";
	}
	
	public static class Login{
		public static final String EMAIL_ADDRESS = "Automation";
		public static final String PASSWORD = "Automation";
	}
	
	
}
