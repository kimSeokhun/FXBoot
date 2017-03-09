package com.flexink.project.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JspTestController {

	@RequestMapping("/jsp")
	public String jspTest(Model model) {
		return "test";
	}
	
}
