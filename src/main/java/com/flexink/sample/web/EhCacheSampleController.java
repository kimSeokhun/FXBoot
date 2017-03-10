package com.flexink.sample.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flexink.sample.service.EhCacheSampleService;

@RestController
@RequestMapping("/sample/cache")
public class EhCacheSampleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EhCacheSampleController.class);
	
	@Autowired
	EhCacheSampleService ehCacheSampleService;
	
	@GetMapping("/getNoCache/{name}")
	public String getNoCache(@PathVariable String name) throws Exception {
		return ehCacheSampleService.getNoCache(name);
	}
	
	@GetMapping("/getCache/{name}")
	public String getCache(@PathVariable String name) throws Exception {
		return ehCacheSampleService.getCache(name);
	}
	
	@GetMapping("/refresh/{name}")
	public String getRefresh(@PathVariable String name) throws Exception {
		return ehCacheSampleService.cacheRefresh(name);
	}

}
