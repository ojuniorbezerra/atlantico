package com.atlantico.data.vo;

import java.util.Set;
import java.util.stream.Collectors;

import com.atlantico.data.enuns.Authorities;
import com.atlantico.data.model.Authority;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResultVO {

	private String username;
    private String email;
    private Set<Authorities> authorities;
    
	public UserResultVO(String username, String email, Set<Authority> authorities) {
		super();
		this.username = username;
		this.email = email;
		this.authorities = authorities.stream().map(a -> a.getName()).collect(Collectors.toSet());
	}
	public UserResultVO() {
		super();
	}
    
    
    
}
