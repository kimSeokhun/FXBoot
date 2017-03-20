package com.flexink.utils;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class PhaseUtils implements EnvironmentAware {

	private static final String DEVELOPMENT = "development";
	private static final String PRODUCTION = "production";
	
    private static Environment environment;

    @Override
    public void setEnvironment(Environment _environment) {
        environment = _environment;
    }

    public static String phase() {
        String[] activeProfiles = environment.getActiveProfiles();
        String activeProfile = DEVELOPMENT;

        if (activeProfiles != null && activeProfiles.length > 0) {
            activeProfile = activeProfiles[0];
        }

        return activeProfile;
    }

    public static boolean isLocal() {
        return phase().equals(DEVELOPMENT);
    }


    public static boolean isProduction() {
        return phase().equals(PRODUCTION);
    }

    public static Environment getEnvironment() {
        return environment;
    }

    public static boolean isDevelopmentMode() {
        return isLocal() || Boolean.parseBoolean(System.getProperty("spring.profiles.active"));
    }
}