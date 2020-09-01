package com.atlantico.service;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Service;

import com.atlantico.converter.DozerConverter;
import com.atlantico.data.enuns.Authorities;
import com.atlantico.data.model.Authority;
import com.atlantico.data.model.User;
import com.atlantico.data.vo.UserCreateVO;
import com.atlantico.data.vo.UserResultVO;
import com.atlantico.data.vo.UserUpdateVO;
import com.atlantico.exception.BadRequestException;
import com.atlantico.exception.ResourceNotFoundException;
import com.atlantico.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;
    
	@Autowired
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}


	public void deleteByEmail(String email) {
		User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		userRepository.delete(user);
	}
	public UserResultVO save(@Valid UserCreateVO user) {
		if(user.notValid()) {
			throw new BadRequestException("params not setted");
		}
		
		User entity = new User();
		entity.setEmail(user.getEmail());
		entity.setPassword(encoder.encode(user.getPassword()));
		entity.setUsername(user.getUsername());
		entity.setCreatedDate(new Date());
		Set<Authority> authorises = user
				.getAuthorities()
				.stream()
				.map(s -> new Authority(s))
				.collect(Collectors.toSet());

		entity.setAuthorities(authorises);
		
		User userSaved = userRepository.save(entity);
		return new UserResultVO(userSaved.getUsername(), userSaved.getEmail(),userSaved.getAuthorities());
	}

	public UserResultVO update(@Valid UserUpdateVO res, String email) {
		if(res.notValid()) {
			throw new BadRequestException("params not setted");
		}
		
		
		if(!existsByEmail(email)) {
			throw new BadRequestException("Email in use.");
		}
		User entity = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this EMAIL"));
	
		if(Objects.nonNull(res.getPassword()) && !res.getPassword().isEmpty() && isAdmin()) {
			entity.setPassword(encoder.encode(res.getPassword()));
		}
		
		
		Set<Authority> authorises = res
					.getAuthorities()
					.stream()
					.map(s -> new Authority(s))
					.collect(Collectors.toSet());

		entity.setAuthorities(authorises);

		entity.setUsername(res.getUsername());
		entity.setUpdatedDate(new Date());
		User user = userRepository.save(entity);
		return new UserResultVO(user.getUsername(), user.getEmail(),user.getAuthorities());
	}

	private boolean isAdmin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getAuthorities().stream()
		     .map(r -> r.getAuthority())
		     .anyMatch(r -> r.equals(Authorities.ROLE_ADMIN.name()));
	}

	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}


	public List<UserResultVO> findAll() {
		return userRepository.findAll().stream().map(u -> new UserResultVO(u.getUsername(), u.getEmail(), u.getAuthorities())).collect(Collectors.toList()); 
	}

	public UserResultVO findByEmail(String email) {
		return userRepository.findByEmail(email)
				.map(u -> new UserResultVO(u.getUsername(), u.getEmail(), u.getAuthorities()))
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this EMAIL"));
	}


	public boolean existsByName(String username) {
		return userRepository.existsByUsername(username);
	}

	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}


	public List<String> findEmailAllAdmin() {
		return userRepository.findEmailAllAdmin();
	}

}
