package com.atlantico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atlantico.data.model.Authority;



public interface AuthorityRepository extends JpaRepository<Authority, String> {

	Authority findByName(String name);

}