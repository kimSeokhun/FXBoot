package com.flexink.sample.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.flexink.sample.domain.Person;

@Controller
@RequestMapping("/sample")
public class ValidateSampleController extends WebMvcConfigurerAdapter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ValidateSampleController.class);
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/result").setViewName("/sample/result");
    }

    @GetMapping("/valid")
    public String showForm(Person person, ModelMap model) {
    	person.setName("AAA");
    	model.addAttribute("model", "modelA");
        return "/sample/form";
    }

    @PostMapping("/valid")
    public String checkPersonInfo(@Valid Person person, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
        	LOGGER.debug("Validator Errors : {} ", bindingResult.getAllErrors());
            return "/sample/form";
        }

        return "redirect:/result";
    }
}
