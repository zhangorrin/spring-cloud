package com.orrin.spring.cloud.security.oath2.auth.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@RequestMapping(path = {"/","/index"})
	public String index(Model model) {
		model.addAttribute("title", "测试标题");
		model.addAttribute("content", "测试内容");
		model.addAttribute("extraInfo", "额外信息，只对管理员显示");
		return "index";
	}

}
