package com.flexink.project.config.web.security.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
@Entity
@Table(name = "AUTHORITY")
public class LoginUserGrantedAuthority implements GrantedAuthority {

	private static final long serialVersionUID = 2642610728784974077L;

	@Id
	@Column(name = "USERNAME")
	private String username;

	@Id
	@Column(name = "AUTHORITY")
	private String authority = null;

	public LoginUserGrantedAuthority() {
	}

	public LoginUserGrantedAuthority(String authority) {
		this.authority = authority;
	}
	

}
