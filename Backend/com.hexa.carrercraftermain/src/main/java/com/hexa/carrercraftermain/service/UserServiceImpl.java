package com.hexa.carrercraftermain.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexa.carrercraftermain.config.JwtProvider;
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
import com.hexa.carrercraftermain.entity.Admin;
import com.hexa.carrercraftermain.entity.Applications;
import com.hexa.carrercraftermain.entity.CustomUserDetails;
import com.hexa.carrercraftermain.entity.Employer;
import com.hexa.carrercraftermain.entity.JobListings;
import com.hexa.carrercraftermain.entity.JobSeeker;
import com.hexa.carrercraftermain.entity.Resume;
import com.hexa.carrercraftermain.entity.User;
import com.hexa.carrercraftermain.enums.UserRole;
import com.hexa.carrercraftermain.repository.AdminRepo;
import com.hexa.carrercraftermain.repository.ApplicationsRepo;
import com.hexa.carrercraftermain.repository.EmployerRepo;
import com.hexa.carrercraftermain.repository.JobListingsRepo;
import com.hexa.carrercraftermain.repository.JobSeekerRepo;
import com.hexa.carrercraftermain.repository.ResumeRepo;
import com.hexa.carrercraftermain.repository.UserRepo;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	AdminRepo adminRepository;
	
    @Autowired
    private UserRepo userRepository;
    
    @Autowired
    JobSeekerRepo jobSeekerRepository;
    
    @Autowired
    EmployerRepo employerRepository;
    

    @Autowired
    private JobListingsRepo jobListingRepository;

    @Autowired
    private ApplicationsRepo applicationRepository;

    @Autowired
    private ResumeRepo resumeRepository;
    
    @Autowired
	 PasswordEncoder passwordEncoder;

	@Autowired
	 JwtProvider jwtProvider;

	@Autowired
	 CustomUserServiceImpl customUserServiceImpl;

    
    // Common user management
    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User user;

        switch (userDTO.getUserRole()) {
            case JOBSEEKER:
                JobSeeker jobSeeker = new JobSeeker();
                jobSeeker.setHighestEducation(((JobSeekerDTO) userDTO).getHighestEducation());
                jobSeeker.setSkills(((JobSeekerDTO) userDTO).getSkills());
                jobSeeker.setUserRole(UserRole.JOBSEEKER);
                user = jobSeeker;
                break;

            case EMPLOYER:
                Employer employer = new Employer();
                employer.setCompanyName(((EmployerDTO) userDTO).getCompanyName());
                employer.setCompanyDetails(((EmployerDTO) userDTO).getCompanyDetails());
                employer.setUserRole(UserRole.EMPLOYER);
                user = employer;
                break;

            case ADMIN:
                Admin admin = new Admin();
                admin.setPrivllage(((AdminDTO) userDTO).getPrivllage());
                admin.setUserRole(UserRole.ADMIN);
                user = admin;
                break;

            default:
                throw new IllegalArgumentException("Invalid user role: " + userDTO.getUserRole());
        }

        // Set common fields
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setAddress(userDTO.getAddress());
        user.setPassword(userDTO.getPassword());
        user.setMob(userDTO.getMob());
        user.setDob(userDTO.getDob());

        // Save the user
        User savedUser = userRepository.save(user);
        return savedUser.mapToDTO();
    }

    
    
    
    
    
    
    @Override
    public UserDTO getUserById(Integer uid) {
        User user = userRepository.findById(uid)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + uid));
        return user.mapToDTO();
    }

    
    
    
    
    
    
    
    
    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(User::mapToDTO)
                .collect(Collectors.toList());
    }

    
    
    
    @Override
    public void deleteUserById(Integer uid) {
        userRepository.deleteById(uid);
    }

    
    
    
    // JobSeeker-specific methods
    @Override
    public List<JobListingsDTO> searchJobs(String keyword) {
        List<JobListings> jobs = jobListingRepository.findAll().stream()
                .filter(job -> job.getJobTitle().contains(keyword) || job.getJobDescription().contains(keyword))
                .collect(Collectors.toList());
        return jobs.stream().map(JobListings::mapToDTO).collect(Collectors.toList());
    }

    
    
    
    
    @Override
    public ApplicationDTO createApplication(ApplicationDTO applicationDTO) {
        // Validate job seeker ID
        Integer jobSeekerId = applicationDTO.getApplicantId();
        JobSeeker jobSeeker = (JobSeeker) userRepository.findById(jobSeekerId)
                .orElseThrow(() -> new RuntimeException("JobSeeker not found with ID: " + jobSeekerId));

        // Validate job listing ID
        JobListings jobListing = jobListingRepository.findById(applicationDTO.getJobListingId())
                .orElseThrow(() -> new RuntimeException("JobListing not found with ID: " + applicationDTO.getJobListingId()));

        // Validate resume existence for the job seeker
        Resume resume = resumeRepository.findByJobSeekerUid(jobSeekerId)
                .orElseThrow(() -> new RuntimeException("Resume not found for JobSeeker with ID: " + jobSeekerId));

        // Check if an application already exists for this job seeker and job listing
        Optional<Applications> existingApplication = applicationRepository.findByApplicantUidAndJobListingsJobId(jobSeekerId, jobListing.getJobId());
        if (existingApplication.isPresent()) {
            throw new RuntimeException("Application already exists for this JobSeeker and JobListing.");
        }

        // Create and populate the Application entity
        Applications application = new Applications();
        application.setApplicant(jobSeeker);
        application.setJobListings(jobListing);
        application.setResume(resume);
        application.setAppliedDate(LocalDate.now());
        application.setStatus(applicationDTO.getStatus() != null ? applicationDTO.getStatus() : "Pending");

        // Save the application to the database
        Applications savedApplication = applicationRepository.save(application);

        // Convert to DTO and return
        return savedApplication.mapToDTO();
    }
    
    
    
    
    @Override
    public ApplicationDTO viewApplicationByUid(Integer uid) {
        // Find the user (JobSeeker) by UID
        JobSeeker jobSeeker = (JobSeeker) userRepository.findById(uid)
                .orElseThrow(() -> new RuntimeException("JobSeeker not found with ID: " + uid));

        // Find the application associated with the job seeker
        Applications application = applicationRepository.findByApplicantUid(uid)
                .orElseThrow(() -> new RuntimeException("Application not found for JobSeeker with ID: " + uid));

        // Convert the application to DTO and return
        return application.mapToDTO();
    }


    
    
    
    
    @Override
    public ResumeDTO createOrUpdateResume(Integer jobSeekerId, ResumeDTO resumeDTO) {
        JobSeeker jobSeeker = (JobSeeker) userRepository.findById(jobSeekerId)
                .orElseThrow(() -> new RuntimeException("JobSeeker not found with ID: " + jobSeekerId));
        
        
        Resume existingResume = resumeRepository.findByJobSeeker(jobSeeker);
        if (existingResume != null) {
            resumeRepository.delete(existingResume);
        }
        
        Resume resume = new Resume();
        resume.setResumeId(resumeDTO.getResumeId()); // For updates, retain existing ID
        resume.setResumeImage(resumeDTO.getResumeImage());
        resume.setUploadDate(LocalDate.now());
        resume.setJobSeeker(jobSeeker);
     
        Resume savedResume = resumeRepository.save(resume);
        return savedResume.mapToDTO();
    }
    
    @Override
    public ResumeDTO getResumeByJobSeekerId(int jobSeekerId) {
        Optional<Resume> resume = resumeRepository.findByJobSeekerUid(jobSeekerId);
        if (resume.isPresent()) {
            // Convert Resume entity to ResumeDTO
            return resume.get().mapToDTO();
        } else {
            throw new RuntimeException("Resume not found for JobSeeker with ID: " + jobSeekerId);
        }
    }

    
    
    
    
    
    


    
    
    
    
    // Employer-specific methods
    @Override
    public JobListingsDTO postJob(JobListingsDTO jobListingsDTO) {
        // Retrieve the Employer entity using the UID in the DTO
        Employer employer = (Employer) userRepository.findById(jobListingsDTO.getEmployer().getUid())
                .orElseThrow(() -> new RuntimeException("Employer not found with ID: " + jobListingsDTO.getEmployer().getUid()));

        // Map DTO to Entity
        JobListings jobListing = jobListingsDTO.mapToEntity();
        jobListing.setEmployer(employer); // Set the employer

        // Save JobListings and return DTO
        JobListings savedJobListing = jobListingRepository.save(jobListing);
        return savedJobListing.mapToDTO();
    }

    
    
    
    
    
    @Override
    public List<ApplicationDTO> viewApplications(int jobListingId) {
        List<Applications> applications = applicationRepository.findByJobListingPostedByUid(jobListingId);
        return applications.stream().map(Applications::mapToDTO).collect(Collectors.toList());
    }
    
    
    @Override
    public List<JobListingsDTO> getEmployerJobListings(Integer employerId) {
        List<JobListings> jobListings = jobListingRepository.findByEmployerUid(employerId);
        return jobListings.stream().map(JobListings::mapToDTO).collect(Collectors.toList());
    }

    
    
    
    
    
    // Admin-specific methods
    
    public String createUser(SignUpDTO req) {
        User existingUser = userRepository.findByEmail(req.getEmail());
        if (existingUser != null) {
            throw new RuntimeException("User already exists with this email: " + req.getEmail());
        }

        // Create base User object
        User newUser;
        switch (req.getUserRole()) {
            case EMPLOYER:
                Employer employer = new Employer();
                employer.setCompanyName(req.getCompanyName());
                employer.setWebsite(req.getWebsite());
                employer.setCompanyDetails(req.getCompanyDetails());
                newUser = employer;
                break;

            case JOBSEEKER:
                JobSeeker jobSeeker = new JobSeeker();
                jobSeeker.setHighestEducation(req.getHighestEducation());
                jobSeeker.setSkills(req.getSkills());
                newUser = jobSeeker;
                break;

            case ADMIN:
                Admin admin = new Admin();
                admin.setPrivllage(req.getPrivilege());
                newUser = admin;
                break;

            default:
                throw new RuntimeException("Invalid user role: " + req.getUserRole());
        }

        // Set common User fields
        newUser.setEmail(req.getEmail());
        newUser.setPassword(passwordEncoder.encode(req.getPassword()));
        newUser.setName(req.getName());
//        newUser.setMob(req.getMob());
//        newUser.setDob(req.getDob());
        newUser.setUserRole(req.getUserRole());

        // Save User
        newUser = userRepository.save(newUser);

        // Generate a JWT token for the new user
        String token = jwtProvider.generateToken(newUser.getEmail(), req.getUserRole().name());

        return token;
    }


    @Override
    public AuthResponse loginUser(LoginDTO req) {
        String email = req.getEmail();
        String password = req.getPassword();
        UserRole userRole = req.getUserRole();

        Authentication authentication = authenticate(email, password, userRole);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(email, userRole.name());
        
        CustomUserDetails customUserDetails = (CustomUserDetails) customUserServiceImpl.loadUserByUsername(email);
        int uid = customUserDetails.getUid(); 

        return new AuthResponse(token, "Login successful!", userRole, uid);
    }

    private Authentication authenticate(String email, String password, UserRole userRole) {
        UserDetails userDetails = customUserServiceImpl.loadUserByUsername(email);
        
        if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid credentials.");
        }

        // Check if the user has the correct role
        boolean hasRole = userDetails.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_" + userRole.name()));

        if (!hasRole) {
            throw new BadCredentialsException("User does not have the correct role.");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }


	 
    @Override
    public List<JobListingsDTO> getAllJobs() {
        return jobListingRepository.findAll().stream()
                .map(JobListings::mapToDTO)
                .collect(Collectors.toList());
    }
    
    
    
    
    

    @Override
    public List<UserDTO> getAllEmployers() {
        return userRepository.findByUserRole(UserRole.EMPLOYER).stream()
                .map(User::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getAllJobSeekers() {
        return userRepository.findByUserRole(UserRole.JOBSEEKER).stream()
                .map(User::mapToDTO)
                .collect(Collectors.toList());
    }


    
    
    
    
    
    @Override
    public List<ApplicationDTO> getAllApplications() {
        return applicationRepository.findAll().stream()
                .map(Applications::mapToDTO)
                .collect(Collectors.toList());
    }

    
    
    
    
    @Override
    public void deleteJob(int jobId) {
        jobListingRepository.deleteById(jobId);
    }
    
    
    
    
    
    
    
    @Override
    @Transactional
    public EmployerDTO updateEmployerProfile(int uid, EmployerDTO employerDTO) {
        User existingUser = userRepository.findById(uid)
            .orElseThrow(() -> new RuntimeException("User not found"));

        if (existingUser instanceof Employer) {
            Employer existingEmployer = (Employer) existingUser;
            existingEmployer.setName(employerDTO.getName());
            existingEmployer.setEmail(employerDTO.getEmail());
            existingEmployer.setAddress(employerDTO.getAddress());
            existingEmployer.setMob(employerDTO.getMob());
            existingEmployer.setDob(employerDTO.getDob());
            existingEmployer.setCompanyName(employerDTO.getCompanyName());
            existingEmployer.setWebsite(employerDTO.getWebsite());
            existingEmployer.setCompanyDetails(employerDTO.getCompanyDetails());
            
            User updatedUser = userRepository.save(existingEmployer);
            return ((Employer) updatedUser).mapToDTO();
        } else {
            existingUser.setName(employerDTO.getName());
            existingUser.setEmail(employerDTO.getEmail());
            existingUser.setAddress(employerDTO.getAddress());
            existingUser.setMob(employerDTO.getMob());
            existingUser.setDob(employerDTO.getDob());
            
            User updatedUser = userRepository.save(existingUser);
            EmployerDTO updatedDTO = new EmployerDTO();
            updatedDTO.setUid(updatedUser.getUid());
            updatedDTO.setName(updatedUser.getName());
            updatedDTO.setEmail(updatedUser.getEmail());
            updatedDTO.setAddress(updatedUser.getAddress());
            updatedDTO.setMob(updatedUser.getMob());
            updatedDTO.setDob(updatedUser.getDob());
            return updatedDTO;
        }}
    
    
    @Override
    @Transactional
    public JobSeekerDTO updateJobSeekerProfile(int uid, JobSeekerDTO jobSeekerDTO) {
        User existingUser = userRepository.findById(uid)
            .orElseThrow(() -> new RuntimeException("User not found"));

        if (existingUser instanceof JobSeeker) {
            JobSeeker existingJobSeeker = (JobSeeker) existingUser;
            existingJobSeeker.setName(jobSeekerDTO.getName());
            existingJobSeeker.setEmail(jobSeekerDTO.getEmail());
            existingJobSeeker.setAddress(jobSeekerDTO.getAddress());
            existingJobSeeker.setMob(jobSeekerDTO.getMob());
            existingJobSeeker.setDob(jobSeekerDTO.getDob());
            existingJobSeeker.setHighestEducation(jobSeekerDTO.getHighestEducation());
            existingJobSeeker.setSkills(jobSeekerDTO.getSkills());
            
            User updatedUser = userRepository.save(existingJobSeeker);
            return ((JobSeeker) updatedUser).mapToDTO();
        } else {
            existingUser.setName(jobSeekerDTO.getName());
            existingUser.setEmail(jobSeekerDTO.getEmail());
            existingUser.setAddress(jobSeekerDTO.getAddress());
            existingUser.setMob(jobSeekerDTO.getMob());
            existingUser.setDob(jobSeekerDTO.getDob());
            
            User updatedUser = userRepository.save(existingUser);
            JobSeekerDTO updatedDTO = new JobSeekerDTO();
            updatedDTO.setUid(updatedUser.getUid());
            updatedDTO.setName(updatedUser.getName());
            updatedDTO.setEmail(updatedUser.getEmail());
            updatedDTO.setAddress(updatedUser.getAddress());
            updatedDTO.setMob(updatedUser.getMob());
            updatedDTO.setDob(updatedUser.getDob());
            return updatedDTO;
        }
    }
    
    
    @Override
    @Transactional
    public AdminDTO updateAdminProfile(int uid, AdminDTO adminDTO) {
        User existingUser = userRepository.findById(uid)
            .orElseThrow(() -> new RuntimeException("User not found"));

        if (existingUser instanceof Admin) {
            Admin existingAdmin = (Admin) existingUser;
            existingAdmin.setName(adminDTO.getName());
            existingAdmin.setEmail(adminDTO.getEmail());
            existingAdmin.setAddress(adminDTO.getAddress());
            existingAdmin.setMob(adminDTO.getMob());
            existingAdmin.setDob(adminDTO.getDob());
            existingAdmin.setPrivllage(adminDTO.getPrivllage());
            
            User updatedUser = userRepository.save(existingAdmin);
            return ((Admin) updatedUser).mapToDTO();
        } else {
            existingUser.setName(adminDTO.getName());
            existingUser.setEmail(adminDTO.getEmail());
            existingUser.setAddress(adminDTO.getAddress());
            existingUser.setMob(adminDTO.getMob());
            existingUser.setDob(adminDTO.getDob());
            
            User updatedUser = userRepository.save(existingUser);
            AdminDTO updatedDTO = new AdminDTO();
            updatedDTO.setUid(updatedUser.getUid());
            updatedDTO.setName(updatedUser.getName());
            updatedDTO.setEmail(updatedUser.getEmail());
            updatedDTO.setAddress(updatedUser.getAddress());
            updatedDTO.setMob(updatedUser.getMob());
            updatedDTO.setDob(updatedUser.getDob());
            return updatedDTO;
        }
    }







	


}
