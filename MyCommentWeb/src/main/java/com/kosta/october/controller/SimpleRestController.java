package com.kosta.october.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.october.domain.Person;

//@RestController
@Controller
public class SimpleRestController {
	
	@GetMapping("/ajax")
	public String ajax() {
		return "ajax";			// view 반환됨
	}
	
	@ResponseBody				// json 문자열이 Body에 담아서 감
	@PostMapping("/send")
	public Person test(@RequestBody Person p) {		// Body에 값 요청
		System.out.println("p = " + p);
		p.setName("kosta0902");
		p.setAge(p.getAge() + 10);
		return p;
	}
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
}
