package com.flexink.config.web.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.flexink.config.web.security.service.CacheManager;
import com.flexink.domain.sec.Authorities;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FilterMetadataSource implements FilterInvocationSecurityMetadataSource, InitializingBean {

	
	@Autowired
	private CacheManager cacheManager;
	
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		FilterInvocation fi = (FilterInvocation) object;
	    String url = fi.getRequestUrl();
	    
	    log.debug("FilterMetadataSource : FilterInvocation : url {}", url);
	    
	    List<Authorities> authorities = cacheManager.getAuthorities().get(url);
	    if (authorities == null) {
	      return null;
	    }
	    
	    log.debug("FilterMetadataSource : authorities : {} ", authorities);
	    
	    for(Authorities auth : authorities) {
	    	
	    }
	    //List<String> roles = authorities.stream().map(Authorities::getRoleName).collect(Collectors.toList());
	    List<String> roles = null;

	    String[] stockArr = new String[roles.size()];
	    stockArr = roles.toArray(stockArr);

	    return SecurityConfig.createList(stockArr);
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}


}
