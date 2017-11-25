package com.user.crud.model;

public class UserUpdateModel {
	
	
	public String email;
	
	public String birthDate;

	public UserUpdateModel() {
		
	}

	public UserUpdateModel(String email, String birthDate) {
		
		this.email = email;
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	
	
	
}
