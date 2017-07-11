package com.orrin.spring.cloud.security.oath2.auth.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Orrin on 2017/7/11.
 */

@RestController
public class UserController {
	@RequestMapping({ "/user", "/me" })
	public Map<String, String> user(Principal principal) {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("name", principal.getName());
		return map;
	}

	@RequestMapping("/hello")
	public String Hello() {
		return "hello word!";
	}

}
