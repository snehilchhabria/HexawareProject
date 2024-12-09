package com.hexa.carrercraftermain.dto;

import java.time.LocalDate;

import com.hexa.carrercraftermain.entity.Admin;
import com.hexa.carrercraftermain.enums.UserRole;

public class AdminDTO extends UserDTO{
	
	String privllage;
	
	public AdminDTO() {
		
	}

	public AdminDTO(String name, String email, String address, String password, String mob, LocalDate dob, UserRole userRole, String privllage) {
		super(name, email, address, password, mob, dob, userRole);
		super.setUserRole(UserRole.ADMIN);
		this.privllage = privllage;
	}

	public String getPrivllage() {
		return privllage;
	}

	public void setPrivllage(String privllage) {
		this.privllage = privllage;
	}
	
	public Admin mapToEntity() {
        Admin admin = new Admin();
        admin.setName(this.name);
        admin.setEmail(this.email);
        admin.setAddress(this.address);
        admin.setPassword(this.password);
        admin.setMob(this.mob);
        admin.setDob(this.dob);
        admin.setUserRole(this.userRole.ADMIN);
        admin.setPrivllage(this.privllage);
        return admin;
    }
	
	
}
