package com.flexink.sample.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flexink.sample.service.EhCacheSampleService;

@RestController
@RequestMapping("/sample/cache")
public class EhCacheSampleController {

	@Autowired
	EhCacheSampleService ehCacheSampleService;
	
	/********************************************************************
	 * @메소드명	: getNoCache
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 캐쉬 없음
	 ********************************************************************/
	@GetMapping("/getNoCache/{name}")
	public String getNoCache(@PathVariable String name) throws Exception {
		return ehCacheSampleService.getNoCache(name);
	}
	
	/********************************************************************
	 * @메소드명	: getCache
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 캐쉬 호출
	 ********************************************************************/
	@GetMapping("/getCache/{name}")
	public String getCache(@PathVariable String name) throws Exception {
		return ehCacheSampleService.getCache(name);
	}
	
	/********************************************************************
	 * @메소드명	: getRefresh
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 캐쉬 삭제
	 ********************************************************************/
	@GetMapping("/refresh/{name}")
	public String getRefresh(@PathVariable String name) throws Exception {
		return ehCacheSampleService.cacheRefresh(name);
	}

}
