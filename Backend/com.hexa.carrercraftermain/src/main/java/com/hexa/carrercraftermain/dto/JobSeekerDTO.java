package com.hexa.carrercraftermain.dto;

import java.time.LocalDate;
import java.util.List;

import com.hexa.carrercraftermain.entity.Applications;
import com.hexa.carrercraftermain.entity.JobSeeker;
import com.hexa.carrercraftermain.entity.Resume;
import com.hexa.carrercraftermain.enums.UserRole;

public class JobSeekerDTO extends UserDTO {
	
     String highestEducation;
     
     String skills;
     
     List<Applications> applications; 
     
     Resume resume;

     public JobSeekerDTO() {
    	 
    	 }

	public JobSeekerDTO(String name, String email, String address, String password, String mob, LocalDate dob, UserRole userRole,String highestEducation, String skills, List<Applications> applications, Resume resume) {
		super(name, email, address, password, mob, dob, userRole);
		
		this.highestEducation = highestEducation;
		this.skills = skills;
		this.applications = applications;
		this.resume = resume;
	}

	public String getHighestEducation() {
		return highestEducation;
	}

	public void setHighestEducation(String highestEducation) {
		this.highestEducation = highestEducation;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public List<Applications> getApplications() {
		return applications;
	}

	public void setApplications(List<Applications> applications) {
		this.applications = applications;
	}

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}
     
    
	public JobSeeker mapToEntity() {
	    JobSeeker jobSeeker = new JobSeeker();
	    jobSeeker.setUid(this.getUid());
	    jobSeeker.setName(this.getName());
	    jobSeeker.setEmail(this.getEmail());
	    jobSeeker.setAddress(this.getAddress());
	    jobSeeker.setPassword(this.getPassword());
	    jobSeeker.setMob(this.getMob());
	    jobSeeker.setDob(this.getDob());
	    jobSeeker.setUserRole(this.getUserRole());
	    jobSeeker.setHighestEducation(this.highestEducation);
	    jobSeeker.setSkills(this.skills);
	    jobSeeker.setResume(this.getResume());
	    jobSeeker.setApplications(this.applications);
	    return jobSeeker;
	}
	
     
}

    