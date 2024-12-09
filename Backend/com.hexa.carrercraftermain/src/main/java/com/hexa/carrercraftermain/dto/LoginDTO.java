package com.hexa.carrercraftermain.dto;

import com.hexa.carrercraftermain.enums.UserRole;

import jakarta.validation.constraints.NotBlank;

public class LoginDTO {
	
	@NotBlank(message = "Email is required")
	String email;
	
	
	String password;
	
	UserRole userRole;
	
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
	
	

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public LoginDTO(@NotBlank(message = "Email is required") String email,
			@NotBlank(message = "Password is required") String password, UserRole userRole) {
		super();
		this.email = email;
		this.password = password;
		this.userRole = userRole;
	}

	public LoginDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}

