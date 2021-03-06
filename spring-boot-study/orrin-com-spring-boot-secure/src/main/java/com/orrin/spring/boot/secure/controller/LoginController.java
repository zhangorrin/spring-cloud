package com.orrin.spring.boot.secure.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Orrin on 2017/7/10.
 */
@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping("/login")
	public String index(HttpServletRequest request, Model model) {
		Object o = request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");

		if(o instanceof AuthenticationException){
			model.addAttribute("loginerror", ((AuthenticationException)o).getMessage());
		}

		return "login";
	}

	@RequestMapping("/securityException/accessDenied")
	@ResponseBody
	public String accessDenied(HttpServletRequest request, Model model) {
		return "没有访问权限";
	}

}
