package com.orrin.spring.boot.secure.service;

import com.orrin.spring.boot.secure.domain.SysRolesAuthorities;
import org.springframework.data.domain.Page;

/**
 * @author Orrin on 2017/7/8.
 */
public interface SysRolesAuthoritiesService {
	Page<SysRolesAuthorities> findNoCriteria(Integer page, Integer size);
	Page<SysRolesAuthorities> findCriteria(Integer page,Integer size,SysRolesAuthorities sysRolesAuthorities);

}
