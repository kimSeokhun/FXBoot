package com.flexink.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.flexink.security.domain.User;

public class SessionUserDetail extends org.springframework.security.core.userdetails.User {

	private List<String> roles;

	public SessionUserDetail(User user, List<String> roles, List<SimpleGrantedAuthority> roleList) {
		super(user.getUsername(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(),
				user.isCredentialsNonExpired(), user.isAccountNonLocked(), roleList);
		this.roles = roles;
	}

	/*
	 * public UserDetail(User user, List<String> roles,
	 * List<SimpleGrantedAuthority> roleList) { super(user.getUsername(),
	 * user.getPassword(), roleList); this.user = user; this.roles = roles; }
	 * 
	 * public UserDetail(User user, List<String> roles,
	 * List<SimpleGrantedAuthority> roleList, boolean enabled, boolean
	 * accountNonExpired, boolean credentialsNonExpired, boolean
	 * accountNonLocked) { super(user.getUsername(), user.getPassword(),
	 * enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
	 * roleList); this.user = user; this.roles = roles; }
	 */
}