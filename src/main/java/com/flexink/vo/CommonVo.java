package com.flexink.vo;

import com.flexink.config.web.security.user.UserDetailsHelper;
import com.flexink.domain.sec.LoginUserDetails;

public class CommonVo {

	private LoginUserDetails loginUserDetails;
	
	public CommonVo() {
		this.loginUserDetails = (LoginUserDetails) UserDetailsHelper.getAuthenticatedUser();
	}
	
	/********************************************************************
	 * @메소드명	: getUserDetails
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 인증된 로그인정보 객체
	 ********************************************************************/
	public LoginUserDetails getUserDetails() {
		return this.loginUserDetails;
	}
	
	/********************************************************************
	 * @메소드명	: getLoginId
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 인증된 사용자 ID
	 ********************************************************************/
	public String getLoginId() {
		if(loginUserDetails != null && !loginUserDetails.equals("anonymousUser")) {
			return loginUserDetails.getUsername();
		}
		return "system";
	}

	
}
