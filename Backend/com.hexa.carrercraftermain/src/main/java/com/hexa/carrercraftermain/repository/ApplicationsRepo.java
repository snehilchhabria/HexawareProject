package com.hexa.carrercraftermain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexa.carrercraftermain.entity.Applications;

@Repository
public interface ApplicationsRepo extends JpaRepository<Applications, Integer>{
	
	Optional<Applications> findByApplicantUid(Integer uid);


    // Corrected method to find applications by Employer UID via JobListings
    @Query("SELECT a FROM Applications a WHERE a.jobListings.employer.uid = :employerUid")
    List<Applications> findByJobListingPostedByUid(@Param("employerUid") Integer employerUid);
    
    
    Optional<Applications> findByApplicantUidAndJobListingsJobId(Integer applicantId, Integer jobId);
    
  
}
