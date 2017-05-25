package com.flexink.sample.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/boardMng")
public class BoardMngController {

	@GetMapping
	public String viewBoardMng() {
		
		return "/sample/boardMng";
	}
}
