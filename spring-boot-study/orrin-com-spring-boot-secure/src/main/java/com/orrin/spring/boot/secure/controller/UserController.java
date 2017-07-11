package com.orrin.spring.boot.secure.controller;

import com.orrin.spring.boot.secure.domain.SysUsers;
import com.orrin.spring.boot.secure.service.SysUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Orrin on 2017/7/8.
 */
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private SysUsersService sysUsersService;

	@GetMapping("/all")
	@ResponseBody
	public Page<SysUsers> helloWorld() {
		return this.sysUsersService.findSysUsersNoCriteria(0,100);
	}
}
