package com.hexa.carrercraftermain.dto;

import java.time.LocalDate;
import java.util.List;

import com.hexa.carrercraftermain.entity.Applications;
import com.hexa.carrercraftermain.entity.Employer;
import com.hexa.carrercraftermain.entity.JobListings;
import com.hexa.carrercraftermain.entity.JobSeeker;

public class JobListingsDTO {
	
	int jobId;
	
	String jobTitle;
	
	String company;
	
	String jobDescription;
	
	String location;
	
	String requirements;
	
	int salary;
	
	LocalDate postedDate;
	
	List<Applications> applications;
	
	Employer employer;
	
	public JobListingsDTO(){
		
	}

	public JobListingsDTO(int jobId, String jobTitle, String company, String jobDescription, String location,
			String requirements, int salary, LocalDate postedDate, List<Applications> applications, Employer employer) {
		super();
		this.jobId = jobId;
		this.jobTitle = jobTitle;
		this.company = company;
		this.jobDescription = jobDescription;
		this.location = location;
		this.requirements = requirements;
		this.salary = salary;
		this.postedDate = postedDate;
		this.applications = applications;
		this.employer = employer;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public LocalDate getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(LocalDate postedDate) {
		this.postedDate = postedDate;
	}

	public List<Applications> getApplications() {
		return applications;
	}

	public void setApplications(List<Applications> applications) {
		this.applications = applications;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}
	
	
	public JobListings mapToEntity() {
		JobListings job = new JobListings();
	    job.setJobTitle(this.getJobTitle());
	    job.setCompany(this.getCompany());
	    job.setJobDescription(this.getJobDescription());
	    job.setLocation(this.getLocation());
	    job.setRequirements(this.getRequirements());
	    job.setSalary(this.getSalary());
	    job.setPostedDate(this.getPostedDate());
	    return job;
	}
	
	
}
