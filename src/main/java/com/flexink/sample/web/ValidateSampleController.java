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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.flexink.sample.domain.Person;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/sample")
public class ValidateSampleController {
	
    /********************************************************************
     * @메소드명	: showForm
     * @작성자	: KIMSEOKHOON
     * @메소드 내용	: validate 페이지
     ********************************************************************/
    @GetMapping("/valid")
    public String showForm(Person person, ModelMap model) {
    	person.setName("AAA");
    	model.addAttribute("model", "modelA");
        return "/sample/form";
    }

    /********************************************************************
     * @메소드명	: checkPersonInfo
     * @작성자	: KIMSEOKHOON
     * @메소드 내용	: 유효성 검증 및 리다이렉트
     ********************************************************************/
    @PostMapping("/valid")
    public String checkPersonInfo(@Valid Person person, BindingResult bindingResult, RedirectAttributes redirectAttr) {

    	// 유효성 검증
        if (bindingResult.hasErrors()) {
        	log.debug("Validator Errors : {} ", bindingResult.getAllErrors());
            return "/sample/form";
        }
        
        // Redirect Post 전송
        redirectAttr.addFlashAttribute(person);
        return "redirect:result";
    }
    
    /********************************************************************
     * @메소드명	: result
     * @작성자	: KIMSEOKHOON
     * @메소드 내용	: validate 결과
     ********************************************************************/
    @GetMapping("/result")
    public String result(Person person, ModelMap model) {
        return "/sample/result";
    }
}
