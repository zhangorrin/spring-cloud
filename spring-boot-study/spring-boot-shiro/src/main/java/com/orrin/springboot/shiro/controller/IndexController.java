package com.orrin.springboot.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Orrin on 2017/6/8.
 */
@Controller
@RequestMapping(value = "/pages")
public class IndexController {

	@RequestMapping(value = "/login")
	public String showLoginForm(HttpServletRequest req, HttpServletResponse response, Model model) {

		String destinationPage = "/pages/login";

		if(SecurityUtils.getSubject().isAuthenticated()){
			model.addAttribute("loginName", SecurityUtils.getSubject().getPrincipal());
			destinationPage = "/pages/index";
		}

		return destinationPage;
	}

	@RequestMapping(value = "/index")
	public String index(HttpServletRequest req, HttpServletResponse response, Model model) {
		model.addAttribute("loginName", SecurityUtils.getSubject().getPrincipal());
		return "/pages/index";
	}
}
