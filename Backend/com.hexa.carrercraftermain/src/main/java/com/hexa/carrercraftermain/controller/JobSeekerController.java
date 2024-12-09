package com.hexa.carrercraftermain.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hexa.carrercraftermain.dto.ApplicationDTO;
import com.hexa.carrercraftermain.dto.JobListingsDTO;
import com.hexa.carrercraftermain.dto.JobSeekerDTO;
import com.hexa.carrercraftermain.dto.ResumeDTO;
import com.hexa.carrercraftermain.entity.Resume;
import com.hexa.carrercraftermain.enums.UserRole;
import com.hexa.carrercraftermain.repository.ResumeRepo;
import com.hexa.carrercraftermain.service.UserService;

@RestController
@RequestMapping("/api/jobseeker")
public class JobSeekerController {

    @Autowired
    private UserService userService;
    
    @Autowired
    ResumeRepo resumeRepository;
    
    @PostMapping("/createjobseeker")
    public ResponseEntity<JobSeekerDTO> createJobSeeker(@RequestBody JobSeekerDTO jobSeekerDTO) {
        jobSeekerDTO.setUserRole(UserRole.JOBSEEKER); // Ensure userRole is set to JOB_SEEKER
        JobSeekerDTO createdJobSeeker = (JobSeekerDTO) userService.saveUser(jobSeekerDTO);
        return ResponseEntity.ok(createdJobSeeker);
    }
    
    
    
    @GetMapping("/search-jobs")
    public ResponseEntity<List<JobListingsDTO>> searchJobs(@RequestParam String keyword) {
        List<JobListingsDTO> jobs = userService.searchJobs(keyword);
        return ResponseEntity.ok(jobs);
    }

    @PostMapping("/apply")
    public ResponseEntity<ApplicationDTO> createApplication(@RequestBody ApplicationDTO applicationDTO) {
        System.out.println("Received Payload: " + applicationDTO);
        try {
            ApplicationDTO createdApplication = userService.createApplication(applicationDTO);
            return ResponseEntity.ok(createdApplication);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    @GetMapping("/viewApplication/{uid}")
    public ResponseEntity<ApplicationDTO> viewApplication(@PathVariable Integer uid) {
        try {
            // Fetch the application details for the given uid
            ApplicationDTO applicationDTO = userService.viewApplicationByUid(uid);
            return ResponseEntity.ok(applicationDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    

    @PostMapping("/upload-resume")
    public ResponseEntity<ResumeDTO> uploadResume(
            @RequestParam Integer jobSeekerId,
            @RequestParam("file") MultipartFile file) {
        ResumeDTO resumeDTO = new ResumeDTO();
        try {
            // Set file metadata
            
            resumeDTO.setResumeImage(file.getBytes());
            resumeDTO.setUploadDate(LocalDate.now()); 

            // Link the resume to the jobSeekerId
            ResumeDTO updatedResume = userService.createOrUpdateResume(jobSeekerId, resumeDTO);
            return ResponseEntity.ok(updatedResume);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    
    
    @GetMapping("/resume-status/{jobSeekerId}")
    public ResponseEntity<Boolean> checkResumeStatus(@PathVariable Integer jobSeekerId) {
        boolean hasResume = resumeRepository.existsByJobSeekerUid(jobSeekerId);
        return ResponseEntity.ok(hasResume);
    }

    
    
    
    
    @PutMapping("/profile/{uid}")
    public ResponseEntity<JobSeekerDTO> updateJobSeekerProfile(
            @PathVariable int uid, 
            @RequestBody JobSeekerDTO jobSeekerDTO) {
        JobSeekerDTO updatedProfile = userService.updateJobSeekerProfile(uid, jobSeekerDTO);
        return ResponseEntity.ok(updatedProfile);
    }
    
    
    
    @GetMapping("/status/{jobSeekerId}")
    public ResponseEntity<?> getResumeStatus(@PathVariable int jobSeekerId) {
        // Fetch ResumeDTO based on JobSeeker ID
        ResumeDTO resumeDTO = userService.getResumeByJobSeekerId(jobSeekerId);
        
        // Return ResumeDTO response
        return ResponseEntity.ok(resumeDTO);
    }
}
