package com.flexink.project.config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

public class CustomSiteMeshFilter extends ConfigurableSiteMeshFilter {

	@Value("${spring.h2.console.path}")
    String h2ConsolePath;
	
	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		System.out.println("###################");
		System.out.println(h2ConsolePath);
		System.out.println("###################");
		builder.addDecoratorPath("/*", "/WEB-INF/decorators/decorator.jsp")
			//.addExcludedPath(h2ConsolePath+"/**")
			.addExcludedPath("/h2-console/**")
			.addExcludedPath("/security/**")
			.addExcludedPath("/popup/**");
    }
}
