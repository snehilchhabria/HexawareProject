package com.hexa.carrercraftermain.service;

import java.util.List;

import com.hexa.carrercraftermain.dto.AdminDTO;
import com.hexa.carrercraftermain.dto.ApplicationDTO;
import com.hexa.carrercraftermain.dto.AuthResponse;
import com.hexa.carrercraftermain.dto.EmployerDTO;
import com.hexa.carrercraftermain.dto.JobListingsDTO;
import com.hexa.carrercraftermain.dto.JobSeekerDTO;
import com.hexa.carrercraftermain.dto.LoginDTO;
import com.hexa.carrercraftermain.dto.ResumeDTO;
import com.hexa.carrercraftermain.dto.SignUpDTO;
import com.hexa.carrercraftermain.dto.UserDTO;
import com.hexa.carrercraftermain.entity.Resume;
import com.hexa.carrercraftermain.entity.User;

public interface UserService {
    // Common user management
    UserDTO saveUser(UserDTO userDTO);


    // JobSeeker-specific methods
    List<JobListingsDTO> searchJobs(String keyword);

    ApplicationDTO createApplication(ApplicationDTO applicationDTO);

    ResumeDTO createOrUpdateResume(Integer jobSeekerId, ResumeDTO resumeDTO);


    
    
    // Employer-specific methods
    JobListingsDTO postJob(JobListingsDTO jobListingDTO);

    List<ApplicationDTO> viewApplications(int jobListingId);
    
    List<JobListingsDTO> getEmployerJobListings(Integer employerId);
    
    
    
   
    
    

    // Admin-specific methods
    
    String createUser(SignUpDTO signUpRequest);

	AuthResponse loginUser(LoginDTO loginRequest);

    List<JobListingsDTO> getAllJobs();
    
    
    List<UserDTO> getAllEmployers();

    List<UserDTO> getAllJobSeekers();

    List<ApplicationDTO> getAllApplications();

    void deleteJob(int jobId);
    
    void deleteUserById(Integer uid);
    
    List<UserDTO> getAllUsers();
    
    UserDTO getUserById(Integer uid);
    
    public EmployerDTO updateEmployerProfile(int uid, EmployerDTO employerDTO);
    
    JobSeekerDTO updateJobSeekerProfile(int uid, JobSeekerDTO jobSeekerDTO);
    
    AdminDTO updateAdminProfile(int uid, AdminDTO adminDTO);


    public ResumeDTO getResumeByJobSeekerId(int jobSeekerId);


	ApplicationDTO viewApplicationByUid(Integer uid);
    
    
    }
