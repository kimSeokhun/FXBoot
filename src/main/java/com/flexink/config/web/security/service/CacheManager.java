package com.flexink.config.web.security.service;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationListener;

import com.flexink.config.web.security.CacheEventMessage;
import com.flexink.domain.sec.Authorities;

public class CacheManager implements ApplicationListener<CacheEventMessage> {
	private Map<String, List<Authorities>> authorities;

	public Map<String, List<Authorities>> getAuthorities() {
		return authorities;
	}

	public List<Authorities> getAuthoritie(String key) {
		return authorities.get(key);
	}

	@Override
	public void onApplicationEvent(CacheEventMessage event) {
		//authorities = event.getAuthorities().stream().collect(groupingBy(Authorities::getUrl, toList()));
		
		/*String url;
		for (Authorities authorities : event.getAuthorities()) {
			url = authorities.getUrl();
			if (this.urlRoles.containsKey(url)) {
				List<String> roles = this.urlRoles.get(url);
				roles.add(authorities.getRoleName());

			} else {
				List<String> roles = new ArrayList<>();
				roles.add(authorities.getRoleName());
				this.urlRoles.put(url, roles);
			}
		}*/
		// grouping
		// url => roles

		// String url;
		// for (UserRoleDto userRoleDto : event.getUserRoleDto()) {
		// url = userRoleDto.getUrl();
		// if (this.urlRoles.containsKey(url)) {
		// List<String> roles = this.urlRoles.get(url);
		// roles.add(userRoleDto.getRoleName());
		//
		// } else {
		// List<String> roles = new ArrayList<>();
		// roles.add(userRoleDto.getRoleName());
		// this.urlRoles.put(url, roles);
		// }
		// }
	}
}