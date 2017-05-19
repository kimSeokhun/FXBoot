package com.flexink.config.web.security;

import java.util.List;

import org.springframework.context.ApplicationEvent;

import com.flexink.domain.sec.Authorities;

public class CacheEventMessage extends ApplicationEvent {

	 final List<Authorities> authorities;

	  public CacheEventMessage(Object source, final List<Authorities> authorities) {
	    super(source);
	    this.authorities = authorities;
	  }

	  public List<Authorities> getAuthorities() {
	    return authorities;
	  }

}
