package com.flexink.sample.code;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample")
public class CodeMngController {

	/*@Autowired
	EhCacheSampleService ehCacheSampleService;*/
	
	@GetMapping("/code")
	public String viewCode() throws Exception {
		return "/sample/codeMng/codeMng";
	}
	
	@GetMapping("/code2")
	public String viewCode2() throws Exception {
		return "/sample/codeMng/codeMng2";
	}
	

}
