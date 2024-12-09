package com.hexa.carrercraftermain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexa.carrercraftermain.entity.Employer;

@Repository
public interface EmployerRepo extends JpaRepository<Employer, Integer> {
	
	Employer findByEmail(String email);
}
