package com.hexa.carrercraftermain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexa.carrercraftermain.entity.JobListings;

@Repository
public interface JobListingsRepo extends JpaRepository<JobListings, Integer>{
	
    List<JobListings> findByLocation(String location);
    
    List<JobListings> findByJobTitle(String jobTitle);


	List<JobListings> findByCompany(String company);


	List<JobListings> findByLocationAndJobTitleAndCompany(String location, String jobTitle, String company);


	List<JobListings> findByLocationAndJobTitle(String location, String jobTitle );


	List<JobListings> findByLocationAndCompany(String location, String company);


	List<JobListings> findByJobTitleAndCompany(String jobTitle, String company);


	@Query("SELECT j FROM JobListings j WHERE j.employer.uid = :employerUid")
    List<JobListings> findByEmployerUid(@Param("employerUid") int employerUid);
	
}
