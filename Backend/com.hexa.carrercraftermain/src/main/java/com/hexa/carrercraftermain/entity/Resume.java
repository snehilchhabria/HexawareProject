package com.hexa.carrercraftermain.entity;

import java.time.LocalDate;

import com.hexa.carrercraftermain.dto.ResumeDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;

@Entity
public class Resume {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int resumeId;
	
	@Lob
    byte[] resumeImage;
	
	@Column
	LocalDate uploadDate;
	
	@OneToOne
    @JoinColumn(name = "job_seeker_id", nullable = false)
    JobSeeker jobSeeker;
	
	public Resume() {
		
	}

	public Resume(byte[] resumeImage, LocalDate uploadDate, JobSeeker jobSeeker) {
		super();
		this.resumeImage = resumeImage;
		this.uploadDate = uploadDate;
		this.jobSeeker = jobSeeker;
	}

	public int getResumeId() {
		return resumeId;
	}

	public void setResumeId(int resumeId) {
		this.resumeId = resumeId;
	}

	public byte[] getResumeImage() {
		return resumeImage;
	}

	public void setResumeImage(byte[] resumeImage) {
		this.resumeImage = resumeImage;
	}

	public LocalDate getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(LocalDate uploadDate) {
		this.uploadDate = uploadDate;
	}

	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}
	
	public ResumeDTO mapToDTO() {
		ResumeDTO resdto = new ResumeDTO();
		resdto.setResumeId(this.getResumeId());
		resdto.setResumeImage(this.getResumeImage());
		resdto.setUploadDate(this.getUploadDate());
		resdto.setJobSeekerId(this.getJobSeeker().getUid());
		return resdto;
	}
}
