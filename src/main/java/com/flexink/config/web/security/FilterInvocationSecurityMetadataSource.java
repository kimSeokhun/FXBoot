package com.flexink.config.web.security;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FilterInvocationSecurityMetadataSource
		implements org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource {

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

		log.debug("FilterInvocationSecurityMetadataSource  "+object.toString());
		FilterInvocation fi = (FilterInvocation) object;
		HttpServletRequest request 		= fi.getHttpRequest();
		HttpServletResponse response 	= fi.getResponse();
		String url = request.getRequestURI();

		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}
//	@Override
//	public void afterPropertiesSet() throws Exception {
//		resourceMetaService.findAllResources();
//	}

}
