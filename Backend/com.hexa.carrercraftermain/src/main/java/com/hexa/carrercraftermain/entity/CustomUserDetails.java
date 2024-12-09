package com.hexa.carrercraftermain.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hexa.carrercraftermain.enums.UserRole;

public class CustomUserDetails implements UserDetails {

    private int uid;
    private String username;
    private String password;
    private UserRole userRole;

    public CustomUserDetails(int uid, String username, String password, UserRole userRole) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    public int getUid() {
        return uid;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + userRole.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

