package com.hexa.carrercraftermain.controller;

import com.hexa.carrercraftermain.dto.EmployerDTO;
import com.hexa.carrercraftermain.dto.JobListingsDTO;
import com.hexa.carrercraftermain.enums.UserRole;
import com.hexa.carrercraftermain.dto.ApplicationDTO;
import com.hexa.carrercraftermain.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employer")
public class EmployerController {

    private final UserService userService;

    public EmployerController(UserService userService) {
        this.userService = userService;
    }

    // Create Employer
    @PostMapping("/create")
    public ResponseEntity<EmployerDTO> createEmployer(@RequestBody EmployerDTO employerDTO) {
        employerDTO.setUserRole(UserRole.EMPLOYER);
        EmployerDTO createdEmployer = (EmployerDTO) userService.saveUser(employerDTO);
        return ResponseEntity.ok(createdEmployer);
    }

    // Post a Job
    @PostMapping("/post-job")
    public ResponseEntity<JobListingsDTO> postJob(@RequestBody JobListingsDTO jobListingsDTO) {
        JobListingsDTO createdJob = userService.postJob(jobListingsDTO);
        return ResponseEntity.ok(createdJob);
    }

    // View Applications for a Job
    @GetMapping("/job/{jobId}/applications")
    public ResponseEntity<List<ApplicationDTO>> viewApplications(@PathVariable int jobId) {
        List<ApplicationDTO> applications = userService.viewApplications(jobId);
        return ResponseEntity.ok(applications);
    }

    // View all Job Listings posted by Employer
    @GetMapping("/my-jobs/{employerId}")
    public ResponseEntity<List<JobListingsDTO>> getEmployerJobs(@PathVariable Integer employerId) {
        List<JobListingsDTO> jobs = userService.getEmployerJobListings(employerId);
        return ResponseEntity.ok(jobs);
    }
    
    @PutMapping("/profile/{uid}")
    public ResponseEntity<EmployerDTO> updateEmployerProfile(
            @PathVariable int uid, 
            @RequestBody EmployerDTO employerDTO) {
        EmployerDTO updatedProfile = userService.updateEmployerProfile(uid, employerDTO);
        return ResponseEntity.ok(updatedProfile);
    }
}
