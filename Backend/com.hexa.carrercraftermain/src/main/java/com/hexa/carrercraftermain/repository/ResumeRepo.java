package com.hexa.carrercraftermain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexa.carrercraftermain.entity.JobSeeker;
import com.hexa.carrercraftermain.entity.Resume;

@Repository
public interface ResumeRepo extends JpaRepository<Resume, Integer>{
	
	Optional<Resume> findByJobSeekerUid(Integer jobSeekerId);
	
	Resume findByJobSeeker(JobSeeker jobSeeker);

	boolean existsByJobSeekerUid(Integer jobSeekerId);

	
}
