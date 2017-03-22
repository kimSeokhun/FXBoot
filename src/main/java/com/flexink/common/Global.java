package com.flexink.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:project.properties")
public class Global {

	@Value("${scheduled.a}")
	public static String scheduledA = "AAAAAAAAAA";
}
