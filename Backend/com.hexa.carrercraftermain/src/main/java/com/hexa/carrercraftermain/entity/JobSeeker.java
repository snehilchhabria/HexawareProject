package com.hexa.carrercraftermain.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hexa.carrercraftermain.dto.JobSeekerDTO;
import com.hexa.carrercraftermain.enums.UserRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class JobSeeker extends User{
	
	@Column
	String highestEducation;
	
	@Column
	String skills;
	
	@OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true)

	List<Applications> applications;
	
	@OneToOne(mappedBy = "jobSeeker", cascade = CascadeType.ALL, orphanRemoval = true)
	Resume resume;
	
	public JobSeeker(){
		
	}

	public JobSeeker(String name, String email, String address, String password, String mob, LocalDate dob, UserRole userRole,String highestEducation, String skills, List<Applications> applications, Resume resume) {
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
	
	
	public JobSeekerDTO mapToDTO() {
	    JobSeekerDTO dto = new JobSeekerDTO();
	    dto.setUid(this.getUid());
	    dto.setName(this.getName());
	    dto.setEmail(this.getEmail());
	    dto.setAddress(this.getAddress());
	    dto.setPassword(this.getPassword());
	    dto.setMob(this.getMob());
	    dto.setDob(this.getDob());
	    dto.setUserRole(this.getUserRole());
	    dto.setHighestEducation(this.highestEducation);
	    dto.setSkills(this.skills);
	    dto.setResume(this.getResume());
	    dto.setApplications(this.applications);
	    return dto;
	}
	
}
