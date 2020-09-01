package com.atlantico.data.vo;

import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateVO {

	@NotNull
	private String username;
    private String password;
    private Set<String> authorities;
   
	public UserUpdateVO() {
		super();
	}
	public boolean notValid() {
		return StringUtils.isEmpty(username)
				|| StringUtils.isEmpty(username)
				|| Objects.isNull(authorities) || authorities.isEmpty();
	} 
    
}
