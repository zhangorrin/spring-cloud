package com.orrin.spring.boot.secure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Orrin on 2017/7/8.
 */
@Controller
public class IndexController {

	@RequestMapping(path = {"/","/index","/admin"})
	public String index(Model model) {
		Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
		model.addAttribute("msg", msg);
		return "index";
	}



}
