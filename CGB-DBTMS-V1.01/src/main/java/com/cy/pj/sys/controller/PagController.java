package com.cy.pj.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.experimental.PackagePrivate;

/**
 * 在controller中
 */
@Controller
@RequestMapping("/")
public class PagController {
	@RequestMapping("doIndexUI")
	public String doIndexUI() {
		return "starter";
	}

	@RequestMapping("log/doLogUI")
	public String doLogUI() {
		return "sys/log_list";
	}
	
	@RequestMapping("doPageUI")
	public String doPageUI() {
		return "common/page";
	}
	@RequestMapping("menu/doMenuListUI")
	public String doMenuListUI(){
	return "sys/menu_list";
	}
	@RequestMapping("menu/doMenuEditUI")
	 public String doMenuEditUI(){
		 return "sys/menu_edit";
	 }
	@RequestMapping("role/doRoleListUI")
	public String doRoleListUI() {
		return "sys/role_list";
	}
	@RequestMapping("role/doRoleEditUI")
	public String doRoleEditUI(){
			return "sys/role_edit";
	}
	
	@RequestMapping("doDeptListUI")
	public String doDeptListUI(){
	return "sys/dept_list";
	}


	

	



}
