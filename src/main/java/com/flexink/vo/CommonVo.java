package com.flexink.vo;

import com.flexink.config.web.security.user.UserDetailsHelper;
import com.flexink.security.SessionUser;

public class CommonVo {

	private SessionUser sessionUser;
	
	public CommonVo() {
		if(UserDetailsHelper.getAuthenticatedUser() instanceof SessionUser) {
			this.sessionUser = (SessionUser) UserDetailsHelper.getAuthenticatedUser();
		} else {
			this.sessionUser = null;
		}
	}
	
	/********************************************************************
	 * @메소드명	: getUserDetails
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 인증된 로그인정보 객체
	 ********************************************************************/
	public SessionUser getUserDetails() {
		return this.sessionUser;
	}
	
	/********************************************************************
	 * @메소드명	: getLoginId
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 인증된 사용자 ID
	 ********************************************************************/
	public String getLoginId() {
		if(sessionUser != null && !sessionUser.equals("anonymousUser")) {
			return sessionUser.getUsername();
		}
		return "system";
	}

	
}
