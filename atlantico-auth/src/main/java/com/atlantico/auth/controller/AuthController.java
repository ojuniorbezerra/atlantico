package com.atlantico.auth.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    @GetMapping("/v1.0/principal")
    public Principal getCurrentLoggedInUser(Principal user) {
        return user;
    }
}