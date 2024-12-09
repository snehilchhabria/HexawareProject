package com.hexa.carrercraftermain.dto;

import java.time.LocalDate;

import com.hexa.carrercraftermain.entity.Applications;
import com.hexa.carrercraftermain.entity.JobListings;
import com.hexa.carrercraftermain.entity.JobSeeker;
import com.hexa.carrercraftermain.entity.Resume;

public class ApplicationDTO {
	
	int applicationId;
	
	String status;
	
	LocalDate appliedDate;
	
	int applicantId;
	
	int jobListingId;
	
	int resumeId;
	
	public ApplicationDTO() {
		
	}

	public ApplicationDTO(int applicationId, String status, LocalDate appliedDate, int applicantId,
			int jobListingId, int resumeId) {
		super();
		this.applicationId = applicationId;
		this.status = status;
		this.appliedDate = appliedDate;
		this.applicantId = applicantId;
		this.jobListingId = jobListingId;
		this.resumeId = resumeId;
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

	public int getApplicantId() {
        return applicantId;
	}

	public void setApplicant(int applicantId) {
		this.applicantId = applicantId;
	}

	public int getJobListingId() {
		return jobListingId;
	}

	public void setJobListings(int jobListingId) {
		this.jobListingId = jobListingId;
	}

	public int getResumeId() {
		return resumeId;
	}

	public void setResume(int resumeId) {
		this.resumeId = resumeId;
	}
	
	 public Applications mapToEntity() {
	        Applications app = new Applications();
	        app.setStatus(this.status);
	        app.setAppliedDate(this.appliedDate);
	        // applicant, jobListings, and resume must be set externally
	        return app;
	    }
	
}
