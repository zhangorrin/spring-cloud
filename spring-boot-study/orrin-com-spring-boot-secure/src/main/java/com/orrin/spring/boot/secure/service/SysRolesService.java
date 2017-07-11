package com.orrin.spring.boot.secure.service;

import com.orrin.spring.boot.secure.domain.SysRoles;
import org.springframework.data.domain.Page;

/**
 * @author Orrin on 2017/7/8.
 */
public interface SysRolesService {
	Page<SysRoles> findNoCriteria(Integer page, Integer size);
	Page<SysRoles> findCriteria(Integer page,Integer size,SysRoles sysRoles);
}
