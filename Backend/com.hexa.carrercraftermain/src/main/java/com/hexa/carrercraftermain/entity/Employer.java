package com.hexa.carrercraftermain.entity;

import java.time.LocalDate;
import java.util.List;

import com.hexa.carrercraftermain.dto.EmployerDTO;
import com.hexa.carrercraftermain.enums.UserRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Employer extends User{
	
	@Column(nullable = false)
    String companyName;
	
	@Column
	String website;
	
	@Column
    String companyDetails;
	
	@OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, orphanRemoval = true)
    List<JobListings> jobListings;
	
	public Employer() {
		
	}
	
	

	public Employer(String name, String email, String address, String password, String mob, LocalDate dob, UserRole userRole,String companyName, String website, String companyDetails, List<JobListings> jobListings) {
		super(name, email, address, password, mob, dob, userRole);
		
		this.companyName = companyName;
		this.website = website;
		this.companyDetails = companyDetails;
		this.jobListings = jobListings;
	}



	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getCompanyDetails() {
		return companyDetails;
	}

	public void setCompanyDetails(String companyDetails) {
		this.companyDetails = companyDetails;
	}

	public List<JobListings> getJobListings() {
		return jobListings;
	}

	public void setJobListings(List<JobListings> jobListings) {
		this.jobListings = jobListings;
	}
	
	public EmployerDTO mapToDTO(){
		EmployerDTO dto = new EmployerDTO();
		dto.setUid(this.getUid());
	    dto.setName(this.getName());
	    dto.setEmail(this.getEmail());
	    dto.setAddress(this.getAddress());
	    dto.setPassword(this.getPassword());
	    dto.setMob(this.getMob());
	    dto.setDob(this.getDob());
	    dto.setUserRole(this.getUserRole());
	    dto.setCompanyName(this.getCompanyName());
	    dto.setWebsite(this.getWebsite());
	    dto.setCompanyDetails(this.getCompanyDetails());
	    dto.setJobListings(this.getJobListings());
		return dto;
	}
	
}
