package com.flexink.sample.web;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Controller
@RequestMapping(value = "/prog")
public class ProgramController {

	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;

	@GetMapping(value="/a")
	public void getProg() {
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = this.requestMappingHandlerMapping.getHandlerMethods();

		for (Entry<RequestMappingInfo, HandlerMethod> item : handlerMethods.entrySet()) {
			RequestMappingInfo mapping = item.getKey();
			HandlerMethod method = item.getValue();

			for (String urlPattern : mapping.getPatternsCondition().getPatterns()) {
				System.out.println(method.getBeanType().getName() + "#" + method.getMethod().getName() + " <-- " + urlPattern);

				if (urlPattern.equals("some specific url")) {
					// add to list of matching METHODS
				}
			}
		}
	}
	
	@GetMapping(value="/b")
	public void getPro2g() {
	}
	
	
}
