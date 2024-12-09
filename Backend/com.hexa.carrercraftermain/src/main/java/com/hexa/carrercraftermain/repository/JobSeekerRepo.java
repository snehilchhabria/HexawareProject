package com.hexa.carrercraftermain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexa.carrercraftermain.entity.JobSeeker;

public interface JobSeekerRepo extends JpaRepository<JobSeeker, Integer>{
	
	JobSeeker findByEmail(String email);

}
