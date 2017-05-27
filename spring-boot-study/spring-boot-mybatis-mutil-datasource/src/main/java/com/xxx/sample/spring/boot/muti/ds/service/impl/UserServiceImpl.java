package com.xxx.sample.spring.boot.muti.ds.service.impl;

import com.xxx.sample.spring.boot.muti.ds.dao.cluster.CityDao;
import com.xxx.sample.spring.boot.muti.ds.dao.master.UserDao;
import com.xxx.sample.spring.boot.muti.ds.domain.City;
import com.xxx.sample.spring.boot.muti.ds.domain.User;
import com.xxx.sample.spring.boot.muti.ds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Orrin on 2017/5/27.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao; // 主数据源

	@Autowired
	private CityDao cityDao; // 从数据源

	@Override
	public User findByName(String userName) {

		User user = userDao.findByName(userName);

		City city = cityDao.findByName("温岭市");
		user.setCity(city);
		return user;
	}

	@Override
	@Transactional
	public int insertUser(User user) {
		insertCity(user.getCity());
		int i = insertOnlyUser(user);
		return i;
	}

	@Override
	@Transactional
	public int insertOnlyUser(User user) {
		int i = userDao.insertUser(user);
		if( i >= 0){
			throw new NullPointerException("测试回滚");
		}
		return i;

	}

	@Override
	@Transactional
	public int insertCity(City city) {
		int i = cityDao.insertCity(city);

		return i;
	}
}
