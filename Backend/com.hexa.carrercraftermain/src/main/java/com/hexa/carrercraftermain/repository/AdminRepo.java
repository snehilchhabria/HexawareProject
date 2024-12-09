package com.hexa.carrercraftermain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexa.carrercraftermain.entity.Admin;
import com.hexa.carrercraftermain.entity.Employer;

import jakarta.transaction.Transactional;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {
	
	Admin findByName(String name);
	
	Admin findByEmail(String email);
	
	@Modifying
    @Transactional
    @Query("DELETE FROM Employer e WHERE e.id = :id")
	void deleteEmployer(@Param("id") Employer emp);
}
