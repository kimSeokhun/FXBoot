package com.flexink.sample.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flexink.domain.menu.Menu;
import com.flexink.sample.service.MenuService;
import com.flexink.vo.ParamsVo;

@Controller
@RequestMapping("/system/menus")
public class MenuController {
	
	@Autowired
	MenuService menuService;

	@GetMapping
	public String viewMenu() {
		return "/sample/menu";
	}
	
	@GetMapping(consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<Menu>> getMenus(ParamsVo params) {
		return new ResponseEntity<List<Menu>>(menuService.getMenus(params), HttpStatus.OK);
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public boolean saveMenus(@RequestBody List<Menu> menus) {
		System.out.println(menus);
		menuService.saveMenus(menus);
		return true;
	}
}
