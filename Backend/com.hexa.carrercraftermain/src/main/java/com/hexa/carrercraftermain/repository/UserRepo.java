package com.hexa.carrercraftermain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexa.carrercraftermain.entity.User;
import com.hexa.carrercraftermain.enums.UserRole;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	Optional<User> findById(Integer id);
    User findByEmail(String email);
    
    
    List<User> findByUserRole(UserRole userRole);
}
