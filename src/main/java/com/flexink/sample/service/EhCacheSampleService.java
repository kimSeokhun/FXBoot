package com.flexink.sample.service;

public interface EhCacheSampleService {

	public String getNoCache(String name) throws Exception;
	
	public String getCache(String name) throws Exception;
	
	public String cacheRefresh(String name) throws Exception;
}
