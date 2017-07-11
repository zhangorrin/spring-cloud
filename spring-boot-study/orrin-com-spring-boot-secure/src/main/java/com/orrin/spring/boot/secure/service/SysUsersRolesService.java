package com.orrin.spring.boot.secure.service;

import com.orrin.spring.boot.secure.domain.SysUsersRoles;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Orrin on 2017/7/8.
 */
public interface SysUsersRolesService {
	Page<SysUsersRoles> findNoCriteria(Integer page, Integer size);
	Page<SysUsersRoles> findCriteria(Integer page,Integer size,SysUsersRoles sysUsersRoles);

	List<SysUsersRoles> findDistinctByUserId(String userId);
}
