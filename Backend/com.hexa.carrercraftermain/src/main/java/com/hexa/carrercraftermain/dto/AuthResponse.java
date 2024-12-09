package com.hexa.carrercraftermain.dto;

import com.hexa.carrercraftermain.enums.UserRole;

public class AuthResponse {
    private String jwt;
    private String message;
    private UserRole userRole;
    int uid;

    public AuthResponse(String jwt, String message, UserRole userRole,int uid) {
        this.jwt = jwt;
        this.message = message;
        this.userRole = userRole;
        this.uid = uid; 
    }

    // Getters and setters
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
    
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}

