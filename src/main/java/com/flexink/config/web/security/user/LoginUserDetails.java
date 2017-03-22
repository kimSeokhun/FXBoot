package com.flexink.config.web.security.user;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
@Entity
@Table(name = "USER")
public class LoginUserDetails implements UserDetails, Serializable{

	private static final long serialVersionUID = -8507239908294868883L;

	@Size(min=2, max=20)
	@NotNull
	@Id
	@Column(name = "USERNAME", unique = true, nullable = false)
	private String username;

	@Size(min=4)
	@NotNull
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@Transient
	private String retypePassword;
	
	@Email
	@Column(name = "EMAIL", nullable = true)
	private String email;

	@Column(name = "ACCOUNTNONEXPIRED")
	private boolean accountNonExpired;

	@Column(name = "ACCOUNTNONLOCKED")
	private boolean accountNonLocked;

	@Column(name = "CREDENTIALSNONEXPIRED")
	private boolean credentialsNonExpired;

	@Column(name = "ENABLED")
	private boolean enabled;


	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="USERNAME")
	private Collection<LoginUserGrantedAuthority> authorities;



}
