package com.atlantico.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atlantico.auth.model.User;


public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    
}
