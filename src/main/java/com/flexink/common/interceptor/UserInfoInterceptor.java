/*package com.flexink.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.flexink.config.web.security.user.UserDetailsHelper;
import com.flexink.domain.sec.LoginUserDetails;

@Component
public class UserInfoInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		LoginUserDetails user = UserDetailsHelper.getLoginUserDetails();
		modelAndView.addObject("loginVo", user);
		
		super.postHandle(request, response, handler, modelAndView);
	}

}
*/