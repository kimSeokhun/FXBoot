package com.flexink.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthoritiesDto {

	private String url;
	
	private String roleName;
	
}