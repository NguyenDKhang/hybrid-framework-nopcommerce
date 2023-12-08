package utilities;

import java.util.Locale;

import com.github.javafaker.Faker;

public class DataHelper {
	private Locale location = new Locale("en");
	private Faker faker = new Faker(location);
	
	public static DataHelper getDataHelper() {
		return new DataHelper();
	}
	
	// Firtname/ Lastname/ Email/ City/ Phone/ Address/ State/ Pin/ Zip Code/ Country
	
	public String getFirstName() {
		return faker.address().firstName();
	}
	public String getLastName() {
		return faker.address().lastName();
	}
	
	public String getEmailAddress() {
		return faker.internet().emailAddress();
	}
	
	public String getCityName() {
		return faker.address().city();
	}

	public String getPhoneNumber() {
		return faker.phoneNumber().phoneNumber();
	}
	
	public String getAddress() {
		return faker.address().streetAddress();
	}
	
	public String getPassWord() {
		return faker.internet().password(6, 8, true, true);
	}
	
}
