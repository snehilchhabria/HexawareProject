package com.hexa.carrercraftermain.dto;

import java.time.LocalDate;

import com.hexa.carrercraftermain.entity.Resume;

public class ResumeDTO {
    private int resumeId;
    private byte[] resumeImage;
    private LocalDate uploadDate;
    private int jobSeekerId; // Only store JobSeeker ID

    // Getters and Setters
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

    public int getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(int jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    // Map to Entity
    public Resume mapToEntity() {
        Resume resume = new Resume();
        resume.setResumeId(this.resumeId);
        resume.setResumeImage(this.resumeImage);
        resume.setUploadDate(this.uploadDate);
        return resume;
    }
}
