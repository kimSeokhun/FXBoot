package com.flexink.config.web.security.user;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class LoginUserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return LoginUserDetails.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required", new Object[]{"username"}, "Enter your name");
		
		LoginUserDetails user = (LoginUserDetails) target;
		if(user.getPassword().length() <= 0) {
			
		}
		if(!user.getPassword().equals(user.getRetypePassword())) {
			errors.rejectValue("retypePassword", "error.confirmPassword", "password unmatchie");
		}
	}
	
	

}
