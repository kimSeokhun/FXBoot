package com.flexink.sample.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class Person {
	
	// JSR-303, JSR-380 참조
	
	@NotNull
	@Size(min = 2, max = 30)
	private String name;

	@NotNull
	@Min(18)
	private Integer age;
	
	@Email
	private String email;
	
	@Range(min=10, max=50, message="10에서 50사이를 입력해주세요.")
	private int range;

	
}
