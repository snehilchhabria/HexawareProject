package com.hexa.carrercraftermain.entity;

import java.time.LocalDate;

import com.hexa.carrercraftermain.dto.AdminDTO;
import com.hexa.carrercraftermain.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity

public class Admin extends User{
	
	@Column
	String privllage;
	
	public Admin() {
		
	}

	public Admin(String name, String email, String address, String password, String mob, LocalDate dob, UserRole userRole,String privllage) {
		super(name, email, address, password, mob, dob, userRole);
		this.privllage = privllage;
	}

	public String getPrivllage() {
		return privllage;
	}

	public void setPrivllage(String privllage) {
		this.privllage = privllage;
	}
	
	public AdminDTO mapToDTO() {
        AdminDTO dto = new AdminDTO();
        dto.setName(this.name);
        dto.setEmail(this.email);
        dto.setAddress(this.address);
        dto.setPassword(this.password);
        dto.setMob(this.mob);
        dto.setDob(this.dob);
        dto.setUserRole(this.userRole);
        dto.setPrivllage(this.privllage);
        return dto;
    }
	
}
