package com.flexink.common.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flexink.config.web.WebSecurityConfigureAdapter;
import com.flexink.config.web.security.user.Role;
import com.flexink.config.web.security.user.UserDetailsHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping()
public class LoginController {
	

	/********************************************************************
	 * @메소드명	: loginPage
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 로그인 페이지
	 ********************************************************************/
	@RequestMapping(WebSecurityConfigureAdapter.LOGIN_PAGE)
	public String loginPage(HttpServletRequest request) {
		
		System.out.println(UserDetailsHelper.getAuthorities());
		System.out.println(UserDetailsHelper.containsAuthority(Role.ROLE_ADMIN.toString()));
		System.out.println(UserDetailsHelper.getRoleType());
		
		if(UserDetailsHelper.isAuthenticated()) {
			if(UserDetailsHelper.getRoleType().equals(Role.ROLE_ADMIN.toString())) {
				log.debug("redirect To Admin Page");
				return "redirect:/";
			} else {
				log.debug("redirect To User Page");
				return "redirect:/";
			}
		}
		
		return "/login/login";
	}
	
	/********************************************************************
	 * @메소드명	: encodePw
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: BCrypt 패스워드 인코딩 테스트
	 ********************************************************************/
	@RequestMapping(WebSecurityConfigureAdapter.SECURITY_PATH + "/encodePw/{password}")
	@ResponseBody
	public String encodePw(@PathVariable String password) {
		String encoded = new BCryptPasswordEncoder().encode(password);
		return encoded;
	}
	
	/********************************************************************
	 * @메소드명	: matches
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: BCrypt 패스워드 매칭 테스트
	 ********************************************************************/
	@RequestMapping(WebSecurityConfigureAdapter.SECURITY_PATH + "/matches")
	@ResponseBody
	public Map<String, Object> matches(@RequestParam Map<String,Object> map) {
		String rowPw = (String) map.get("rowPw");
		String encodedPw = (String) map.get("encodedPw");
		boolean matches = new BCryptPasswordEncoder().matches(rowPw, encodedPw);
		
		Map<String, Object> result = new HashMap<>();
		result.put("RowPassword", rowPw);
		result.put("EncodedPassword", encodedPw);
		result.put("Matches", matches);
		return result;
	}
}
