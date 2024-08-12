package com.kosta.kostait2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {
	
	//@RequestMapping(value = "/register/add", method = {RequestMethod.GET})		//method가 GET방식은 생략가능, value값 하나면 생략가능
	//@RequestMapping(value = "/register/add")
	//@GetMapping("/register/add")
	public String register() {
		return "registerForm";			// /WEB-INF/views/registerForm.jsp
	}
	
	//@RequestMapping(value = "/register/save", method = {RequestMethod.POST})
	@PostMapping("/register/save")
	public String save() {
		return "registerinfo";				// /WEB-INF/views/registerinfo.jsp
	}
}
