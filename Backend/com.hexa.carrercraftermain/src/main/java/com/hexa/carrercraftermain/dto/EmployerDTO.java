package com.hexa.carrercraftermain.dto;

import java.time.LocalDate;
import java.util.List;

import com.hexa.carrercraftermain.entity.Employer;
import com.hexa.carrercraftermain.entity.JobListings;
import com.hexa.carrercraftermain.enums.UserRole;

public class EmployerDTO extends UserDTO{
	
	String companyName;
	
	String website;
	
	String companyDetails;
	
	List<JobListings> jobListings;
	
	public EmployerDTO() {
		
	}

	public EmployerDTO(String name, String email, String address, String password, String mob, LocalDate dob, UserRole userRole, String companyName, String website, String companyDetails, List<JobListings> jobListings) {
		super(name, email, address, password, mob, dob, userRole);
		super.setUserRole(UserRole.EMPLOYER);
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
	
	public Employer mapToEntity() {
		Employer emp = new Employer();
		emp.setName(this.name);
        emp.setEmail(this.email);
        emp.setAddress(this.address);
        emp.setPassword(this.password);
        emp.setMob(this.mob);
        emp.setDob(this.dob);
        emp.setUserRole(this.userRole.EMPLOYER);
        emp.setCompanyName(this.getCompanyName());
        emp.setWebsite(this.getWebsite());
        emp.setCompanyDetails(this.getCompanyDetails());
        emp.setJobListings(this.getJobListings());
		return emp;
	}
	
}
