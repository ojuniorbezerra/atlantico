package com.atlantico.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlantico.repository.AuthorityRepository;

@Service
@Transactional
public class AuthorityService {

	private AuthorityRepository authorityRepository;

	@Autowired
	public AuthorityService(AuthorityRepository authorityRepository) {
		super();
		this.authorityRepository = authorityRepository;
	}
	
	
	
	
}
