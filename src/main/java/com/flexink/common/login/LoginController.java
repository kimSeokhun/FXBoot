package com.flexink.common.login;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flexink.project.config.web.WebSecurityConfigureAdapter;

@Controller
@RequestMapping(WebSecurityConfigureAdapter.SECURITY_PATH)
public class LoginController {
	

	@RequestMapping(WebSecurityConfigureAdapter.LOGIN_PAGE)
	public String loginPage() {
		return "/login/login";
	}
	
	@RequestMapping(WebSecurityConfigureAdapter.SECURITY_PATH + "/encodePw/{password}")
	@ResponseBody
	public String encodePw(@PathVariable String password) {
		String encoded = new BCryptPasswordEncoder().encode(password);
		return encoded;
	}
	
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
