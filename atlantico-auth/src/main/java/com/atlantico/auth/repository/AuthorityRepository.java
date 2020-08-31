package com.atlantico.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atlantico.auth.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String>{
	
	Authority findByName(String name);
	
}