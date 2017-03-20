package com.flexink.common.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(ErrorController.PATH_ROOT)
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {

	public static final String PATH_ROOT 			= "/error";
	public static final String PATH_ERROR_DEFAULT 	= "/default";
	public static final String PATH_ERROR_401 		= "/401";
	public static final String PATH_ERROR_403 		= "/403";
	public static final String PATH_ERROR_404 		= "/404";
	public static final String PATH_ERROR_500 		= "/500";


	@RequestMapping(value = PATH_ERROR_DEFAULT, method = RequestMethod.GET)
	public String defaultError() {
		return "error/default";
	}

	@RequestMapping(value = PATH_ERROR_401, method = RequestMethod.GET)
	public String error401() {
		return "error/401";
	}

	@RequestMapping(value = PATH_ERROR_403, method = RequestMethod.GET)
	public String error403() {
		return "error/403";
	}

	@RequestMapping(value = PATH_ERROR_404, method = RequestMethod.GET)
	public String error404() {
		return "error/404";
	}

	@RequestMapping(value = PATH_ERROR_500, method = RequestMethod.GET)
	public String error500() {
		return "error/500";
	}

	@Override
	public String getErrorPath() {
		return "error/default";
	}
}
