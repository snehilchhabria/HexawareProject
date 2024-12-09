package com.hexa.carrercraftermain.dto;

import java.time.LocalDate;

import com.hexa.carrercraftermain.entity.User;
import com.hexa.carrercraftermain.enums.UserRole;

public class UserDTO {
	 int uid;
     String name;
     String email;
     String address;
     String password;
     String mob;
     LocalDate dob;
     UserRole userRole;

    public UserDTO() {}

    public UserDTO(String name, String email, String address, String password, String mob, LocalDate dob, UserRole userRole) {
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
    
    public User mapToEntity() {
        User user = new User();
        user.setUid(this.uid);
        user.setName(this.name);
        user.setEmail(this.email);
        user.setAddress(this.address);
        user.setPassword(this.password);
        user.setMob(this.mob);
        user.setDob(this.dob);
        user.setUserRole(this.userRole);
        return user;
    }
}
