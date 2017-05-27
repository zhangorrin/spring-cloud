package com.xxx.sample.spring.boot.muti.ds.dao.master;

import com.xxx.sample.spring.boot.muti.ds.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Orrin on 2017/5/27.
 */
@Mapper
public interface UserDao {

	/**
	 * 根据用户名获取用户信息
	 *
	 * @param userName
	 * @return
	 */
	User findByName(@Param("userName") String userName);

	int insertUser(User user);
}