package com.hexa.carrercraftermain.entity;

import java.time.LocalDate;

import com.hexa.carrercraftermain.dto.UserDTO;
import com.hexa.carrercraftermain.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name= "Users")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int uid;
	
	@Column(name = "Name")
	String name;
	
	@Column(name = "Email",unique = true)
	String email;
	
	@Column(name = "Address")
	String address;
	
	@Column(name = "Password")
	String password;
	
	@Column(name = "Mobile Number")
	String mob;
	
	@Column(name = "Date of Birth")
	LocalDate dob;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "userRole", nullable = false)
    UserRole userRole;
	
	
	public User(){
		
	}
	

	public User( String name, String email, String address, String password, String mob, LocalDate dob,UserRole userRole) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
		this.password = password;
		this.mob = mob;
		this.dob = dob;
		this.userRole = userRole;
	}


	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getMob() {
		return mob;
	}


	public void setMob(String mob) {
		this.mob = mob;
	}


	public LocalDate getDob() {
		return dob;
	}


	public void setDob(LocalDate dob) {
		this.dob = dob;
	}


	public UserRole getUserRole() {
		return userRole;
	}


	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	
	public UserDTO mapToDTO() {
	    UserDTO dto = new UserDTO();
	    dto.setUid(this.uid);
	    dto.setName(this.name);
	    dto.setEmail(this.email);
	    dto.setAddress(this.address);
	    dto.setPassword(this.password);
	    dto.setMob(this.mob);
	    dto.setDob(this.dob);
	    dto.setUserRole(this.userRole);
	    return dto;
	}
	
}
