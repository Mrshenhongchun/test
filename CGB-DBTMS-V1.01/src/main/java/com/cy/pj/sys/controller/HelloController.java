package com.cy.pj.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HelloController {
	@RequestMapping("doSayHello")
	@ResponseBody
	public String doSayHello() {
		return "hello spring boot";
	}

	@RequestMapping("doSayHelloUI")
	public String doSayHelloUI() {
		return "hello";
	}

}
