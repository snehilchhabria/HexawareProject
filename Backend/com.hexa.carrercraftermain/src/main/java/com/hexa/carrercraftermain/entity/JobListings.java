package com.hexa.carrercraftermain.entity;


import java.time.LocalDate;
import java.util.List;

import com.hexa.carrercraftermain.dto.JobListingsDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
	
@Entity
public class JobListings {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int jobId;
	
	@Column(nullable = false)
	String jobTitle;
	
	@Column
	String company;
	
	@Column
	String jobDescription;
	
	@Column
	String location;
	
	@Column
	String requirements;
	
	@Column
	int salary;
	
	@Column
	LocalDate postedDate;
		
	@OneToMany(mappedBy = "jobListings", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Applications> applications;
	
	
	@ManyToOne
	@JoinColumn(name = "employer_id", nullable = false)
	Employer employer;
	
	public JobListings(){
		
	}
	
	public JobListings(String jobTitle, String company, String jobDescription, String location,String requirements, int salary, LocalDate postedDate) {
		super();
		this.jobTitle = jobTitle;
		this.company = company;
		this.jobDescription = jobDescription;
		this.location = location;
		this.requirements = requirements;
		this.salary = salary;
		this.postedDate = postedDate;
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
	
	
	public Employer getEmployer() {
		return employer;
	}
	
	public void setEmployer(Employer employer) {
		this.employer = employer;	}
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	public JobListingsDTO mapToDTO() {
		JobListingsDTO jobdto = new JobListingsDTO();
		jobdto.setJobId(this.getJobId());
	    jobdto.setJobTitle(this.getJobTitle());
	    jobdto.setCompany(this.getCompany());
	    jobdto.setJobDescription(this.getJobDescription());
	    jobdto.setLocation(this.getLocation());
	    jobdto.setRequirements(this.getRequirements());
	    jobdto.setSalary(this.getSalary());
	    jobdto.setPostedDate(this.getPostedDate());
	    return jobdto;
	}

}