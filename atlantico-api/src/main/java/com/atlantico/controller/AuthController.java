package com.atlantico.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value = "Principal EndPoint", tags = {"PrincipalEndPoint"})
@RestController
public class AuthController {

	@ApiOperation(value = "get current principal")
    @GetMapping("/v1.0/principal")
    public Principal getCurrentLoggedInUser(Principal user) {
        return user;
    }
}