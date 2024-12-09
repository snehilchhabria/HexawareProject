package com.hexa.carrercraftermain.entity;

import java.time.LocalDate;

import com.hexa.carrercraftermain.dto.ApplicationDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Applications {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int applicationId;
	
	@Column(nullable = false)
	String status;
	
	@Column
	LocalDate appliedDate;
	
	@ManyToOne
    @JoinColumn(name = "job_seeker_id", nullable = false)
    JobSeeker applicant;
	
	@ManyToOne
    @JoinColumn(name = "job_listing_id", nullable = false)
    JobListings jobListings;
	
	@OneToOne
    @JoinColumn(name = "resume_id", nullable = false)
    Resume resume;
	
	public Applications() {
		
	}

	public Applications(String status, LocalDate appliedDate, JobSeeker applicant, JobListings jobListings, Resume resume) {
		super();
		this.status = status;
		this.appliedDate = appliedDate;
		this.applicant = applicant;
		this.jobListings = jobListings;
		this.resume = resume;
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(LocalDate appliedDate) {
		this.appliedDate = appliedDate;
	}

	public JobSeeker getApplicant() {
		return applicant;
	}

	public void setApplicant(JobSeeker applicant) {
		this.applicant = applicant;
	}

	public JobListings getJobListings() {
		return jobListings;
	}

	public void setJobListings(JobListings jobListings) {
		this.jobListings = jobListings;
	}

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}
	
	public ApplicationDTO mapToDTO() {
        return new ApplicationDTO(
            this.applicationId,
            this.status,
            this.appliedDate,
            this.applicant != null ? this.applicant.getUid() : 0,  // Only ID
            this.jobListings != null ? this.jobListings.getJobId() : 0,  // Only ID
            this.resume != null ? this.resume.getResumeId() : 0  // Only ID
        );
    }
	
}
