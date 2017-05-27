package com.xxx.sample.spring.boot.muti.ds.controller;

import com.xxx.sample.spring.boot.muti.ds.domain.City;
import com.xxx.sample.spring.boot.muti.ds.domain.User;
import com.xxx.sample.spring.boot.muti.ds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Orrin on 2017/5/27.
 */
@RestController
public class UserRestController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	/**
	 * 根据用户名获取用户信息，包括从库的地址信息
	 *
	 * @param userName
	 * @return
	 */
	@RequestMapping(value = "/api/user", method = RequestMethod.GET)
	public User findByName(@RequestParam(value = "userName", required = true) String userName) {
		return userService.findByName(userName);
	}

	@RequestMapping(value = "/api/user/insert", method = RequestMethod.GET)
	public int findByName() {

		long now = System.currentTimeMillis();
		long id = Long.parseLong(String.valueOf(now).substring(5,String.valueOf(now).length()));

		User user = new User();
		user.setId(id);
		user.setDescription(user.getId() + " 测试回滚");
		user.setUserName("测试回滚");

		City city = new City();
		city.setId(id);
		city.setCityName("测试回滚");
		city.setDescription(city.getId() + " 测试回滚");

		user.setCity(city);
		return userService.insertUser(user);
	}

}