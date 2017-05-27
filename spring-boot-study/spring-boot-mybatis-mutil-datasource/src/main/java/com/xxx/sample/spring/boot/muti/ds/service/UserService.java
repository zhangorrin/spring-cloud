package com.xxx.sample.spring.boot.muti.ds.service;

import com.xxx.sample.spring.boot.muti.ds.domain.City;
import com.xxx.sample.spring.boot.muti.ds.domain.User;

/**
 * @author Orrin on 2017/5/27.
 */
public interface UserService {


	/**
	 * 根据用户名获取用户信息，包括从库的地址信息
	 *
	 * @param userName
	 * @return
	 */
	User findByName(String userName);

	int insertUser(User user);

	int insertOnlyUser(User user);

	int insertCity(City city);
}
