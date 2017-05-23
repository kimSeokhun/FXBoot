package com.flexink.common.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flexink.config.web.security.user.Role;
import com.flexink.domain.sec.LoginUserDetails;
import com.flexink.domain.sec.LoginUserGrantedAuthority;
import com.flexink.domain.sec.repository.LoginUserGrantedAuthorityRepository;
import com.flexink.domain.sec.repository.LoginUserRepository;

import lombok.extern.slf4j.Slf4j;

/**************************************************************
 * @FileName	: LoginUserService.java
 * @Project		: fxBoot
 * @Package_Name: com.flexink.common.login.service
 * @Date		: 2017. 3. 23. 
 * @작성자		: KIMSEOKHOON
 * @변경이력		:
 * @프로그램 설명 	: 사용자 신규 등록
 **************************************************************/
@Slf4j
@Service
@Transactional
public class LoginUserService {
	
	@Autowired
	LoginUserRepository userRepository;
	@Autowired
	LoginUserGrantedAuthorityRepository authorityRepository;

	
	/********************************************************************
	 * @메소드명	: regUser
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 사용자 등록
	 ********************************************************************/
	public LoginUserDetails regUser(LoginUserDetails user) {
		// 비밀번호 bcryto 변환
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		// Authority 등록
		LoginUserGrantedAuthority authority = new LoginUserGrantedAuthority(Role.ROLE_ADMIN.toString());
		authorityRepository.save(authority);
		// 사용자 Authority 참조 관계 설정 및 등록
		user.addAuthorities(authority);
		userRepository.save(user);
		
		log.debug("사용자 등록됨 : {}", user);
		return user;
	}
}
