package com.atlantico.controller;


import java.io.Serializable;
import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atlantico.data.vo.UserCreateVO;
import com.atlantico.data.vo.UserResultVO;
import com.atlantico.data.vo.UserUpdateVO;
import com.atlantico.exception.EntityNotFoundException;
import com.atlantico.exception.ResourceNotFoundException;
import com.atlantico.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "User EndPoint", tags = {"UserEndPoint"})
@RestController
@RequestMapping("/v1.0/user")
public class UserController implements Serializable{

	private static final long serialVersionUID = -2738077696172323867L;

	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "get all database users")
	@GetMapping
	public List<UserResultVO> all() {
       return userService.findAll();
   }
	
	@ApiOperation(value = "get user by email")
	@GetMapping("/{email}")
	public ResponseEntity<?> getUserByEmail( @PathVariable String email) {
       return ResponseEntity.ok(userService.findByEmail(email));
   }

	@ApiOperation(value = "update user with params by email")
	@PutMapping("/{email}")
	public ResponseEntity<?> update(@Valid @RequestBody UserUpdateVO res, @PathVariable String email) {
		userService.update(res, email);
		return ResponseEntity.ok().build();
   }

   @ApiOperation(value = "create user with params")
   @PostMapping
  // @PreAuthorize("!hasAuthority('USER')")
   public UserResultVO create(@Valid @RequestBody UserCreateVO res) {
       return userService.save(res);
   }

   @ApiOperation(value = "delete user by email")
   @DeleteMapping("/{email}")
   //@PreAuthorize("!hasAuthority('USER')")
   public ResponseEntity<?> delete(@PathVariable String email) {
	   userService.deleteByEmail(email);
	   return ResponseEntity.ok().build();
   }
	
}
