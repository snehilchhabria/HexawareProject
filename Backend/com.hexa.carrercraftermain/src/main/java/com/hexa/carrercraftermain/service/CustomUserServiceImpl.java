package com.hexa.carrercraftermain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hexa.carrercraftermain.entity.CustomUserDetails;
import com.hexa.carrercraftermain.entity.User;
import com.hexa.carrercraftermain.repository.UserRepo;

@Service
public class CustomUserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = adminRepository.findByEmail(username);

        if (user != null) {
            return new CustomUserDetails(user.getUid(), user.getEmail(), user.getPassword(), user.getUserRole());
        }
        throw new UsernameNotFoundException("User not found.");
    }
}

