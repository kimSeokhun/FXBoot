package com.flexink.project.common.scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@PropertySource("classpath:project.properties")
public class ScheduledTasks {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);
	
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");
	
	@Value("${scheduled.a}")
	String scheduledA;
	
	@Value("${scheduled.b}")
	String scheduledB;
	
	@Scheduled(cron="${scheduled.a}")
	public void reportCurrentTimeA(){
		LOGGER.debug("[cron={}]The time is now {}", scheduledA, DATE_FORMAT.format(new Date()));
	}
	
	@Scheduled(fixedDelayString="${scheduled.b}")
	public void reportCurrentTimeB(){
		LOGGER.debug("[fixedRate={}]The time is now {}", scheduledB, DATE_FORMAT.format(new Date()));
	}
	
	
}
