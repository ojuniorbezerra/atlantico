package com.atlantico.data.vo;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateVO {

	@NotNull
	private String username;
	@NotNull
    private String email;
    private String password;
    private Set<String> authorities;
    
	public UserCreateVO(String username, String email) {
		super();
		this.username = username;
		this.email = email;
	}
	public UserCreateVO() {
		super();
	}
	public boolean notValid() {
		return StringUtils.isEmpty(email) || StringUtils.isEmpty(username)
				|| StringUtils.isEmpty(username)
				|| Objects.isNull(authorities) || authorities.isEmpty();
	}
    
}
