package com.flexink.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flexink.security.SessionUserDetail;
import com.flexink.security.domain.Authorities;
import com.flexink.security.domain.User;
import com.flexink.security.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	MessageSourceAccessor messageSource;

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByusername(username);
		if (user == null) {
			throw new UsernameNotFoundException(messageSource.getMessage("DigestAuthenticationFilter.usernameNotFound", new Object[]{username}));
		}
		
		List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
		SimpleGrantedAuthority simpleGrantedAuthority;
		List<String> userRoles = new ArrayList<>();
		
		for(Authorities auth : user.getUserRoles()) {
			userRoles.add(auth.getRole().getRoleName());
			simpleGrantedAuthority = new SimpleGrantedAuthority(auth.getRole().getRoleName());
			simpleGrantedAuthorities.add(simpleGrantedAuthority);
		}
		
		
		log.info("username : {} , role : {} : ", username, userRoles);
		

		return new SessionUserDetail(user, userRoles, simpleGrantedAuthorities);
		//return user;
	}
}