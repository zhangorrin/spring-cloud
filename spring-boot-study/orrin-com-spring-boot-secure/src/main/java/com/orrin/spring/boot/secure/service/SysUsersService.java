package com.orrin.spring.boot.secure.service;

import com.orrin.spring.boot.secure.domain.SysUsers;
import org.springframework.data.domain.Page;

/**
 * @author Orrin on 2017/7/8.
 */
public interface SysUsersService {
	Page<SysUsers> findSysUsersNoCriteria(Integer page, Integer size);
	Page<SysUsers> findSysUsersCriteria(Integer page,Integer size,SysUsers sysUsers);

	SysUsers findByUsername(String username);
}
