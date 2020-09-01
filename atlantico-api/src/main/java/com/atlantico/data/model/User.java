package com.atlantico.data.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User implements Serializable {


	private static final long serialVersionUID = 3192721352579520261L;
    
    @Column(nullable = false)
    private String username;
    
    @Id
    @Email
    @Size(min = 0, max = 50)
    private String email;
    
    @JsonIgnore
    @Size(min = 0, max = 500)
    private String password;
    
    @Column(nullable = false, name = "created_date")
    private Date createdDate = new Date();
    
    @Column(name = "updated_date")
    private Date updatedDate;

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = @JoinColumn(name = "email"),
            inverseJoinColumns = @JoinColumn(name = "authority"))
    private Set<Authority> authorities;
    
    @Size(min = 0, max = 100)
    @Column(name = "activationkey")
    private String activationKey;

    @Size(min = 0, max = 100)
    @Column(name = "resetpasswordkey")
    private String resetPasswordKey;

    public User() {
    }

    public User(String username, String email) {
        super();
        this.username = username;
        this.email = email;
    }
    public User(User user) {
        super();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.authorities = user.getAuthorities();
    }
    public User(String username, String email, String password, Set<Authority> authorities) {
        super();
        this.username = username;
        this.email = email;
        this.authorities = authorities;
        this.password = password;
    }

	
}