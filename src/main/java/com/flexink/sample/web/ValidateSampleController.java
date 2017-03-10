package com.flexink.sample.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.flexink.sample.domain.PersonForm;

@Controller
@RequestMapping("/sample")
public class ValidateSampleController extends WebMvcConfigurerAdapter {
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/valid")
    public String showForm(PersonForm personForm) {
        return "form";
    }

    @PostMapping("/valid")
    public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
        	System.out.println(bindingResult.getAllErrors());
            return "form";
        }

        return "redirect:/results";
    }
}
