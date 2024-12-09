package com.hexa.carrercraftermain.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexa.carrercraftermain.dto.AdminDTO;
import com.hexa.carrercraftermain.dto.ApplicationDTO;
import com.hexa.carrercraftermain.dto.JobListingsDTO;
import com.hexa.carrercraftermain.dto.UserDTO;
import com.hexa.carrercraftermain.enums.UserRole;
import com.hexa.carrercraftermain.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<AdminDTO> createAdmin(@RequestBody AdminDTO adminDTO) {
        adminDTO.setUserRole(UserRole.ADMIN);
        AdminDTO createdAdmin = (AdminDTO) userService.saveUser(adminDTO);
        return ResponseEntity.ok(createdAdmin);
    }

    // Get all Job Listings
    @GetMapping("/jobs")
    public ResponseEntity<List<JobListingsDTO>> getAllJobs() {
        List<JobListingsDTO> jobs = userService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }

    // Get all Employers
    @GetMapping("/employers")
    public ResponseEntity<List<UserDTO>> getAllEmployers() {
        List<UserDTO> employers = userService.getAllEmployers();
        return ResponseEntity.ok(employers);
    }

    // Get all JobSeekers
    @GetMapping("/jobseekers")
    public ResponseEntity<List<UserDTO>> getAllJobSeekers() {
        List<UserDTO> jobSeekers = userService.getAllJobSeekers();
        return ResponseEntity.ok(jobSeekers);
    }

    // Get all Applications
    @GetMapping("/applications")
    public ResponseEntity<List<ApplicationDTO>> getAllApplications() {
        return ResponseEntity.ok(userService.getAllApplications());
    }

    // Delete Job by ID
    @DeleteMapping("/job/{jobId}")
    public ResponseEntity<Void> deleteJob(@PathVariable int jobId) {
        userService.deleteJob(jobId);
        return ResponseEntity.noContent().build();
    }

    // Delete User by ID
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    // Get User by ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }
    
    @PutMapping("/profile/{uid}")
    public ResponseEntity<AdminDTO> updateAdminProfile(
            @PathVariable int uid, 
            @RequestBody AdminDTO adminDTO) {
        AdminDTO updatedProfile = userService.updateAdminProfile(uid, adminDTO);
        return ResponseEntity.ok(updatedProfile);
    }
}
