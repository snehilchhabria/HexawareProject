package com.hexa.carrercraftermain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexa.carrercraftermain.dto.AuthResponse;
import com.hexa.carrercraftermain.dto.LoginDTO;
import com.hexa.carrercraftermain.dto.SignUpDTO;
import com.hexa.carrercraftermain.service.UserServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/user")
public class AuthController {

    @Autowired
    private UserServiceImpl authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody SignUpDTO signUpDTO) throws Exception {
        String jwt = authService.createUser(signUpDTO);
        return ResponseEntity.ok(new AuthResponse(jwt, "Registration successful!", signUpDTO.getUserRole(),0));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginDTO loginDTO) throws Exception {
        AuthResponse response = authService.loginUser(loginDTO);
        return ResponseEntity.ok(response);
    }
}
