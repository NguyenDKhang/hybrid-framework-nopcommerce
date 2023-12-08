package com.nopcommerce.data;

import java.io.File;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.PrivateBinder;

import commons.GlobalConstants;

public class UserDataMapper {
	
	public static UserDataMapper getUserData() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(new File(GlobalConstants.PROJECT_PATH + "\\resources\\UserData.json"), UserDataMapper.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	@JsonProperty("firstname")
	private String firstname;
	
	@JsonProperty("lastname")
	private String lastname;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("date")
	private String date;
	
	@JsonProperty("month")
	private String month;
	
	@JsonProperty("year")
	private String year;
	
	// Taoj ra class con neu trong file json cos cap do
	@JsonProperty("Login")
	private Login login;
	
	static class Login {
		@JsonProperty("username")
		private String username;
		
		@JsonProperty("password")
		private String password;
	}
	
	// Dùng cho mảng trong json ( dùng cho đối tượng là Object cùng tên biến )
	@JsonProperty("subjects")
	private List<Subject> subjects; 
	// Subject is name class under
	public List<Subject> getSubjects(){
		return subjects;
	}
	
	public static class Subject {
		@JsonProperty("name")
		private String name;
		
		@JsonProperty("point")
		private String point;
		
		public String getName() {
			return name;
		}

		public String getPoint() {
			return point;
		}
	}
	
	
	public String getLoginUsername() {
		return login.username;
	}
	
	public void setLoginUsername(String username) {
		this.login.username = username;
	}
	
	public String getLoginPassword() {
		return login.password;
	}
	
	public void setLoginPassword(String password) {
		this.login.password = password;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
}
